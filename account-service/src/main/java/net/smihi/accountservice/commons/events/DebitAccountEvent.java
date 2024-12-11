package net.smihi.accountservice.commons.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DebitAccountEvent extends BaseEvent<String>{
    private double Amount;
    private String currency;
    private String description;
    @JsonCreator
    public DebitAccountEvent(
            @JsonProperty("id") String id,
            @JsonProperty("amount") double amount,
            @JsonProperty("currency") String currency,
            @JsonProperty("description") String description) {
        super(id);
        this.Amount = amount;
        this.currency = currency;
        this.description = description;
    }
}
