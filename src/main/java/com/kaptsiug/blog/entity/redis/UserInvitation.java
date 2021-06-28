package com.kaptsiug.blog.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@RedisHash("Invitation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInvitation implements Serializable {
    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String invitationCode;
}
