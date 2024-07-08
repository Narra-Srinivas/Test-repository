package com.awsdeployment.awsdeployementspringbootapplication.controller;


import com.awsdeployment.awsdeployementspringbootapplication.eventsHubService.EventHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/aws")
public class AwsController {

    private final EventHubService eventHubService;

    @GetMapping("/endpoint1")
    public String endpoint1() {

        return "this endpoint works well";
    }

    @GetMapping("/sendEvent")
    public String sendEvent(@RequestParam String message) {
        eventHubService.sendEvent(message);
        return "Event sent: " + message;
    }

}
