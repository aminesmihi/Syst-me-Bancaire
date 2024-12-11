package net.smihi.authenticationservice.Repository;

import net.smihi.authenticationservice.entities.RoleApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleApp,Integer> {
}
