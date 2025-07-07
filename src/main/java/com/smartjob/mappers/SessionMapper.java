package com.smartjob.mappers;

import com.smartjob.dto.RequestUserDTO;
import com.smartjob.dto.UserDTO;
import com.smartjob.dto.UserSessionDTO;
import com.smartjob.model.User;
import com.smartjob.model.UserSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = {UUID.class})

public interface SessionMapper {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);


    UserSessionDTO EntitytoUserSessionDTO(UserSession entity);

    List<UserSessionDTO> listEntitytoUserSessionDTO(List<UserSession> entity);

    List<UserSession> listDTOToListEntity(List<UserSessionDTO> dto);
    UserSession DtoToEntity(UserSessionDTO dto);

}
