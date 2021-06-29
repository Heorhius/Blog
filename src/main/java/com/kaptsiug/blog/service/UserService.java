package com.kaptsiug.blog.service;

import com.kaptsiug.blog.dto.CheckCode;
import com.kaptsiug.blog.entity.redis.ActivationCodeEntity;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import com.kaptsiug.blog.entity.sql.UserEntity;
import com.kaptsiug.blog.mapper.UserMapper;
import com.kaptsiug.blog.repository.redis.ActivationCodeRepository;
import com.kaptsiug.blog.repository.redis.UserInvitationRepository;
import com.kaptsiug.blog.repository.sql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInvitationRepository invitationRepository;
    private final ActivationCodeRepository activationCodeRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void activate(String hash) {
        List<UserInvitation> result = new ArrayList<>();
        invitationRepository.findAll().forEach(result::add);
        UserInvitation invitation = result.stream()
                .filter(o -> o.getInvitationCode().equals(hash))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User invitation not found"));

        UserEntity save = userRepository.save(userMapper.toUserEntity(invitation));
        invitationRepository.delete(invitation);
    }

    public CheckCode checkCode(String activationCode) {
        Optional<ActivationCodeEntity> activationCodeEntity = activationCodeRepository.findById(activationCode);
        if (activationCodeEntity.isEmpty()) {
            throw new IllegalArgumentException("Activation code doesn't exist");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        return new CheckCode(localDateTime.isAfter(activationCodeEntity.get().getCreatedAt()));
    }
}
