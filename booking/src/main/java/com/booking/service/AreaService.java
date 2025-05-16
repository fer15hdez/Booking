package com.booking.service;

import com.booking.domain.Area;
import com.booking.domain.AreaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AreaService {
    private final AreaRepository repository;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public AreaService(AreaRepository repository) {
        this.repository = repository;
    }

    public List<Area> allArea(){
        return this.repository.findAll();
    }

    @Transactional
    public Area createArea(Area area){

        return this.repository.save(area);
    }

    @Transactional
    public Area updateArea(Area area){
        Integer areaId = area.getId();
        if (areaId == null){
            logger.warning("Intento de actualizar un área con ID nulo.");
            throw new IllegalArgumentException("El ID del área no puede ser nulo para la actualización.");
        }

        Area existingArea = this.repository.findById(area.getId()).orElseThrow(
                () -> {
                    logger.info("Entity no found with id: " + area.getId());
                    return new EntityNotFoundException("Entity no found");
                }
        );

        if (area.getName() == null){
            area.setName(existingArea.getName());
        }

        return this.repository.save(area);
    }

    public void deleteArea(Integer id){
        this.repository.findById(id).orElseThrow(
                () -> {
                    logger.info("Entity no found with id: " + id);
                    return new EntityNotFoundException("Entity no found");
                }
        );

        this.repository.deleteById(id);
    }
}
