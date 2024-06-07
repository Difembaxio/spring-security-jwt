package ru.difembaxio.secutityjwt.mappers;

import ru.difembaxio.secutityjwt.dto.userDto.UserDto;
import ru.difembaxio.secutityjwt.model.User;

public class UserMapper {


    public static UserDto toUserDto(User user) {
        return UserDto.builder()
            .login(user.getLogin())
            .password(user.getPassword())
            .build();
    }

    public static User toUser(UserDto userDto) {
        return User.builder()
            .login(userDto.getLogin())
            .password(userDto.getPassword())
            .build();
    }
}
