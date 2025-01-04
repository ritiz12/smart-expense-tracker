package com.example.Expenses_Tracker.user.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name ;

    private String email;

    private String password ;

    public User(){
        super();
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    public void setPassword(final String password) {
        this.password = password;
    }

    public User(final Long userId, final String name, final String email, final String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
