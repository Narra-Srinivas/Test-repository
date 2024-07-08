package com.awsdeployment.awsdeployementspringbootapplication.eventsHubService;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EventHubService {

    private final EventHubProducerClient producerClient;

    public EventHubService(
            @Value("${azure.eventhubs.connection-string}") String connectionString,
            @Value("${azure.eventhubs.eventhub-name}") String eventHubName) {
        this.producerClient = new EventHubClientBuilder()
                .connectionString(connectionString, eventHubName)
                .buildProducerClient();
    }

    public void sendEvent(String message) {
        try {
//            EventData eventData = new EventData(message);
            List<EventData> eventData = Arrays.asList(new EventData(message));
            producerClient.send(eventData);
            System.out.println("Event sent: " + message);
        } catch (Exception e) {
            System.err.println("Error sending event: " + e.getMessage());
            e.printStackTrace();
            // Handle or log the exception as appropriate
        }
    }
}
