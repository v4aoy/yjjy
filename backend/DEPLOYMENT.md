# 项目部署和运维指南

## 本地开发环境

### 环境要求
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Docker（可选）

### 快速启动

#### 使用Docker Compose（推荐）
```bash
# 1. 进入项目目录
cd backend

# 2. 构建镜像并启动容器
docker-compose up -d

# 3. 查看日志
docker-compose logs -f backend

# 4. 停止容器
docker-compose down
```

#### 本地开发（不使用Docker）
```bash
# 1. 启动MySQL
mysql -u root -p < init.sql

# 2. 编辑配置文件
vi src/main/resources/application.yml

# 3. 构建项目
mvn clean install

# 4. 运行项目
mvn spring-boot:run
```

## 生产环境部署

### 构建Docker镜像
```bash
# 1. 打包项目
mvn clean package -DskipTests

# 2. 构建镜像
docker build -t airport-emergency-backend:1.0.0 .

# 3. 标记镜像（可选）
docker tag airport-emergency-backend:1.0.0 your-registry/airport-emergency-backend:1.0.0

# 4. 推送到镜像仓库（可选）
docker push your-registry/airport-emergency-backend:1.0.0
```

### 部署到服务器

#### 使用Docker
```bash
# 1. 登录到服务器
ssh user@server-ip

# 2. 拉取镜像
docker pull your-registry/airport-emergency-backend:1.0.0

# 3. 运行容器
docker run -d \
  --name airport-emergency-backend \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-host:3306/airport_emergency \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=password \
  -e JWT_SECRET=your-secret-key \
  your-registry/airport-emergency-backend:1.0.0
```

#### 使用Kubernetes（可选）
```bash
# 1. 创建命名空间
kubectl create namespace airport-emergency

# 2. 创建配置密钥
kubectl create secret generic airport-emergency-secrets \
  --from-literal=db-password=password \
  --from-literal=jwt-secret=your-secret-key \
  -n airport-emergency

# 3. 部署应用
kubectl apply -f deployment.yaml -n airport-emergency
```

## 系统监控

### 日志查看
```bash
# Docker 容器日志
docker logs -f airport-emergency-backend

# 本地日志
tail -f logs/airport-emergency.log
```

### 健康检查
```bash
# 检查应用是否运行
curl http://localhost:8080/api/users

# 检查数据库连接
curl http://localhost:8080/actuator/health
```

## 性能优化

### 数据库优化
- 已创建必要的索引
- 建议定期执行 `ANALYZE TABLE` 更新统计信息
- 配置适当的连接池大小（推荐20-50）

### Java JVM优化
```bash
# 设置JVM参数（可在docker-compose.yml或启动脚本中配置）
-Xmx1024m -Xms512m -XX:+UseG1GC
```

### 缓存策略
- 考虑添加Redis缓存层以提升性能
- 缓存用户信息、资源列表等热数据

## 备份和恢复

### 数据库备份
```bash
# 完整备份
mysqldump -u root -p airport_emergency > backup.sql

# 定期备份脚本
#!/bin/bash
mysqldump -u root -p airport_emergency > /backup/airport_emergency_$(date +%Y%m%d_%H%M%S).sql
```

### 数据恢复
```bash
mysql -u root -p airport_emergency < backup.sql
```

## 故障排查

### 常见问题

1. **数据库连接失败**
   - 检查MySQL是否运行
   - 验证数据库用户名和密码
   - 检查防火墙设置

2. **应用无法启动**
   - 查看日志文件了解错误信息
   - 确保Java版本为17+
   - 检查必要的依赖库是否已安装

3. **性能问题**
   - 检查数据库查询是否使用了正确的索引
   - 增加JVM堆内存
   - 考虑添加缓存层

## 安全建议

1. **密码安全**
   - 修改默认密码
   - 使用强密码策略
   - 定期更新密码

2. **JWT令牌**
   - 使用强密钥
   - 定期轮换密钥
   - 实现令牌黑名单机制

3. **数据库安全**
   - 限制数据库访问IP
   - 使用SSL连接
   - 定期备份数据

4. **网络安全**
   - 使用HTTPS
   - 配置防火墙规则
   - 实现速率限制

## 更新和升级

### 应用升级流程
```bash
# 1. 构建新版本
mvn clean package -DskipTests

# 2. 构建新镜像
docker build -t airport-emergency-backend:1.0.1 .

# 3. 停止旧容器
docker stop airport-emergency-backend

# 4. 启动新容器
docker run -d --name airport-emergency-backend -p 8080:8080 airport-emergency-backend:1.0.1

# 5. 删除旧容器
docker rm airport-emergency-backend
```

### 数据库迁移
- Hibernate会自动更新数据库架构（ddl-auto: update）
- 对于重大更改，建议先备份数据
- 测试迁移脚本
