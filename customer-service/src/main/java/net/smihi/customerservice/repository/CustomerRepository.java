package net.smihi.customerservice.repository;

import lombok.RequiredArgsConstructor;
import net.smihi.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("select c from Customer c where c.email like %:keyword%")
    List<Customer> searchCustomers(String keyword);

}
