package com.kaptsiug.blog.mapper;

import com.kaptsiug.blog.entity.UserEntity;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.mapstruct.Mapping;

public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toUser(UserInvitation source);
}
