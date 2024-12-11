package net.smihi.customerservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.smihi.customerservice.enums.Adress;

@Builder @Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;


}
