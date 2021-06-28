package com.kaptsiug.blog.mapper;

import com.kaptsiug.blog.dto.UserForm;
import com.kaptsiug.blog.entity.redis.UserInvitation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInvitationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "invitationCode", source = "hashCode")
    UserInvitation toUserInvitation(UserForm source, String hashCode);
}
