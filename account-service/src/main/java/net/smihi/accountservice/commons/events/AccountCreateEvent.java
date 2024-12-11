package net.smihi.accountservice.commons.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.smihi.accountservice.enums.AccountStatus;

@Getter
public class AccountCreateEvent extends BaseEvent<String> {
    private String currency;
    private double balance;
    private AccountStatus status;
    private  Integer customerId;


    @JsonCreator
    public AccountCreateEvent(
            @JsonProperty("id") String id,
            @JsonProperty("currency") String currency,
            @JsonProperty("balance") double balance,
            @JsonProperty("customerId") Integer customerId,
            @JsonProperty("status") AccountStatus status) {
        super(id);
        this.currency = currency;
        this.balance = balance;
        this.status = status;
        this.customerId=customerId;
    }
}
