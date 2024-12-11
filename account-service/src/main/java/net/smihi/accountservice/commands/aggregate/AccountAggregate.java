package net.smihi.accountservice.commands.aggregate;

import net.smihi.accountservice.commands.command.CreateAccountCommand;
import net.smihi.accountservice.commands.command.CreditAccountCommande;
import net.smihi.accountservice.commands.command.DebitAccountCommande;
import net.smihi.accountservice.commands.command.DeleteAccountCommand;
import net.smihi.accountservice.commands.exception.NegativeInitialBalanceException;
import net.smihi.accountservice.commons.events.AccountCreateEvent;
import net.smihi.accountservice.commons.events.CreditAccountEvent;
import net.smihi.accountservice.commons.events.DebitAccountEvent;
import net.smihi.accountservice.commons.events.DeleteAccountEvent;
import net.smihi.accountservice.enums.AccountStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String AccountId;
    private String currency;
    private double balance;
    private AccountStatus status;
    private Integer customerId;


    public AccountAggregate() {}
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        if (command.getInitialBalance() < 0) {
            throw new NegativeInitialBalanceException("Negative balance not allowed");
        }
        AccountCreateEvent event = new AccountCreateEvent(
                UUID.randomUUID().toString(),
                command.getCurrency(),
                command.getInitialBalance(),
                command.getCustomerId(),
                command.getStatus()
        );
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void debitAccountCommand(DebitAccountCommande debitCommande){
        DebitAccountEvent event=new DebitAccountEvent(
                debitCommande.getId(),
                debitCommande.getAmount(),
                debitCommande.getCurrency(),
                debitCommande.getDescription());
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void creditAccountCommand(CreditAccountCommande creditCommande){
        CreditAccountEvent event=new CreditAccountEvent(
                creditCommande.getId(),
                creditCommande.getAmount(),
                creditCommande.getCurrency(),
                creditCommande.getDescription());
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void deleteAccountCommand(DeleteAccountCommand deleteCommande){
        DeleteAccountEvent event=new DeleteAccountEvent(
                deleteCommande.getId()
                );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountCreateEvent createEvent){
        this.AccountId=createEvent.getId();
        this.balance=createEvent.getBalance();
        this.currency=createEvent.getCurrency();
        this.customerId=createEvent.getCustomerId();
    }
    @EventSourcingHandler
    public void on(DebitAccountEvent debitEvent){
        this.balance -= debitEvent.getAmount();
    }

    @EventSourcingHandler
    public void on(CreditAccountEvent creditEvent){
        this.balance += creditEvent.getAmount();
    }

}
