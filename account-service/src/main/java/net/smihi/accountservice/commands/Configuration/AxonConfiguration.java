package net.smihi.accountservice.commands.Configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.deadletter.jpa.JpaSequencedDeadLetterQueue;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.modelling.saga.repository.SagaStore;
import org.axonframework.modelling.saga.repository.jpa.JpaSagaStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.axonframework.common.transaction.TransactionManager;

@Configuration
@EnableAsync
public class AxonConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }

    @Bean
    public TransactionManager axonTransactionManager(PlatformTransactionManager platformTransactionManager) {
        return new SpringTransactionManager(platformTransactionManager);
    }

    // Set JacksonSerializer as the default and primary serializer for events and messages
    @Bean
    @Primary
    public Serializer defaultSerializer() {
        return JacksonSerializer.defaultSerializer();
    }

    // Event Storage Engine with JacksonSerializer
    @Bean
    public JpaEventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider,
                                                    TransactionManager transactionManager,
                                                    @Qualifier("defaultSerializer") Serializer defaultSerializer) {
        return JpaEventStorageEngine.builder()
                .entityManagerProvider(entityManagerProvider)
                .transactionManager(transactionManager)
                .eventSerializer(defaultSerializer)  // Use JacksonSerializer for event storage
                .build();
    }

    // SagaStore configured with JacksonSerializer
    @Bean
    public SagaStore sagaStore(EntityManagerProvider entityManagerProvider, @Qualifier("defaultSerializer") Serializer defaultSerializer) {
        return JpaSagaStore.builder()
                .entityManagerProvider(entityManagerProvider)
                .serializer(defaultSerializer)  // Use JacksonSerializer for saga storage
                .build();
    }

    // Dead Letter Queue Configuration with both genericSerializer and eventSerializer
    @Bean
    public JpaSequencedDeadLetterQueue<EventMessage<?>> deadLetterQueue(EntityManagerProvider entityManagerProvider,
                                                                        TransactionManager transactionManager,
                                                                        @Qualifier("defaultSerializer") Serializer eventSerializer,
                                                                        @Qualifier("generic") Serializer genericSerializer) {
        return JpaSequencedDeadLetterQueue.builder()
                .processingGroup("defaultProcessingGroup")
                .entityManagerProvider(entityManagerProvider)
                .transactionManager(transactionManager)
                .eventSerializer(eventSerializer)
                .genericSerializer(genericSerializer)  // Add the required genericSerializer
                .build();
    }

    @Bean
    @Qualifier("generic")
    public Serializer genericSerializer() {
        return JacksonSerializer.defaultSerializer();  // Set JacksonSerializer as the generic serializer
    }

    // Event Processor Configuration
    public void configureTrackingEventProcessor(EventProcessingConfigurer configurer,
                                                JpaSequencedDeadLetterQueue<EventMessage<?>> deadLetterQueue) {
        configurer.registerTrackingEventProcessor(
                "yourEventProcessorName",
                org.axonframework.config.Configuration::eventStore,
                configuration -> TrackingEventProcessorConfiguration.forParallelProcessing(1)
        );
    }
}
