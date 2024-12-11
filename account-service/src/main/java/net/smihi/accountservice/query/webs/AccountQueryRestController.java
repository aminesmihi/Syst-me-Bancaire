package net.smihi.accountservice.query.webs;

import lombok.RequiredArgsConstructor;
import net.smihi.accountservice.query.entites.Account;
import net.smihi.accountservice.query.queries.GetAccountByCustomerId;
import net.smihi.accountservice.query.queries.GetAllAccounts;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/query/account")
public class AccountQueryRestController {

    private final QueryGateway queryGateway;

   @GetMapping("/list")
    public CompletableFuture<List<Account>>AllAccount(){
       GetAllAccounts accounts=new GetAllAccounts();
       return queryGateway.query(accounts, ResponseTypes.multipleInstancesOf(Account.class));
   }
    @GetMapping("/{id}")
    public CompletableFuture<Account> getAccountByCustomerId(@PathVariable("id") Integer id){
        GetAccountByCustomerId account=new GetAccountByCustomerId(id);
        return queryGateway.query(account, ResponseTypes.instanceOf(Account.class));
    }

}
