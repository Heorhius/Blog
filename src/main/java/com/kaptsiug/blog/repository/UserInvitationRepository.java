package com.kaptsiug.blog.repository;

import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.springframework.data.repository.CrudRepository;

public interface UserInvitationRepository extends CrudRepository<UserInvitation, String> {

    UserInvitation findByHashCode(String hashCode);

    boolean existsUserInvitationByEmail(String email);
}
