package com.airport.emergency.util;

import com.airport.emergency.exception.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 验证工具类
 */
public class ValidationUtil {
    
    // 邮箱正则表达式
    private static final Pattern EMAIL_PATTERN = 
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    
    // 手机号正则表达式
    private static final Pattern PHONE_PATTERN = 
            Pattern.compile("^1[3-9]\\d{9}$");
    
    // 用户名正则表达式（字母、数字、下划线，3-20个字符）
    private static final Pattern USERNAME_PATTERN = 
            Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    
    /**
     * 验证邮箱格式
     */
    public static boolean isValidEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * 验证手机号格式
     */
    public static boolean isValidPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }
    
    /**
     * 验证用户名格式
     */
    public static boolean isValidUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username).matches();
    }
    
    /**
     * 验证密码强度（至少8个字符，包含大小写字母和数字）
     */
    public static boolean isStrongPassword(String password) {
        if (!StringUtils.hasText(password) || password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = password.matches(".*[A-Z].*");
        boolean hasLowerCase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        return hasUpperCase && hasLowerCase && hasDigit;
    }
    
    /**
     * 验证字符串非空
     */
    public static void notEmpty(String value, String fieldName) {
        if (!StringUtils.hasText(value)) {
            throw new BadRequestException(fieldName, "cannot be empty");
        }
    }
    
    /**
     * 验证对象非空
     */
    public static void notNull(Object value, String fieldName) {
        if (value == null) {
            throw new BadRequestException(fieldName, "cannot be null");
        }
    }
    
    /**
     * 验证ID有效性
     */
    public static void validateId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new BadRequestException(fieldName, "must be a positive number");
        }
    }
}
