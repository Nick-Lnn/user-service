package com.dharbor.userservice.model.mapper;

import com.dharbor.userservice.api.request.UserRequest;
import com.dharbor.userservice.api.response.UserResponse;
import com.dharbor.userservice.model.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    User userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(User user);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "isDeleted", ignore = true)
//    void updateUserFromUserRequest(UserRequest userRequest, @org.mapstruct.MappingTarget User user);
}
