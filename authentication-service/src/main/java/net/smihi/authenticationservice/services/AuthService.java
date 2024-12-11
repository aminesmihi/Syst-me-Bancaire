package net.smihi.authenticationservice.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.smihi.authenticationservice.Models.AuthRequest;
import net.smihi.authenticationservice.Models.AuthResponseJwt;

public interface AuthService {
    AuthResponseJwt auth(AuthRequest request);
    AuthResponseJwt RefreshToken(HttpServletRequest request, HttpServletResponse response);
}
