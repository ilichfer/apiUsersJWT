package com.smartjob.service.implementation;

import com.smartjob.dto.PhoneDTO;
import com.smartjob.dto.UserDTO;
import com.smartjob.dto.UserSessionDTO;
import com.smartjob.mappers.PhoneMapper;
import com.smartjob.mappers.SessionMapper;
import com.smartjob.mappers.UserMapper;
import com.smartjob.model.Phone;
import com.smartjob.model.User;
import com.smartjob.model.UserSession;
import com.smartjob.repository.PhoneRepository;
import com.smartjob.repository.SessionRepository;
import com.smartjob.repository.UserRepository;
import com.smartjob.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private PhoneRepository phoneRepository;
    private SessionRepository sessionRepository;

    private UserMapper userMapper;
    private PhoneMapper phoneMapper;
    private SessionMapper sessionMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper, SessionRepository sessionRepository
            ,SessionMapper sessionMapper,PhoneRepository phoneRepository,
                           PhoneMapper phoneMapper) {
        this.userRepository = userRepository;
        this.userMapper = UserMapper.INSTANCE;
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
    }






    @Transactional
    @Override
    public UserSessionDTO saveUser(UserDTO requestDTO) {
        UserSession sesion = new UserSession();
        try {
            validateEmail(requestDTO.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        User userSave = userMapper.DtoToEntity(requestDTO);
        userSave.getPhones().clear();
        User userSaved = userRepository.save(userSave);

        UserUpdatePhones(userSaved, requestDTO.getPhones());

        return saveSession(userSaved, sesion);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    private void validateEmail(String email) {

        if(!validate(email)){
            throw new RuntimeException("Error Email format");
        }

        Optional<User> exist = userRepository.findByEmail(email);
        if (exist.isEmpty() ) {
        }else {
            throw new RuntimeException("Email already exists");
        }
    }

    private UserSessionDTO saveSession( User userSaved, UserSession sesion) {
        UUID uuid = UUID.randomUUID();
        Date date = new Date();
        sesion.setSessionId(uuid.toString());
        sesion.setUser(userSaved);
        sesion.setCreated(date);
        sesion.setExpirationTime(10L);
        sesion.setLast_login(date);
        sesion.setModified(date);
        sesion.setToken("qwerty");
        sesion.setIsActive(true);
        return sessionMapper.EntitytoUserSessionDTO( sessionRepository.save(sesion));
    }

    private void UserUpdatePhones(User userSaved, List<PhoneDTO> phones) {
        if (phones != null && !phones.isEmpty()) {
            userSaved.getPhones().clear();
            List<Phone> phonesSave =  phoneMapper.listDTOToListEntity(phones);
            phonesSave.forEach(p -> p.setUser(userSaved));
            phoneRepository.saveAll(phonesSave);
        }
    }
}
