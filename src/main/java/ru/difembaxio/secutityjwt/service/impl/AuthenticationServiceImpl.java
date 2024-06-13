package ru.difembaxio.secutityjwt.service.impl;


import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.difembaxio.secutityjwt.dto.tokenDto.JwtAuthenticationResponse;
import ru.difembaxio.secutityjwt.dto.tokenDto.RefreshTokenRequest;
import ru.difembaxio.secutityjwt.dto.auth.SignInRequest;
import ru.difembaxio.secutityjwt.dto.userDto.RegistrationDto;
import ru.difembaxio.secutityjwt.dto.userDto.UserDto;
import ru.difembaxio.secutityjwt.exception.AuthenticationException;
import ru.difembaxio.secutityjwt.exception.JwtTokenInvalidException;
import ru.difembaxio.secutityjwt.exception.UserAlreadyExistsException;
import ru.difembaxio.secutityjwt.mappers.UserMapper;
import ru.difembaxio.secutityjwt.model.Role;
import ru.difembaxio.secutityjwt.model.User;
import ru.difembaxio.secutityjwt.repository.UserRepository;
import ru.difembaxio.secutityjwt.service.AuthenticationService;
import ru.difembaxio.secutityjwt.service.JwtService;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;


    @Override
    public RegistrationDto registerUser(UserDto userDto) {
        return createUser(userDto, Role.USER);
    }

    @Override
    public RegistrationDto registerAdmin(UserDto userDto) {
        return createUser(userDto, Role.ADMIN);
    }

    private RegistrationDto createUser(UserDto userDto, Role role) {
        if (userRepository.findUserByLogin(userDto.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует");
        }
        User user = UserMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getLogin(), signInRequest.getPassword()));
        } catch (Exception ex) {
            throw new AuthenticationException("Ошибка аутентификации пользователь не найден "
                + "введен не верный логин или пароль");
        }
        var user = userRepository.findUserByLogin(signInRequest.getLogin()).orElseThrow();
        var jwt = jwtService.generateAccessToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(jwtRefreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        try {
            String login = jwtService.extractUserName(refreshTokenRequest.getRefreshToken());
            User user = userRepository.findUserByLogin(login).orElseThrow();
            if (!jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
                throw new JwtTokenInvalidException("Передан не валидный refreshToken ");
            } else {
                var jwt = jwtService.generateRefreshToken(user);
                JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
                jwtAuthenticationResponse.setAccessToken(jwt);
                jwtAuthenticationResponse.setRefreshToken(
                    refreshTokenRequest.getRefreshToken());
                return jwtAuthenticationResponse;
            }
        } catch (Exception e) {
            throw new JwtTokenInvalidException("Передан не валидный refreshToken");
        }
    }
}