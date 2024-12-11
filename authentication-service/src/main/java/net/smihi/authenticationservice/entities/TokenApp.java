package net.smihi.authenticationservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.smihi.authenticationservice.enums.TypeToken;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenApp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TypeToken type;
    private String refreshToken;
    private Boolean revoke;
    private Boolean expiration;
    @ManyToOne
    @JoinColumn(name = "user_app_id", nullable = false)
    private UserApp user;
}
