package net.smihi.accountservice.commons.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DeleteAccountEvent extends BaseEvent<String>{
    public DeleteAccountEvent(
            @JsonProperty("id") String id) {
        super(id);
    }
}
