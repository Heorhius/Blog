package com.kaptsiug.blog.mapper;

import com.kaptsiug.blog.entity.sql.UserEntity;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toUser(UserInvitation source);
}
