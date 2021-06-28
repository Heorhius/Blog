package com.kaptsiug.blog.service;

import com.kaptsiug.blog.entity.UserEntity;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import com.kaptsiug.blog.mapper.UserMapper;
import com.kaptsiug.blog.repository.UserInvitationRepository;
import com.kaptsiug.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void activate(String hash) {
        UserInvitation invitation = invitationRepository.findByHashCode(hash);
        if (invitation == null) {
            throw new IllegalArgumentException("User invitation not found");
        }

        UserEntity save = userRepository.save(userMapper.toUser(invitation));

        // delete from queue
        invitationRepository.delete(invitation);
    }
}
