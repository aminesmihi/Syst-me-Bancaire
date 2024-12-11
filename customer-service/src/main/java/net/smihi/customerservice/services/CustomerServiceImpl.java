package net.smihi.customerservice.services;

import lombok.RequiredArgsConstructor;
import net.smihi.customerservice.entities.Customer;
import net.smihi.customerservice.exceptions.CustomerNotFoundException;
import net.smihi.customerservice.mappers.CustomerMapper;
import net.smihi.customerservice.models.CustomermModel;
import net.smihi.customerservice.repository.CustomerRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerSrvice{
    private final  ApplicationEventPublisher applicationEventPublisher;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer findById(Integer id) {
        var customer=customerRepository.findById(id).orElseThrow();
        return customer;
    }

    @Override
    public Integer CreateCustomer(CustomermModel customermModel) {
        var Customer=customerMapper.ToCustomer(customermModel);
        return customerRepository.save(Customer).getId();
    }

    @Override
    public void UpdateCustomer(Customer customer) {
        var cust=customerRepository.findById(customer.getId())
                .orElseThrow(()->new CustomerNotFoundException("Customer n'existe pas "));
        if(customer!=null){
            customerRepository.save(cust);
        }
    }

    @Override
    public void deleteCustomer(Integer id) {
        var customer=customerRepository.findById(id);
        if(customer!=null){
            customerRepository.deleteById(id);
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        this.applicationEventPublisher.publishEvent(new UserActivateEvent());
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        return customerRepository.searchCustomers(keyword);
    }
}
