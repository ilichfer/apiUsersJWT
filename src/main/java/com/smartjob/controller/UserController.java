package com.smartjob.controller;

import com.smartjob.dto.RequestUserDTO;
import com.smartjob.dto.UserDTO;
import com.smartjob.dto.UserSessionDTO;
import com.smartjob.service.IUserService;
import com.smartjob.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "User", description = " smartjobs API")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "save users ", description = "save users in the system")
    @PostMapping("/saveUser")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDTO userDTO) {
        UserSessionDTO sessionDTO;
        ApiResponse response;
        try {
            sessionDTO = userService.saveUser(userDTO);

        } catch (Exception e) {
            response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),  e.getMessage(),null);
            return ResponseEntity.badRequest().body(response);
        }
        response = new ApiResponse<>(HttpStatus.CREATED.value(),"Created" ,sessionDTO);
        return ResponseEntity.ok().body(response);
    }



}
