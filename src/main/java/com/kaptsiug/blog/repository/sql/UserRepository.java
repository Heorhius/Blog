package com.kaptsiug.blog.repository.sql;

import com.kaptsiug.blog.entity.sql.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}
