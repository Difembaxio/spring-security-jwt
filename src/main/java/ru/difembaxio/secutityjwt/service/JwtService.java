package ru.difembaxio.secutityjwt.service;

import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Claims extractAllClaims(String token);

    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);
}
