package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.constant.Role;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.AuthService;

/**
 * Service AuthServiceImpl
 * Service used to authentication and registration user
 *
 * @author Kilikova Anna
 * @see MyUserDetailsService
 * @see PasswordEncoder
 * @see UserRepository
 * @see UserMapper
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final MyUserDetailsService manager;

  private final PasswordEncoder encoder;

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  /**
   * The method used to user authentication
   *
   * @param userName user login
   * @param password user password
   * @return authorized user
   */
  @Override
  public boolean login(String userName, String password) {
    log.info("login: " + userName + " password: " + password);
    UserDetails userDetails = manager.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }


    /**
     * The method used to user registration
     *
     * @param registerReq registration
     * @param role        role
     * @return registered user
     */
    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (userRepository.findByEmailIgnoreCase(registerReq.getUsername()).isPresent()) {
            return false;
        }
        User regUser = userMapper.toEntity(registerReq);
        regUser.setRole(role);
        regUser.setPassword(encoder.encode(regUser.getPassword()));
        userRepository.save(regUser);
        return true;
    }

}