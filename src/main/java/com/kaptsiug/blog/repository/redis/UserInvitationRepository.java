package com.kaptsiug.blog.repository.redis;

import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInvitationRepository extends CrudRepository<UserInvitation, String> {

    UserInvitation findByInvitationCode(String hashCode);

    boolean existsByEmail(String email);
}
