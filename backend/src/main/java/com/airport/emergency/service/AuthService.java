package com.airport.emergency.service;

import com.airport.emergency.dto.LoginRequest;
import com.airport.emergency.dto.LoginResponse;

/**
 * 认证服务接口
 */
public interface AuthService {
    /**
     * 用户登录
     */
    LoginResponse login(LoginRequest request);

    /**
     * 刷新令牌
     */
    LoginResponse refreshToken(String refreshToken);

    /**
     * 验证令牌
     */
    boolean validateToken(String token);

    /**
     * 从令牌中获取用户名
     */
    String getUsernameFromToken(String token);
}
