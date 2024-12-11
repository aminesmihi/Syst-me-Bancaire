package net.smihi.accountservice.query.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.smihi.accountservice.commons.events.AccountCreateEvent;
import net.smihi.accountservice.commons.events.CreditAccountEvent;
import net.smihi.accountservice.commons.events.DebitAccountEvent;
import net.smihi.accountservice.commons.events.DeleteAccountEvent;
import net.smihi.accountservice.enums.AccountStatus;
import net.smihi.accountservice.query.entites.Account;
import net.smihi.accountservice.query.entites.Operation;
import net.smihi.accountservice.query.queries.GetAllAccounts;
import net.smihi.accountservice.query.repository.AccountRepository;
import net.smihi.accountservice.query.repository.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountEventHandlerService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreateEvent createEvent ){
        log.info("********");
        log.info("AccoountRepo recivied");
       Account  account= Account.builder()
               .id(createEvent.getId())
               .accountStatus(AccountStatus.CREATED)
               .balance(createEvent.getBalance())
               .currency(createEvent.getCurrency())
               .customerId(createEvent.getCustomerId())
               .build();
       accountRepository.save(account);
    }
    @EventHandler
    public void on(CreditAccountEvent event ){
        Account account=accountRepository.findById(event.getId()).get();
        log.info(String.valueOf(account.getCustomerId()));
        Operation operation=Operation.builder()
                .amount(event.getAmount())
                .description(event.getDescription())
                .account(account)
                .build();
        operationRepository.save(operation);

    }
    @EventHandler
    public void on(DeleteAccountEvent event ){
        accountRepository.deleteById(event.getId());
    }
}
