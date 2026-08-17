package com.airport.emergency.repository;

import com.airport.emergency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * 用户Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    
    List<User> findByRole(User.UserRole role);
    
    List<User> findByEnabled(Boolean enabled);
    
    List<User> findByDepartment(String department);
}
