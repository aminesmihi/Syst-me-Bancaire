package net.smihi.accountservice.commands.command;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreditAccountCommande extends BaseCommand<String>{
    private double Amount;
    private String currency;
    private final String description;
    CreditAccountCommande(String id, LocalDateTime commandDate, double amount, String currency, String description) {
        super(id, commandDate);
        this.Amount = amount;
        this.currency = currency;
        this.description = description;
    }
}
