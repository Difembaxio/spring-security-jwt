package ru.difembaxio.secutityjwt.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import ru.difembaxio.secutityjwt.dto.userDto.UserDto;


public interface UserService {

    List<UserDto> getAllUser();

    String getAuthorized(UserDetails userDetails);

    String getCurrentUserLogin(UserDetails userDetails);

}
