package com.kaptsiug.blog.security;

import com.kaptsiug.blog.entity.UserEntity;
import com.kaptsiug.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) {
            throw new UsernameNotFoundException("User wasn't found. Check your input email");
        }

        return UserDetailsImpl.fromUserEntityToUserDetailsImpl(userEntity);
    }

}
