package net.smihi.accountservice.commands.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class CreditAccountDto {
    private String id;
    private double amount;
    private String currency;
    private String description;
}
