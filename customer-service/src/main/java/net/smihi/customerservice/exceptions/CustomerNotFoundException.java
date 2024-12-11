package net.smihi.customerservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException {

    private final String message;

    public CustomerNotFoundException(String message) {
        this.message = message;
    }
}
