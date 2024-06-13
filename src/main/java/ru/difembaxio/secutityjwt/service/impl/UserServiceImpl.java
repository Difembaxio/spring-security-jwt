package ru.difembaxio.secutityjwt.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.difembaxio.secutityjwt.dto.userDto.RegistrationDto;
import ru.difembaxio.secutityjwt.dto.userDto.UserDto;
import ru.difembaxio.secutityjwt.mappers.UserMapper;
import ru.difembaxio.secutityjwt.repository.UserRepository;
import ru.difembaxio.secutityjwt.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<RegistrationDto> getAllUser() {
        return userRepository.findAll().stream()
            .map(UserMapper::toUserDto)
            .collect(Collectors.toList());
    }

    @Override
    public String getAuthorized(UserDetails userDetails) {
        var isAuthorized = userDetails.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("USER"));
        if (isAuthorized) {
            return "Пользователь " + userDetails.getUsername() + " авторизован";
        }
        throw new AccessDeniedException("Пользователь не авторизован");
    }

    @Override
    public String getCurrentUserLogin(UserDetails userDetails) {
        return Optional.ofNullable(userDetails)
            .map(UserDetails::getUsername)
            .orElseThrow(() -> new AuthenticationException("Пользователь не авторизован") {
            });
    }
}