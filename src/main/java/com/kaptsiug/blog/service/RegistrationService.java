package com.kaptsiug.blog.service;

import com.kaptsiug.blog.dto.Token;
import com.kaptsiug.blog.dto.UserEnter;
import com.kaptsiug.blog.dto.UserForm;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import com.kaptsiug.blog.entity.sql.UserEntity;
import com.kaptsiug.blog.generator.Generator;
import com.kaptsiug.blog.mapper.UserInvitationMapper;
import com.kaptsiug.blog.repository.redis.UserInvitationRepository;
import com.kaptsiug.blog.repository.sql.UserRepository;
import com.kaptsiug.blog.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final UserInvitationRepository invitationRepository;
    private final UserInvitationMapper invitationMapper;
    private final PasswordEncoder passwordEncoder;
    private final Generator<String> generator;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

    public void register(UserForm userForm) {
        String email = userForm.getEmail();
        boolean exist = userRepository.existsByEmail(email);
        if (exist) {
            throw new IllegalArgumentException("User already exists!");
        }
        List<UserInvitation> result = new ArrayList<>();
        invitationRepository.findAll().forEach(result::add);
        Optional<UserInvitation> first = result.stream().filter(o -> o.getEmail().equals(email)).findFirst();
        if (first.isPresent()) {
            throw new IllegalArgumentException("Invitation code already exists!");
        }

        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        String hashCode = generator.generate(email);
        UserInvitation toSave = invitationMapper.toUserInvitation(userForm, hashCode);
        UserInvitation saved = invitationRepository.save(toSave);
        emailService.sendSimpleMessage(saved);
    }

    public Token enter(UserEnter userEnter) {
        List<UserInvitation> result = new ArrayList<>();
        invitationRepository.findAll().forEach(result::add);
        Optional<UserInvitation> invitation = result.stream()
                .filter(o -> o.getEmail().equals(userEnter.getEmail()))
                .findFirst();
        if (invitation.isPresent()) {
            throw new IllegalArgumentException("Code is not confirmed");
        }

        UserEntity userEntity = userRepository.findByEmail(userEnter.getEmail());
        if (userEntity == null) {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        boolean matches = passwordEncoder.matches(userEnter.getPassword(), userEntity.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("");
        }
        return new Token(jwtProvider.generateToken(userEnter.getEmail()));
    }
}
