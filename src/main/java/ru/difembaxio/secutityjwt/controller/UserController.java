package ru.difembaxio.secutityjwt.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.difembaxio.secutityjwt.dto.userDto.RegistrationDto;
import ru.difembaxio.secutityjwt.dto.userDto.UserDto;
import ru.difembaxio.secutityjwt.service.AuthenticationService;
import ru.difembaxio.secutityjwt.service.UserService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;


    @PostMapping("/user/register")
    public RegistrationDto registerUser(@RequestBody UserDto userDto) {
        return authenticationService.registerUser(userDto);
    }

    @PostMapping("/user/register/admin")
    public RegistrationDto registerAdmin(@RequestBody UserDto userDto) {
        return authenticationService.registerAdmin(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public List<RegistrationDto> getAllUsers() {
        return userService.getAllUser();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/test/useronly-access")
    public String getAuthorized(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getAuthorized(userDetails);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String getCurrentUserLogin(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getCurrentUserLogin(userDetails);
    }
}
