package com.booking.service;

import com.booking.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {

    private final RoomTypeRepository roomTypeRepository;

    public RoomMapper(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public Room toRoom(RoomDTO roomDTO){
        if (roomDTO == null){
            throw new NullPointerException("The Room DTO should no be null");
        }

        RoomType roomType = new RoomType();
        Room room = new Room();
        if (roomDTO.type() != null){
            roomType.setId(roomDTO.type());
            room.setType(roomType);
        }
        room.setLength(roomDTO.length());

        room.setWidth(roomDTO.width());

        room.setName(roomDTO.name());
        room.setDescription(roomDTO.description());
        room.setAvailability(roomDTO.availability());

        return room;
    }

    public RoomResponseDTO toRoomResponseDTO(Room room){
        Integer roomTypeId = room.getType().getId();
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Entity not found with id: " + roomTypeId)
                );
        RoomTypeResponseDTO roomTypeResponseDTO = new RoomTypeResponseDTO(roomType.getName(), roomType.getDescription());

        return new RoomResponseDTO(
                room.getName(),
                room.getDescription(),
                room.getWidth(),
                room.getLength(),
                roomTypeResponseDTO
        );
    }
}
