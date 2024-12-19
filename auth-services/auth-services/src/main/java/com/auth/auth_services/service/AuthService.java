package com.auth.auth_services.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.auth_services.entity.User;
import com.auth.auth_services.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

    private static final String SECRET_KEY = "6adb73bc29ee7e8a67dc97e984486a85630e146474157316991a04b39c876ce2";

    @Autowired
    private UserRepository userRepository;

    public String login(User user) {
        Optional<User> userFind = userRepository.findByUsername(user.getUsername());
        if (userFind.isPresent() && userFind.get().getPassword().equals(user.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
            return token;
        } else {
            return "Login failed";
        }
    }
    
}
