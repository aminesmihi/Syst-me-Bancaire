package net.smihi.customerservice.services;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.smihi.customerservice.entities.Customer;
import net.smihi.customerservice.models.CustomermModel;

import java.util.List;
@Tag(name = "CustomerComposite",description = "REST API for composite customer information.")
public interface CustomerSrvice {
    Customer findById(Integer id);
    Integer CreateCustomer(CustomermModel customer);

    void UpdateCustomer(Customer requestCustomer);

    void deleteCustomer(Integer id);

    List<Customer> findAllCustomers();

    List<Customer> searchCustomers(String keyword);
}
