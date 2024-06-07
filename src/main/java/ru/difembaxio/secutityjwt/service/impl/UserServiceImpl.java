package ru.difembaxio.secutityjwt.service.impl;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.difembaxio.secutityjwt.dto.userDto.UserDto;
import ru.difembaxio.secutityjwt.mappers.UserMapper;
import ru.difembaxio.secutityjwt.repository.UserRepository;
import ru.difembaxio.secutityjwt.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
            .map(UserMapper::toUserDto)
            .collect(Collectors.toList());
    }

    @Override
    public String getAuthorized(UserDetails userDetails) {
        if (userDetails != null && userDetails.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("USER"))) {
            return "Авторизован";
        }
        throw new AccessDeniedException("Пользователь не авторизован");
    }

    @Override
    public String getCurrentUserLogin(UserDetails userDetails) {
        if (userDetails != null) {
            return userDetails.getUsername();
        }
        throw new AuthenticationException("Пользователь не авторизован") {
        };
    }
}