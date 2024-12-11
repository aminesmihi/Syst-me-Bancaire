package net.smihi.authenticationservice.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

 @Getter @NoArgsConstructor @AllArgsConstructor
 @Builder
public class AuthResponseJwt {
    private String access_token;
    private String refresh_token;
}
