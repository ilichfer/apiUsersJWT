package com.smartjob.mappers;

import com.smartjob.dto.PhoneDTO;
import com.smartjob.dto.RequestUserDTO;
import com.smartjob.dto.UserDTO;
import com.smartjob.model.Phone;
import com.smartjob.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = {UUID.class})

public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);


    PhoneDTO EntitytoPhoneDTO(Phone entity);

    List<PhoneDTO> listEntitytoPhoneDTO(List<Phone> entity);

    List<Phone> listDTOToListEntity(List<PhoneDTO> dto);
    Phone DtoToEntity(PhoneDTO dto);



}
