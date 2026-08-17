package com.airport.emergency.service;

import com.airport.emergency.dto.UserDTO;
import com.airport.emergency.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 用户注册
     */
    UserDTO register(String username, String password, String fullName, User.UserRole role);

    /**
     * 获取用户
     */
    Optional<UserDTO> getUserById(Long id);

    /**
     * 根据用户名获取用户
     */
    Optional<UserDTO> getUserByUsername(String username);

    /**
     * 获取所有用户
     */
    List<UserDTO> getAllUsers();

    /**
     * 获取特定角色的用户
     */
    List<UserDTO> getUsersByRole(User.UserRole role);

    /**
     * 更新用户信息
     */
    UserDTO updateUser(Long id, UserDTO userDTO);

    /**
     * 修改密码
     */
    void changePassword(Long id, String oldPassword, String newPassword);

    /**
     * 启用/禁用用户
     */
    void toggleUserStatus(Long id, Boolean enabled);

    /**
     * 删除用户
     */
    void deleteUser(Long id);
}
