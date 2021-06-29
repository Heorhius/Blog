package com.kaptsiug.blog.mapper;

import com.kaptsiug.blog.dto.User;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import com.kaptsiug.blog.entity.sql.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(target = "id", ignore = true)
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUserDto(UserEntity source);

    UserEntity toUserEntity(UserInvitation source);


}
