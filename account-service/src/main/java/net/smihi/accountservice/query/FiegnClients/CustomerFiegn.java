package net.smihi.accountservice.query.FiegnClients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import net.smihi.accountservice.query.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerFiegn {

    @GetMapping("/api/v1/customer/{id}")
    @RateLimiter(name = "rateLimiterCustomer")
    @CircuitBreaker(name = "circuitBreakercustomer",fallbackMethod = "retryCustomerService")
    public Customer GetCustomerById(@PathVariable("id") Integer id);

    public default Customer retryCustomerService(Integer id,Throwable throwable){
        return  new Customer();
    }

}
