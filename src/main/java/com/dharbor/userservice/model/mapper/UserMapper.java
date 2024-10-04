package com.dharbor.userservice.model.mapper;

import com.dharbor.userservice.api.request.UserRequest;
import com.dharbor.userservice.api.response.UserResponse;
import com.dharbor.userservice.model.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    User userRequestToUser(UserRequest userRequest);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    UserResponse userToUserResponse(User user);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "isDeleted", ignore = true)
//    void updateUserFromUserRequest(UserRequest userRequest, @org.mapstruct.MappingTarget User user);
}
