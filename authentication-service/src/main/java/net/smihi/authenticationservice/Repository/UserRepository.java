package net.smihi.authenticationservice.Repository;

import net.smihi.authenticationservice.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApp,Integer> {
    UserDetails findByUsername(String username);
}
