package com.booking.service;

import com.booking.domain.Coach;
import com.booking.domain.CoachDTO;
import com.booking.domain.CoachRepository;
import com.booking.domain.CoachResponseDTO;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachService {

    private final CoachRepository repository;
    private final CoachMapper mapper;

    public CoachService(CoachRepository repository, CoachMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CoachResponseDTO> allCoaches(){
        return this.repository.findAll()
                .stream()
                .map(mapper::toCoachResponseDTO)
                .collect(Collectors.toList());
    }

    public CoachResponseDTO createCoach(CoachDTO coachDTO){
        Coach coach = this.mapper.toCoach(coachDTO);

        return this.mapper.toCoachResponseDTO(this.repository.save(coach));

    }

    public CoachResponseDTO updateCoach(CoachDTO coachDTO){
        Coach coach = this.mapper.toCoach(coachDTO);

        return this.mapper.toCoachResponseDTO(this.repository.save(coach));
    }

    public void deleteCoach(Integer id){
        this.repository.deleteById(id);
    }

}
