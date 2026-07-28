package com.travel.travelhospitality.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.RefreshToken;
import com.travel.travelhospitality.entity.User;
import com.travel.travelhospitality.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    // Create Refresh Token
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(86400));

        return refreshTokenRepository.save(refreshToken);
    }

    // Find Refresh Token
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElse(null);
    }

    // Check Expiry
    public boolean isExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(Instant.now());
    }

    // Delete Refresh Token
    public void deleteToken(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }
}