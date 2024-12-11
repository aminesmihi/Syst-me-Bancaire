package net.smihi.authenticationservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtUtils {

    @Value("${spring.app.jwtSecret}")
    private  String jwtSecret;
    @Value("${spring.app.expiration}")
    private long Expiration;
    @Value("${spring.app.refreshExpiration}")
    private long refreshExpiration;

    public String getJwtFromHeader(HttpServletRequest request){
        String jwt=request.getHeader("Authorization");
        if(jwt!=null && jwt.startsWith("Bearer ")){
            return jwt.substring(7);
        }
        return "";
    }
    public String generateJwtFromUserDetails(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+Expiration))
                .signWith(key())
                .claim("token_type","access_token")
                .compact();
    }
    public String refreshToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+refreshExpiration))
                .signWith(key())
                .claim("token_type","refresh_token")
                .compact();
    }
    public Claims getClaimsFromToken(String token){
        return Jwts.parser().verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    };
    public String getUsernameFromJwtToken(String token){
        Claims claims=getClaimsFromToken(token);
        return claims.getSubject();
    }
    public String getTypeByToken(String token){
        Claims claims=getClaimsFromToken(token);
        return (String)claims.get("token_type");
    }
    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public Boolean validateJwtToken(String token) {
        try {
            // Parser et vérifier le token avec la clé secrète
            var jwt = Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(token);

            // Vérifier que le JWT n'est pas null et que la date d'expiration est valide
            if (jwt != null && jwt.getPayload().getExpiration() != null) {
                // Comparer la date d'expiration avec la date actuelle
                if (jwt.getPayload().getExpiration().after(new Date())) {
                    return true; // Le token est valide et non expiré
                } else {
                    return false; // Le token est expiré
                }
            } else {
                return false; // Le token est invalide ou n'a pas de date d'expiration
            }

        } catch (Exception e) {
            // Capturer toute exception (problème avec le token, parsing, etc.)
            e.printStackTrace(); // Facultatif : log pour vérifier l'exception exacte
            return false; // Retourner false en cas d'erreur
        }
    }

}
