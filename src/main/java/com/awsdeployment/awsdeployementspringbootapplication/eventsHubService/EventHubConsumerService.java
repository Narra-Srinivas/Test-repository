package com.awsdeployment.awsdeployementspringbootapplication.eventsHubService;

import com.azure.messaging.eventhubs.*;
import com.azure.messaging.eventhubs.models.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class EventHubConsumerService {

    private final EventProcessorClient eventProcessorClient;

    public EventHubConsumerService(
            @Value("${azure.eventhubs.connection-string}") String connectionString,
            @Value("${azure.eventhubs.eventhub-name}") String eventHubName,
            @Value("${azure.eventhubs.consumer-group}") String consumerGroup) {

        Consumer<EventContext> processEvent = eventContext -> {
            String eventData = eventContext.getEventData().getBodyAsString();
            System.out.println("Received event: " + eventData + " from partition: " + eventContext.getPartitionContext().getPartitionId());
        };

        Consumer<ErrorContext> processError = error -> {
            System.err.println("Error occurred while receiving: " + error.getThrowable());
        };

        this.eventProcessorClient = new EventProcessorClientBuilder()
                .connectionString(connectionString, eventHubName)
                .consumerGroup(consumerGroup)
                .processEvent(processEvent)
                .processError(processError)
                .buildEventProcessorClient();
    }

    @PostConstruct
    public void start() {
        eventProcessorClient.start();
    }

    @PreDestroy
    public void stop() {
        eventProcessorClient.stop();
    }
}
