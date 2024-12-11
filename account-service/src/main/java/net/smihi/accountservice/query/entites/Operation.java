package net.smihi.accountservice.query.entites;

import jakarta.persistence.*;
import lombok.*;
import net.smihi.accountservice.enums.OperationType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
@Entity @Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(updatable = false)
    @CreatedDate
    private LocalDate createAt;
    private double amount;
    private OperationType type;
    @ManyToOne
    private Account account;
    private String description;
}
