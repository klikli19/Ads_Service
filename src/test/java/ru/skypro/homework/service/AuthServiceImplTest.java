package ru.skypro.homework.service;

import liquibase.pro.packaged.P;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.constant.Role;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserMapper mapper;
    @Mock
    private UserRepository repository;
    @Mock
    private MyUserDetailsService manager;
    @InjectMocks
    private AuthServiceImpl service;

    String USERNAME = "login@ya.ru";
    String PASSWORD = "password";
    String ENCODER_PASSWORD = "passwordTwo";

    @Test
    public void loginTest() {

        User user = new User();
        user.setEmail(USERNAME);
        user.setPassword(ENCODER_PASSWORD);
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>());
        when(manager.loadUserByUsername(USERNAME)).thenReturn(userDetails);
        when(encoder.matches(PASSWORD,ENCODER_PASSWORD)).thenReturn(true);

        Assertions.assertTrue(service.login(USERNAME, PASSWORD));

    }

    @Test
    public void registerTest() {
    }

}
