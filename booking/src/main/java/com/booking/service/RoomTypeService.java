package com.booking.service;

import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeDTO;
import com.booking.domain.RoomTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RoomTypeService {

    private final RoomTypeRepository repository;
    private final RoomTypeMapper mapper;
    Logger logger =  Logger.getLogger(getClass().getName());

    public RoomTypeService(RoomTypeRepository repository, RoomTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RoomType createRoomType(RoomTypeDTO roomTypeDTO){
        RoomType roomType = this.mapper.toRoomType(roomTypeDTO);

        this.repository.findById(roomType.getId()).orElseThrow(
                () -> {
                    logger.info("No found RoomType entity with id: " + roomType.getId());
                    return new EntityNotFoundException("No found Room Type entity with given id");
                }
        );
        return this.repository.save(roomType);
    }
}
