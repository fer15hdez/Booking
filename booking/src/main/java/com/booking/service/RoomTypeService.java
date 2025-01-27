package com.booking.service;

import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeDTO;
import com.booking.domain.RoomTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {

    private final RoomTypeRepository repository;
    private final RoomTypeMapper mapper;

    public RoomTypeService(RoomTypeRepository repository, RoomTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RoomType createRoomType(RoomTypeDTO roomTypeDTO){
        RoomType roomType = this.mapper.toRoomType(roomTypeDTO);
        return this.repository.save(roomType);
    }
}
