package com.project.event_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.project.event_service.dto.NotificationDTO;

@FeignClient(name="NOTIFICATION-SERVICE",url="https://notification-service-7dvl.onrender.com")
public interface NotificationClient {

    @PostMapping("api/notifications/event/{eventTitle}")
    public ResponseEntity<String> createEventNotification(@PathVariable String eventTitle);
}
