package net.smihi.accountservice.models;

import lombok.Data;

@Data
public class AccountRequest {
    private double balance;
    private Integer idCustomer;
}
