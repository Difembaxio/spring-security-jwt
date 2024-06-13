package ru.difembaxio.secutityjwt.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import ru.difembaxio.secutityjwt.service.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey accessTokenSecretKey;
    private final SecretKey refreshTokenSecretKey;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;


    public JwtServiceImpl(
        @Value("${application.security.jwt.secret-key.access}") String accessTokenSecretKey,
        @Value("${application.security.jwt.secret-key.refresh}") String refreshTokenSecretKey
    ) {
        this.accessTokenSecretKey = Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(accessTokenSecretKey));
        this.refreshTokenSecretKey = Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(refreshTokenSecretKey));
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(issueDate)
            .setExpiration(expiredDate)
            .signWith(accessTokenSecretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(issueDate)
            .setExpiration(expiredDate)
            .signWith(refreshTokenSecretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public String extractUserNameAccess(String token) {
        return extractClaimsAccess(token, Claims::getSubject);
    }

    @Override
    public String extractUserNameRefresh(String token) {
        return extractClaimsRefresh(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaimsAccess(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, accessTokenSecretKey);
        return claimsResolver.apply(claims);
    }

    public <T> T extractClaimsRefresh(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, refreshTokenSecretKey);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token, Key secret) {
        return Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    @Override
    public boolean isTokenValidAccess(String token, UserDetails userDetails) {
        final String userLogin = extractUserNameAccess(token);
        return userLogin.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractClaimsAccess(token, Claims::getExpiration).before(new Date());
    }
}