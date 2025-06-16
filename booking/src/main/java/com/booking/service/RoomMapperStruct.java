package com.booking.service;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring") // Para que Spring lo inyecte como un bean
public abstract class RoomMapperStruct {
    @Autowired
    protected RoomTypeRepository roomTypeRepository;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDto(RoomDTO dto, @MappingTarget Room entity);

    @Mapping(source = "type", target = "type") // Mapea el campo 'type' del DTO al 'type' de la entidad
    @Mapping(target = "id", ignore = true) // Generalmente el ID se genera automáticamente para una nueva entidad
    @Mapping(target = "bookings", ignore = true) // Ignora bookings si no se mapea directamente desde DTO
    public abstract Room toRoom(RoomDTO roomDTO);

    // Méthod de mapeo personalizado para convertir Integer a RoomType
    public RoomType map(Integer typeId) {
        if (typeId == null) {
            return null; // O lanza una excepción si el ID de tipo es obligatorio
        }
        // Busca el RoomType por su ID. Si no lo encuentra, puedes lanzar una excepción.
        return roomTypeRepository.findById(typeId)
                .orElseThrow(() -> new EntityNotFoundException("RoomType with ID " + typeId + " not found"));
    }
}