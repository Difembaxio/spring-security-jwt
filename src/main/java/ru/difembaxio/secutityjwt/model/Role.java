package ru.difembaxio.secutityjwt.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    USER,
    ADMIN;
    public GrantedAuthority getGrantedAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }
}