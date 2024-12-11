package net.smihi.accountservice.commands.command;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class DeleteAccountCommand extends BaseCommand<String>{
    public DeleteAccountCommand(String id, LocalDateTime commandDate) {
        super(id, commandDate);
    }
}
