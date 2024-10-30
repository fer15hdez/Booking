package com.booking.controller;

import com.booking.domain.RoomDTO;
import com.booking.domain.RoomResponseDTO;
import com.booking.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public RoomResponseDTO createRoom(@RequestBody RoomDTO roomDTO){
        return this.service.createRoom(roomDTO);
    }
}
