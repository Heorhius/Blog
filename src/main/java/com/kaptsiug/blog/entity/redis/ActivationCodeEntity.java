package com.kaptsiug.blog.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@RedisHash("Activation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCodeEntity {
    @Id
    @GeneratedValue
    private String id;
    private String activationCode;
    private LocalDateTime createdAt;

    {
        createdAt = LocalDateTime.now();
    }
}
