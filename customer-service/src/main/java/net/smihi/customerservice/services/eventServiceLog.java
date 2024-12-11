package net.smihi.customerservice.services;

import lombok.extern.slf4j.Slf4j;
import net.smihi.customerservice.entities.Customer;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class eventServiceLog {

    @EventListener
    @Async
    public void addCustomer(UserActivateEvent event){
        log.info("lancet email "+event.getEmail());
    }
}
