# Airport Emergency Response System - Backend

机场应急事件响应系统后端服务

## 快速开始

### 环境要求
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### 安装和运行

1. 克隆项目
```bash
git clone <repository-url>
cd backend
```

2. 配置数据库
编辑 `src/main/resources/application.yml`
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/airport_emergency
    username: root
    password: your_password
```

3. 安装依赖
```bash
mvn clean install
```

4. 运行项目
```bash
mvn spring-boot:run
```

项目将在 http://localhost:8080 启动

## 项目结构

```
src/main/java/com/airport/emergency/
├── controller/          # 控制层
├── service/             # 业务逻辑层
├── repository/          # 数据访问层
├── entity/              # 实体类
├── dto/                 # 数据传输对象
├── config/              # 配置类
├── util/                # 工具类
└── AirportEmergencyApplication.java  # 主启动类
```

## API 文档

### 认证相关

#### 登录
```
POST /api/auth/login
```

#### 令牌刷新
```
POST /api/auth/refresh?refreshToken=xxx
```

### 用户管理

#### 注册用户
```
POST /api/users/register
```

#### 获取用户信息
```
GET /api/users/{id}
```

### 事件管理

#### 创建事件
```
POST /api/events?reporterId=xxx
```

#### 获取事件列表
```
GET /api/events
GET /api/events/status/{status}
GET /api/events/priority/{priority}
```

### 资源管理

#### 创建资源
```
POST /api/resources
```

#### 获取可用资源
```
GET /api/resources/available
```

### 位置追踪

#### 记录位置
```
POST /api/locations
```

#### 获取最新位置
```
GET /api/locations/latest/{resourceId}
```

### 统计分析

#### 获取系统统计
```
GET /api/statistics
```

## 部署

### Docker 部署

构建 Docker 镜像
```bash
mvn clean package -DskipTests
docker build -t airport-emergency-backend:1.0.0 .
```

运行容器
```bash
docker run -d \
  -p 8080:8080 \
  -e DB_HOST=mysql-host \
  -e DB_USERNAME=root \
  -e DB_PASSWORD=password \
  -e JWT_SECRET=your-secret-key \
  airport-emergency-backend:1.0.0
```

## 许可证

MIT License
