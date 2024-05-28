package ru.difembaxio.secutityjwt.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.difembaxio.secutityjwt.model.User;


public interface UserService {

  UserDetailsService userDetailsService();

  List<User> getAllUser();

  String getAuthorized(UserDetails userDetails);

  String getCurrentUserLogin(UserDetails userDetails);

}
