package com.kaptsiug.blog.service;

import com.kaptsiug.blog.entity.sql.UserEntity;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import com.kaptsiug.blog.mapper.UserMapper;
import com.kaptsiug.blog.repository.redis.UserInvitationRepository;
import com.kaptsiug.blog.repository.sql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInvitationRepository invitationRepository;
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
}
