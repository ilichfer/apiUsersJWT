package com.smartjob.mappers;

import com.smartjob.dto.RequestUserDTO;
import com.smartjob.dto.UserDTO;
import com.smartjob.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = {UUID.class})

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    UserDTO EntitytoUserDTO(User entity);

    List<UserDTO> listEntitytoUserDTO(List<User> entity);

    List<User> listDTOToListEntity(List<UserDTO> dto);

    @Mapping(target = "userID", expression = "java(UUID.randomUUID().toString())")
    User DtoToEntity(UserDTO dto);

    @Mapping(target = "userID", expression = "java(UUID.randomUUID().toString())")
    User requestDtoToEntity(RequestUserDTO dto);

}
