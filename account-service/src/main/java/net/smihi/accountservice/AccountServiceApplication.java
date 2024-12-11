    package net.smihi.accountservice;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.boot.autoconfigure.domain.EntityScan;
    import org.springframework.cloud.openfeign.EnableFeignClients;
    import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
    import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
    import org.springframework.scheduling.annotation.EnableAsync;

    @SpringBootApplication
    @EnableFeignClients
    @EnableJpaAuditing
    @EnableJpaRepositories
    @EntityScan(basePackages = {
            "org.axonframework.eventsourcing.eventstore.jpa",
            "org.axonframework.modelling.saga.repository.jpa",
            "net.smihi.accountservice.query.entites",
            "org.axonframework.eventhandling.tokenstore.jpa"
    })
    public class AccountServiceApplication {

        public static void main(String[] args) {
            SpringApplication.run(AccountServiceApplication.class, args);
        }

    }
