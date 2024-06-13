package ru.difembaxio.secutityjwt.service;

import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    String generateAccessToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenExpired(String token);

    Claims extractAllClaims(String token,Key secret);

    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);
}
