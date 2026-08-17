# 机场应急救援管理系统

## 项目简介

机场应急救援管理系统是一个综合性的应急救援管理平台，用于管理机场的突发事件、救援资源和应急响应流程。

### 系统功能

1. **应急事件管理**
   - 事件创建、分配和追踪
   - 事件优先级管理
   - 事件状态流转

2. **救援资源管理**
   - 人员管理（救援队、医疗队等）
   - 车辆管理（消防车、救护车等）
   - 设备管理（灭火器、医疗设备等）

3. **实时位置追踪**
   - 救援人员位置追踪
   - 救援资源位置管理
   - 路线优化

4. **数据报表和统计**
   - 事件统计分析
   - 资源使用情况
   - 应急响应效率报表

5. **权限管理**
   - 管理员：系统全权管理
   - 调度员：事件分配和调度
   - 救援队：接收任务和上报状态
   - 普通用户：查看基本信息

## 技术栈

- **后端**：Spring Boot 2.7.x + Spring Data JPA + MySQL 8.0
- **前端**：Vue.js 3.x + Element Plus + Axios
- **数据库**：MySQL 8.0
- **其他**：Redis（缓存）、JWT（认证）

## 项目结构

```
yjjy/
├── backend/                 # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/airport/emergency/
│   │   │   │       ├── config/          # 配置类
│   │   │   │       ├── controller/      # 控制器
│   │   │   │       ├── service/         # 业务逻辑
│   │   │   │       ├── repository/      # 数据访问层
│   │   │   │       ├── entity/          # 实体类
│   │   │   │       ├── dto/             # 数据传输对象
│   │   │   │       ├── util/            # 工具类
│   │   │   │       ├── exception/       # 异常处理
│   │   │   │       └── Application.java # 启动类
│   │   │   └── resources/
│   │   │       ├── application.yml      # 配置文件
│   │   │       └── db/
│   │   │           └── migration/       # 数据库迁移脚本
│   │   └── test/
│   └── pom.xml
├── frontend/                # 前端项目
│   ├── src/
│   │   ├── components/      # 组件
│   │   ├── views/           # 页面
│   │   ├── api/             # API调用
│   │   ├── router/          # 路由
│   │   ├── store/           # 状态管理（Vuex）
│   │   ├── App.vue          # 根组件
│   │   └── main.js          # 入口文件
│   ├── public/              # 静态资源
│   ├── package.json
│   └── vue.config.js        # Vue配置
└── docs/                    # 文档
    ├── 设计文档.md
    ├── API文档.md
    ├── 数据库设计.md
    └── 部署指南.md
```

## 快速开始

### 后端启动

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

后端服务运行在 `http://localhost:8080`

### 前端启动

```bash
cd frontend
npm install
npm run serve
```

前端服务运行在 `http://localhost:8081`

## 默认账户

| 角色 | 用户名 | 密码 | 说明 |
|------|--------|------|------|
| 管理员 | admin | admin123 | 系统管理员 |
| 调度员 | dispatcher | dispatcher123 | 应急调度员 |
| 救援队 | rescue | rescue123 | 救援队队长 |
| 普通用户 | user | user123 | 普通用户 |

## 文档

- [详细设计文档](./docs/设计文档.md)
- [API文档](./docs/API文档.md)
- [数据库设计](./docs/数据库设计.md)
- [部署指南](./docs/部署指南.md)

## 贡献指南

欢迎提交 Issues 和 Pull Requests！

## 许可证

MIT License
