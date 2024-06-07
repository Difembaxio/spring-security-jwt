package ru.difembaxio.secutityjwt.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.difembaxio.secutityjwt.repository.UserRepository;
import ru.difembaxio.secutityjwt.service.CustomUserDetailsService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUserByLogin(username)
            .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

}
