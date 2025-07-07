package com.smartjob.service;

import com.smartjob.dto.PhoneDTO;
import com.smartjob.dto.UserDTO;
import com.smartjob.dto.UserSessionDTO;
import com.smartjob.mappers.PhoneMapper;
import com.smartjob.mappers.SessionMapper;
import com.smartjob.model.Phone;
import com.smartjob.model.User;
import com.smartjob.repository.PhoneRepository;
import com.smartjob.repository.SessionRepository;
import com.smartjob.repository.UserRepository;
import com.smartjob.service.implementation.JwtService;
import com.smartjob.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = "app.regex.email=^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PhoneMapper phoneMapper;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SessionMapper sessionMapper;


    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() throws IllegalAccessException, NoSuchFieldException {
        MockitoAnnotations.openMocks(this);
        // Inyecta el regex manualmente
        java.lang.reflect.Field regexField = UserServiceImpl.class.getDeclaredField("usernameRegex");
        regexField.setAccessible(true);
        regexField.set(userService, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        java.lang.reflect.Field phoneMapperField = UserServiceImpl.class.getDeclaredField("phoneMapper");
        phoneMapperField.setAccessible(true);
        phoneMapperField.set(userService, phoneMapper);

        // Inyecta el mock de phoneRepository
        java.lang.reflect.Field phoneRepositoryField = UserServiceImpl.class.getDeclaredField("phoneRepository");
        phoneRepositoryField.setAccessible(true);
        phoneRepositoryField.set(userService, phoneRepository);

        java.lang.reflect.Field sessionRepositoryField = UserServiceImpl.class.getDeclaredField("sessionRepository");
        sessionRepositoryField.setAccessible(true);
        sessionRepositoryField.set(userService, sessionRepository);

        // Inyecta el mock de sessionMapper
        java.lang.reflect.Field sessionMapperField = UserServiceImpl.class.getDeclaredField("sessionMapper");
        sessionMapperField.setAccessible(true);
        sessionMapperField.set(userService, sessionMapper);
    }

    @Test
    void validate_ValidEmail_ReturnsTrue() {
        assertTrue(userService.validate("test@mail.com"));
    }

    @Test
    void validate_InvalidEmail_ReturnsFalse() {
        assertFalse(userService.validate("testmail.com"));
    }

    @Test
    void saveUser_ReturnsUserSessionDTO() {

        PhoneDTO phone1 = new PhoneDTO();
        phone1.setNumbrer("123456789");
        phone1.setCityCode("1");
        phone1.setCountryCode("57");

        Phone phone2 = new Phone();
        phone2.setNumber("987654321");
        phone2.setCityCode("2");
        phone2.setCountryCode("34");

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@mail.com");
        userDTO.setName("Test User");
        userDTO.setPassword("123456");
        userDTO.setPhones(Arrays.asList(phone1));


        User user = new User();
        user.setUserID("1");
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhones(new ArrayList<>(Arrays.asList(phone2)));

        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(any(User.class))).thenReturn("token123");
        when(phoneMapper.listDTOToListEntity(any())).thenReturn(new ArrayList<>());
        when(phoneRepository.saveAll(any())).thenReturn(new ArrayList<>());
        when(sessionRepository.save(any())).thenReturn(new com.smartjob.model.UserSession());
        when(sessionMapper.EntitytoUserSessionDTO(any())).thenReturn(new UserSessionDTO());

        UserSessionDTO result = userService.saveUser(userDTO);


        UserSessionDTO userSessionDTO = new UserSessionDTO();
        userSessionDTO.setToken("token123");
        when(sessionMapper.EntitytoUserSessionDTO(any())).thenReturn(userSessionDTO);

        assertNotNull(result);

        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).generateToken(any(User.class));
    }
}

