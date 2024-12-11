package net.smihi.accountservice.query.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.smihi.accountservice.query.FiegnClients.CustomerFiegn;
import net.smihi.accountservice.query.entites.Account;
import net.smihi.accountservice.query.models.Customer;
import net.smihi.accountservice.query.queries.GetAccountByCustomerId;
import net.smihi.accountservice.query.queries.GetAllAccounts;
import net.smihi.accountservice.query.repository.AccountRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountQueryHandlerService {
    private final AccountRepository accountRepository;
    private final CustomerFiegn customerFiegn;

    @QueryHandler
    public List<Account> AllAccount(GetAllAccounts accounts){
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account GetAccountByCustomerId(GetAccountByCustomerId account){
        Customer customer=customerFiegn.GetCustomerById(account.getId());
        log.info(customer.toString());
        Account acc = accountRepository.findAccountByCustomerId(account.getId());
        acc.setCustomer(customer);
        return acc;
    }

}
