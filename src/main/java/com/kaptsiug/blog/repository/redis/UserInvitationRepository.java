package com.kaptsiug.blog.repository.redis;

import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInvitationRepository extends CrudRepository<UserInvitation, String> {

    UserInvitation findByInvitationCode(String hashCode);


    List<UserInvitation> findAllByEmail(String email);

    boolean existsByEmail(String email);

    UserInvitation findByEmail(String email);

}
