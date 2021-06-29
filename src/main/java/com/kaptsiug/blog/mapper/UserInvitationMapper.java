package com.kaptsiug.blog.mapper;

import com.kaptsiug.blog.dto.UserForm;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserInvitationMapper {

    UserInvitationMapper INSTANCE = Mappers.getMapper(UserInvitationMapper.class);
    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "invitationCode", source = "hashCode")
    UserInvitation toUserInvitation(UserForm source, String hashCode);
}
