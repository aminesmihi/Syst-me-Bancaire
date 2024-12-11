package net.smihi.authenticationservice.Repository;

import net.smihi.authenticationservice.entities.TokenApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<TokenApp,Integer>
{
    @Query("select T from TokenApp T where T.user.id = :userId and T.revoke=false and T.expiration=false ")
    List<TokenApp> findAllTokenValidByUser(Integer userId);
    TokenApp findTokenAppByRefreshToken(String refreshToken);
}
