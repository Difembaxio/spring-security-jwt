package ru.difembaxio.secutityjwt.service;

import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserNameAccess(String token);
    String extractUserNameRefresh(String token);

    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValidAccess(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Claims extractAllClaims(String token,Key secret);

    <T> T extractClaimsAccess(String token, Function<Claims, T> claimsResolver);
    <T> T extractClaimsRefresh(String token, Function<Claims, T> claimsResolver);

}
