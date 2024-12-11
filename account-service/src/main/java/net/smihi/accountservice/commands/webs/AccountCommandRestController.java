package net.smihi.accountservice.commands.webs;

import lombok.RequiredArgsConstructor;
import net.smihi.accountservice.commands.command.CreateAccountCommand;
import net.smihi.accountservice.commands.command.DebitAccountCommande;
import net.smihi.accountservice.commands.command.DeleteAccountCommand;
import net.smihi.accountservice.commands.dto.CreditAccountDto;
import net.smihi.accountservice.commands.dto.DebitAccountDto;
import net.smihi.accountservice.enums.AccountStatus;
import net.smihi.accountservice.models.AccountRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/command/account")
public class AccountCommandRestController {
    private final  CommandGateway commandGateway;
    private final EventStore eventStore;
    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody AccountRequest accountRequest){
        CreateAccountCommand command=new CreateAccountCommand(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                AccountStatus.CREATED,
                "MAD",
                accountRequest.getBalance(),
                accountRequest.getIdCustomer()
        );
        return commandGateway.send(command);
    }
    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountDto request){
        DebitAccountCommande command=new DebitAccountCommande(
                request.getId(),
                LocalDateTime.now(),
                request.getAmount(),
                request.getCurrency(),
                request.getDescription());
        return commandGateway.send(command);
    }
    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountDto request){
        DebitAccountCommande command=new DebitAccountCommande(
                request.getId(),
                LocalDateTime.now(),
                request.getAmount(),
                request.getCurrency(),
                request.getDescription());
        return commandGateway.send(command);
    }
    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> deleteAccount(@PathVariable("id") String id){
        DeleteAccountCommand command=new DeleteAccountCommand(id,LocalDateTime.now());
        return commandGateway.send(command);
    }
    @GetMapping("eventstore/{id}")
    public Object eventstore(@PathVariable("id") String id){
        DomainEventStream eventStream = eventStore.readEvents(id);
        return eventStream.asStream();
    }


}
