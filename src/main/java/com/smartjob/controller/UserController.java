package com.smartjob.controller;

import com.smartjob.dto.UserDTO;
import com.smartjob.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {



    @PostMapping("/saveUser")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(null);
    }



}
