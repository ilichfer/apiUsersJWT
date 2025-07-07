package com.smartjob.service;

import com.smartjob.dto.UserDTO;
import com.smartjob.dto.UserSessionDTO;


public interface IUserService {
    UserSessionDTO saveUser(UserDTO userDTO);
}
