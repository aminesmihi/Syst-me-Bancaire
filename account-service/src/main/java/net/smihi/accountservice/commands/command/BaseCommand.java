package net.smihi.accountservice.commands.command;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Getter
public class BaseCommand<T> {
    @TargetAggregateIdentifier
     private T id;
    private final LocalDateTime commandDate;
    BaseCommand(T id, LocalDateTime commandDate){
        this.id=id;
        this.commandDate = commandDate;
    }

}
