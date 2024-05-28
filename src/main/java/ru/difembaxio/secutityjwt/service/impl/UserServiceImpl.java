package ru.difembaxio.secutityjwt.service.impl;



import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.difembaxio.secutityjwt.model.User;
import ru.difembaxio.secutityjwt.repository.UserRepository;
import ru.difembaxio.secutityjwt.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDetailsService userDetailsService() {
    return login -> userRepository.findUserByLogin(login)
        .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
  }

  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
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
    throw new AuthenticationException("Пользователь не авторизован") {};
  }
}
