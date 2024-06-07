package ru.difembaxio.secutityjwt.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface CustomUserDetailsService {

    UserDetailsService userDetailsService();

}
