package net.smihi.authenticationservice.tuns;

import lombok.RequiredArgsConstructor;
import net.smihi.authenticationservice.Repository.RoleRepository;
import net.smihi.authenticationservice.Repository.UserRepository;
import net.smihi.authenticationservice.entities.RoleApp;
import net.smihi.authenticationservice.entities.UserApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@RequiredArgsConstructor
public class LineRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        RoleApp role=RoleApp.builder()
                .name("USER")
                .description("Descr1")
                .build();
        roleRepository.save(role);
        UserApp adminUser = UserApp.builder()
                .firstname("amine")
                .lastname("smihi")
                .cin("USER123")
                .email("user@example.com")
                .username("user")
                .password(passwordEncoder.encode("1234"))
                .enable(true)
                .roleApps(List.of(role))
                .build();
        userRepository.saveAll(List.of(adminUser));
        System.out.println("Users and roles initialized");
    }
}
