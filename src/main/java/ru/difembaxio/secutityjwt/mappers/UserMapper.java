package ru.difembaxio.secutityjwt.mappers;

import ru.difembaxio.secutityjwt.dto.userDto.RegistrationDto;
import ru.difembaxio.secutityjwt.dto.userDto.UserDto;
import ru.difembaxio.secutityjwt.model.User;

public class UserMapper {


       public static RegistrationDto toUserDto(User user) {
        return RegistrationDto.builder()
            .login(user.getLogin())
            .build();
    }
    public static User toUser(UserDto userDto) {
        return User.builder()
            .login(userDto.getLogin())
            .password(userDto.getPassword())
            .build();
    }
}
