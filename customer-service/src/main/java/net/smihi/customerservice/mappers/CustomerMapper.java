package net.smihi.customerservice.mappers;

import net.smihi.customerservice.entities.Customer;
import net.smihi.customerservice.models.CustomermModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer ToCustomer(CustomermModel model){
        return Customer.builder()
                .firstname(model.getFirstname())
                .lastname(model.getLastname())
                .email(model.getEmail())
                .build();
    };
}
