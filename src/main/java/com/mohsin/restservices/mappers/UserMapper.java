package com.mohsin.restservices.mappers;

import com.mohsin.restservices.dtos.UserMsDto;
import com.mohsin.restservices.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // User to UserMsDto
    @Mappings({
        @Mapping(source = "email", target = "emailAddress"),
        @Mapping(source = "role", target = "roleName")
    })
    UserMsDto userToUserMsDto(User user);

    // List<User> to List<UserMsDto>
    List<UserMsDto> usersToUserMsDtoList(List<User> users);
}
