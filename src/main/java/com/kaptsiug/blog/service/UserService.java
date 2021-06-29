package com.kaptsiug.blog.service;

import com.kaptsiug.blog.entity.redis.ActivationCodeEntity;
import com.kaptsiug.blog.entity.sql.UserEntity;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import com.kaptsiug.blog.mapper.UserMapper;
import com.kaptsiug.blog.repository.redis.ActivationCodeRepository;
import com.kaptsiug.blog.repository.redis.UserInvitationRepository;
import com.kaptsiug.blog.repository.sql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInvitationRepository invitationRepository;
    private final ActivationCodeRepository activationCodeRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void activate(String hash) {
        UserInvitation invitation = invitationRepository.findByInvitationCode(hash);
        if (invitation == null) {
            throw new IllegalArgumentException("User invitation not found");
        }

        UserEntity save = userRepository.save(userMapper.toUserEntity(invitation));

        // delete from queue
        invitationRepository.delete(invitation);
    }

    public String checkCode(String activationCode) {
        ActivationCodeEntity activationCodeEntity = activationCodeRepository.findFirstByActivationCode(activationCode);
        if (activationCodeEntity == null) {
            throw new IllegalArgumentException("Activation code doesn't exist");
        }
        String result = "";
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.isAfter(activationCodeEntity.getCreatedAt())) {
            result = "Activation code is valid";
        } else {
            result = "Activation code is expired";
        }
        return result;
    }
}
