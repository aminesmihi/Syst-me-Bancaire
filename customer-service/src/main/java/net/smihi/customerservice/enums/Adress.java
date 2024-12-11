package net.smihi.customerservice.enums;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Adress {
    private String street;
    private String houseNumber;
    private String zipCode;
}
