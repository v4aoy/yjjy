-- 创建数据库
CREATE DATABASE IF NOT EXISTS airport_emergency
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE airport_emergency;

-- 用户表
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  full_name VARCHAR(100),
  role ENUM('ADMIN', 'DISPATCHER', 'RESPONDER', 'REPORTER') NOT NULL DEFAULT 'REPORTER',
  enabled BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX idx_username (username),
  INDEX idx_role (role),
  INDEX idx_enabled (enabled)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 应急事件表
CREATE TABLE emergency_events (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  event_type ENUM('FIRE', 'MEDICAL', 'SECURITY', 'HAZMAT', 'OTHER') NOT NULL,
  event_status ENUM('REPORTED', 'ASSIGNED', 'IN_PROGRESS', 'RESOLVED', 'CANCELLED') NOT NULL DEFAULT 'REPORTED',
  priority ENUM('LOW', 'MEDIUM', 'HIGH', 'CRITICAL') NOT NULL DEFAULT 'HIGH',
  location_name VARCHAR(255),
  latitude DOUBLE,
  longitude DOUBLE,
  description TEXT,
  reporter_id BIGINT NOT NULL,
  dispatcher_id BIGINT,
  resolution_remark TEXT,
  reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  assigned_at TIMESTAMP NULL,
  resolved_at TIMESTAMP NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (reporter_id) REFERENCES users(id) ON DELETE RESTRICT,
  FOREIGN KEY (dispatcher_id) REFERENCES users(id) ON DELETE SET NULL,
  INDEX idx_event_type (event_type),
  INDEX idx_event_status (event_status),
  INDEX idx_priority (priority),
  INDEX idx_reporter_id (reporter_id),
  INDEX idx_dispatcher_id (dispatcher_id),
  INDEX idx_reported_at (reported_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 资源表
CREATE TABLE resources (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  resource_code VARCHAR(50) UNIQUE NOT NULL,
  resource_type ENUM('VEHICLE', 'EQUIPMENT', 'PERSONNEL', 'OTHER') NOT NULL,
  resource_category ENUM('AMBULANCE', 'FIRE_TRUCK', 'POLICE_CAR', 'MEDICAL_KIT', 'OXYGEN', 'STRETCHER', 'OTHER') NOT NULL,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  resource_status ENUM('AVAILABLE', 'IN_USE', 'MAINTENANCE', 'OUT_OF_SERVICE') NOT NULL DEFAULT 'AVAILABLE',
  owner_id BIGINT,
  assigned_event_id BIGINT,
  latitude DOUBLE,
  longitude DOUBLE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE SET NULL,
  FOREIGN KEY (assigned_event_id) REFERENCES emergency_events(id) ON DELETE SET NULL,
  INDEX idx_resource_code (resource_code),
  INDEX idx_resource_type (resource_type),
  INDEX idx_resource_status (resource_status),
  INDEX idx_owner_id (owner_id),
  INDEX idx_assigned_event_id (assigned_event_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 位置跟踪表
CREATE TABLE locations (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  resource_id BIGINT NOT NULL,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL,
  altitude DOUBLE,
  accuracy DOUBLE,
  recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (resource_id) REFERENCES resources(id) ON DELETE CASCADE,
  INDEX idx_resource_id (resource_id),
  INDEX idx_recorded_at (recorded_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 事件资源关联表
CREATE TABLE event_resources (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  event_id BIGINT NOT NULL,
  resource_id BIGINT NOT NULL,
  assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  released_at TIMESTAMP NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (event_id) REFERENCES emergency_events(id) ON DELETE CASCADE,
  FOREIGN KEY (resource_id) REFERENCES resources(id) ON DELETE CASCADE,
  UNIQUE KEY unique_event_resource (event_id, resource_id),
  INDEX idx_event_id (event_id),
  INDEX idx_resource_id (resource_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 操作日志表
CREATE TABLE operation_logs (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT,
  operation_type VARCHAR(50) NOT NULL,
  entity_type VARCHAR(50) NOT NULL,
  entity_id BIGINT,
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL,
  INDEX idx_user_id (user_id),
  INDEX idx_operation_type (operation_type),
  INDEX idx_entity_type (entity_type),
  INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 插入测试数据
INSERT INTO users (username, password, full_name, role, enabled) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoXyea0BQZ5h0H5kXzgSQAYqZZ3q8PHLwKB2', '系统管理员', 'ADMIN', TRUE),
('dispatcher', '$2a$10$N9qo8uLOickgx2ZMRZoXyea0BQZ5h0H5kXzgSQAYqZZ3q8PHLwKB2', '调度员', 'DISPATCHER', TRUE),
('responder', '$2a$10$N9qo8uLOickgx2ZMRZoXyea0BQZ5h0H5kXzgSQAYqZZ3q8PHLwKB2', '应急响应人员', 'RESPONDER', TRUE),
('reporter', '$2a$10$N9qo8uLOickgx2ZMRZoXyea0BQZ5h0H5kXzgSQAYqZZ3q8PHLwKB2', '事件报告者', 'REPORTER', TRUE);
