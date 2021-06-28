package com.kaptsiug.blog.service;

import com.kaptsiug.blog.dto.UserEnter;
import com.kaptsiug.blog.dto.UserForm;
import com.kaptsiug.blog.entity.UserEntity;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import com.kaptsiug.blog.generator.Generator;
import com.kaptsiug.blog.mapper.UserInvitationMapper;
import com.kaptsiug.blog.repository.UserInvitationRepository;
import com.kaptsiug.blog.repository.UserRepository;
import com.kaptsiug.blog.security.JwtFilter;
import com.kaptsiug.blog.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final UserInvitationRepository invitationRepository;
    private final UserInvitationMapper invitationMapper;
    private final PasswordEncoder passwordEncoder;
    private final Generator<String> generator;
    private final EmailService emailService;
    private final JwtProvider JwtProvider;

    public void register(UserForm userForm) {
        String email = userForm.getEmail();
        boolean exist = userRepository.existsByEmail(email);
        if (exist) {
            throw new IllegalArgumentException("User already exists!");
        }

        exist = invitationRepository.existsUserInvitationByEmail(email);
        if (exist) {
            throw new IllegalArgumentException("Invitation code already exists!");
        }

        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        String hashCode = generator.generate(email);
        UserInvitation toSave = invitationMapper.toUserInvitation(userForm, hashCode);
        UserInvitation saved = invitationRepository.save(toSave);
        // TODO make queue
        emailService.sendSimpleMessage(saved);
    }

    public String enter(UserEnter userEnter) {
        boolean exist = invitationRepository.existsUserInvitationByEmail(userEnter.getEmail());
        if (exist) {
            throw new IllegalArgumentException("Link is not confirmed");
        }
        UserEntity userEntity = userRepository.findByEmail(userEnter.getEmail());
        if (userEntity == null) {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        boolean matches = passwordEncoder.matches(userEnter.getPassword(), userEntity.getPassword());
        if(!matches) {
            throw new IllegalArgumentException("");
        }
        return JwtProvider.generateToken(userEnter.getEmail());
    }
}
