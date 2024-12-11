package net.smihi.customerservice;

import lombok.RequiredArgsConstructor;
import net.smihi.customerservice.entities.Customer;
import net.smihi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class LineRunner implements CommandLineRunner {
    private  final CustomerRepository customerRepository;
    @Override
    public void run(String... args) throws Exception {
        var customer1= Customer.builder()
                .firstname("amine")
                .lastname("smihi")
                .email("samine")
                .build();
        var customer2= Customer.builder()
                .firstname("youssef")
                .lastname("charki")
                .email("ycharki")
                .build();
        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }
}
