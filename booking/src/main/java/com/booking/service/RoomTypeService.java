package com.booking.service;

import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {

    private final RoomTypeRepository repository;

    public RoomTypeService(RoomTypeRepository repository) {
        this.repository = repository;
    }

    public RoomType createRoomType(RoomType roomType){
        return this.repository.save(roomType);
    }
}
