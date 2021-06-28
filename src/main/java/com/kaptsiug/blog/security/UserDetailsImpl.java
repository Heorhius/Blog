package com.kaptsiug.blog.security;

import com.kaptsiug.blog.entity.sql.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date createdAt;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl fromUserEntityToUserDetailsImpl(UserEntity userEntity) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.firstName = userEntity.getFirstName();
        userDetails.lastName = userEntity.getLastName();
        userDetails.email = userEntity.getEmail();
        userDetails.password = userEntity.getPassword();
        userDetails.createdAt = userEntity.getCreatedAt();
        userDetails.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE-USER"));
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
