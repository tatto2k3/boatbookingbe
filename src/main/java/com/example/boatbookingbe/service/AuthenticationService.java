package com.example.boatbookingbe.service;


import com.example.boatbookingbe.dto.JwtAuthenticationResponse;
import com.example.boatbookingbe.dto.RefreshTokenRequest;
import com.example.boatbookingbe.dto.SignInRequest;
import com.example.boatbookingbe.dto.SignUpRequest;
import com.example.boatbookingbe.entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
