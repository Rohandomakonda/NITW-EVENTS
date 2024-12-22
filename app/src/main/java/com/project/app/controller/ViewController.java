package com.project.app.controller;


import com.project.app.model.Event;
import com.project.app.service.FormService;
import com.project.app.service.ViewSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ViewController {

    @Autowired
    ViewSerivce viewservice;

    @Autowired
    FormService service;

    @GetMapping("/viewevents")
    public ResponseEntity<List<Event>> getAllEvents(){

        if(viewservice.getAllEvents() != null){
            System.out.println("getting all events");
        return new ResponseEntity<>(viewservice.getAllEvents(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(viewservice.getAllEvents(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/event/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] image = service.getEventImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG
                .body(image);
    }

    @GetMapping("/getclubevents/{name}")
    public ResponseEntity<List<Event>> getClubByName(@PathVariable String name){
            List<Event> events = viewservice.getEventsByClub(name);
            return new ResponseEntity<>(events,HttpStatus.OK);
    }


    @GetMapping("/ongoingevents")
    public ResponseEntity<List<Event>> getOngoingEvents(){
        if(viewservice.getongoingEvents() != null)
            return new ResponseEntity<>(viewservice.getongoingEvents(),HttpStatus.OK);
        else{
            return new ResponseEntity<>(viewservice.getongoingEvents(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/public/events")
    public ResponseEntity<List<Event>> getPublicEvents(){
            System.out.println("getpublic events");
        if(viewservice.getPublicEvents() != null){
            System.out.println("getpublic events");
        return new ResponseEntity<>(viewservice.getPublicEvents(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(viewservice.getPublicEvents(),HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<List<Event>> deleteEvent(@PathVariable int id){

        Event event = viewservice.getEventById(id);
        if(event != null){
            viewservice.deleteEvent(id);
            List<Event> events = viewservice.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEvent/{id}")
    public Event getEventToUpdate(@PathVariable int id){
        return viewservice.getEventById(id);
    }

    @PutMapping("/updateEvent/{id}")
    public void updateEvent(@PathVariable int id,@RequestBody Event event){
        viewservice.updateEvent(id,event);
    }
}
