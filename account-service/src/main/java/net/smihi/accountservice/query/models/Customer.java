package net.smihi.accountservice.query.models;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Customer {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;

}
