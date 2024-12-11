package net.smihi.authenticationservice.webs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.smihi.authenticationservice.Models.AuthRequest;
import net.smihi.authenticationservice.Models.AuthResponseJwt;
import net.smihi.authenticationservice.services.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public AuthResponseJwt authenticated(@RequestBody AuthRequest request){
        log.info("********");
        log.info(request.getUsername());
        return authService.auth(request);
    }
    @PostMapping("/refreshtoken")
    public AuthResponseJwt refreshToken(HttpServletRequest request, HttpServletResponse response){
         return authService.RefreshToken(request,response);
    }
    @GetMapping("/test1")
    public String test1(){
        return "test 1  correct test";
    }
    @GetMapping("/test2")
    public String test2(){
        return "test 2  correct test";
    }

}
