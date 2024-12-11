package net.smihi.authenticationservice.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.smihi.authenticationservice.Repository.TokenRepository;
import net.smihi.authenticationservice.entities.TokenApp;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogHandler implements LogoutHandler {
    private final JwtUtils jwtUtils;
    private final TokenRepository tokenRepository;
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    )
    {
        String jwt=jwtUtils.getJwtFromHeader(request);
        if(jwt!=null){
            TokenApp accessToken = tokenRepository.findTokenAppByRefreshToken(jwt);
            accessToken.setRevoke(true);
            tokenRepository.save(accessToken);
        }




    }
}
