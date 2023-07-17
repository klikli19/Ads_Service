package ru.skypro.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ImageService service;
    @Mock
    UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    String USERNAME = "login@ya.ru";
    String PASSWORD = "password";
    String ENCODER_PASSWORD = "passwordTwo";

    String FIRSTNAME = "Vasya";
    String LASTNAME= "Ivanov";
    String PHONE = "+79201475623";

    @Test
    public void updateUserTest() {
        User user = new User();
        UserDTO userDTO = new UserDTO();

        user.setFirstName(FIRSTNAME);
        user.setLastName(LASTNAME);
        user.setPhone(PHONE);

        userDTO.setFirstName(FIRSTNAME);
        userDTO.setLastName(LASTNAME);
        userDTO.setPhone(PHONE);

        when(userRepository.findByEmailIgnoreCase(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);

        UserDTO userDTO1 = userService.updateUser(new UserDTO(),authentication);

        assertEquals(userDTO, userDTO1);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getAuthorizedUserTest() {
        User user = new User();
        user.setEmail(USERNAME);

        when(userRepository.findByEmailIgnoreCase(USERNAME)).thenReturn(Optional.of(user));
        when(authentication.getName()).thenReturn(USERNAME);

        User test  = userService.getAuthorizedUser(authentication);

        assertNotNull(test);
        assertEquals(USERNAME, test.getEmail());
        verify(userRepository).findByEmailIgnoreCase(USERNAME);
    }


    @Test
    public void getAuthorizedUserDtoTest() {
        User user = new User();
        user.setEmail(USERNAME);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(USERNAME);
        when(authentication.getName()).thenReturn(USERNAME);
//        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);
        UserDTO test = userService.getAuthorizedUserDto(authentication);
        assertEquals(userDTO, test);
        verify(userRepository).getUserByEmail(USERNAME);
        verify(userMapper).toDTO(user);

    }
    @Test
    public void changePasswordTest() {
        User user = new User();
        user.setPassword(passwordEncoder.encode(PASSWORD));
        UserDTO userDTO = new UserDTO();

        when(userRepository.findByEmailIgnoreCase(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);

        userService.changePassword(new NewPasswordDTO(),authentication);

        assertEquals(user.getPassword(), passwordEncoder.encode(PASSWORD));
        verify(userRepository, times(1)).save(any(User.class));
    }
}
