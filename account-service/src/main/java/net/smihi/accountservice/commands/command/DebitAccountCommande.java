package net.smihi.accountservice.commands.command;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class DebitAccountCommande extends BaseCommand<String>{
    private double Amount;
    private String currency;
    private final String description;
    public DebitAccountCommande(String id, LocalDateTime commandDate, double amount, String currency, String description) {
        super(id, commandDate);
        this.Amount = amount;
        this.currency = currency;
        this.description = description;
    }
}
