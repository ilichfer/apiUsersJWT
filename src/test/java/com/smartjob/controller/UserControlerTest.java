package com.smartjob.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartjob.dto.UserDTO;
import com.smartjob.dto.UserSessionDTO;
import com.smartjob.service.IUserService;
import com.smartjob.util.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@WebMvcTest(UserControlerTest.class)
public class UserControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void saveUser_ReturnsUserSessionDTO() {
        UserDTO request = new UserDTO();
        UserSessionDTO sessionDTO = new UserSessionDTO();
        sessionDTO.setToken("sampleToken");
        sessionDTO.setSessionId("a9ad44d5-3c57-45ee-b982-891245b34c85");

        when(userService.saveUser(Mockito.any())).thenReturn(sessionDTO);

        ResponseEntity<ApiResponse> response = userController.saveUser(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sessionDTO, response.getBody());
        verify(userService, times(1)).saveUser(request);
    }


    @Test
    void saveUser_Returns500OnException() {
        UserDTO request = new UserDTO();
        when(userService.saveUser(request)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<ApiResponse> response = userController.saveUser(request);

        assertEquals(500, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
