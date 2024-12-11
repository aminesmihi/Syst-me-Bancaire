package net.smihi.customerservice.webs;

import lombok.RequiredArgsConstructor;
import net.smihi.customerservice.entities.Customer;
import net.smihi.customerservice.models.CustomermModel;
import net.smihi.customerservice.services.CustomerSrvice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerSrvice customerSrvice;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable  Integer id){
        return customerSrvice.findById(id);
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerSrvice.findAllCustomers();
    }
    @GetMapping("/search")
    public List<Customer> getAllCustomers(@RequestParam String keyword){
        return customerSrvice.searchCustomers(keyword);
    }


    @PostMapping
    public Integer CreateCustomer(@RequestBody CustomermModel customer){
        System.out.printf("bien reçu");
        return customerSrvice.CreateCustomer(customer);
    }
    @PutMapping
    public void updateCustomer(@RequestBody Customer requestCustomer){
         customerSrvice.UpdateCustomer(requestCustomer);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id){
        customerSrvice.deleteCustomer(id);
    }

}
