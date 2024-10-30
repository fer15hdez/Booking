package com.booking.service;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomRepository;
import com.booking.domain.RoomResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository repository;
    private final RoomMapper mapper;

    public RoomService(RoomRepository repository, RoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RoomResponseDTO createRoom(RoomDTO roomDTO){
        Room room = this.mapper.toRoom(roomDTO);

        return this.mapper.toRoomResponseDTO(this.repository.save(room));
    }
}
