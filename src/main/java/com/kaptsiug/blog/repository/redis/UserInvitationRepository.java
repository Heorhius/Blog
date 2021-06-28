package com.kaptsiug.blog.repository.redis;

import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.springframework.data.repository.CrudRepository;

public interface UserInvitationRepository extends CrudRepository<UserInvitation, String> {

    UserInvitation findByInvitationCode(String hashCode);

    boolean existsByEmail(String email);
}
