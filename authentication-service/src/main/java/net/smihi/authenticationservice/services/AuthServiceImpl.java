package net.smihi.authenticationservice.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.smihi.authenticationservice.Models.AuthRequest;
import net.smihi.authenticationservice.Models.AuthResponseJwt;
import net.smihi.authenticationservice.Repository.TokenRepository;
import net.smihi.authenticationservice.Repository.UserRepository;
import net.smihi.authenticationservice.entities.TokenApp;
import net.smihi.authenticationservice.entities.UserApp;
import net.smihi.authenticationservice.security.AuthManager;
import net.smihi.authenticationservice.security.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthManager authManager;
    private final TokenRepository tokenRepository;

    @Override
    public AuthResponseJwt auth(AuthRequest request) {
        log.info("********");
        log.info(request.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        authManager.authenticate(authenticationToken);
        var user= (UserApp)userRepository.findByUsername(request.getUsername());
        String accessToken=jwtUtils.generateJwtFromUserDetails(user);
        var refreshToken=jwtUtils.refreshToken(user);
        var Token= TokenApp.builder()
                .refreshToken(refreshToken)
                .revoke(false)
                .expiration(false)
                .user(user)
                .build();
        revokeAllTokenByUser(user);
        tokenRepository.save(Token);
        return AuthResponseJwt.builder()
                .access_token(accessToken)
                .refresh_token(refreshToken)
                .build();
    }

    @Override
    public AuthResponseJwt RefreshToken(HttpServletRequest request, HttpServletResponse response) {
        log.info("Ui dkhalt l service refresh token");
        String refreshToken =jwtUtils.getJwtFromHeader(request);
        String username=jwtUtils.getUsernameFromJwtToken(refreshToken);
        if(username!=null && jwtUtils.validateJwtToken(refreshToken)){
            UserApp user=(UserApp)userRepository.findByUsername(username);
            var generateAccessToken=jwtUtils.generateJwtFromUserDetails(user);
            var generateRefreshToken=jwtUtils.refreshToken(user);
            revokeAllTokenByUser(user);
            TokenApp token=TokenApp.builder()
                        .user(user)
                        .refreshToken(refreshToken)
                        .revoke(false)
                        .expiration(false)
                        .build();
                tokenRepository.save(token);
                return AuthResponseJwt
                        .builder()
                        .access_token(generateAccessToken)
                        .refresh_token(refreshToken)
                        .build();
        }
        return new AuthResponseJwt();

    }

    public void revokeAllTokenByUser(UserApp user){
        var tokens=tokenRepository.findAllTokenValidByUser(user.getId());
        tokens.forEach(t->{
            t.setRevoke(true);
            t.setExpiration(true);
        });
        tokenRepository.saveAll(tokens);
    }
}
