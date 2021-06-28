package com.kaptsiug.blog.repository;

import com.kaptsiug.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}
