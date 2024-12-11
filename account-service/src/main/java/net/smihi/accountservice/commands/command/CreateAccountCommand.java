package net.smihi.accountservice.commands.command;

import lombok.Getter;
import net.smihi.accountservice.enums.AccountStatus;

import java.time.LocalDateTime;
@Getter
public class CreateAccountCommand extends BaseCommand<String>{

    private AccountStatus status;
    private String currency;
    private double initialBalance;
    private Integer customerId;


    public CreateAccountCommand(String id, LocalDateTime commandDate, AccountStatus status, String currency, double initialBalance, Integer customerId) {
        super(id, commandDate);
        this.status = status;
        this.currency = currency;
        this.initialBalance = initialBalance;
        this.customerId = customerId;
    }
}
