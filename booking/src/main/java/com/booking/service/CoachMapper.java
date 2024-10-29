package com.booking.service;

import com.booking.domain.Coach;
import com.booking.domain.CoachDTO;
import com.booking.domain.CoachRepository;
import com.booking.domain.CoachResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class CoachMapper {

    public Coach toCoach(CoachDTO coachDTO){
        Coach  coach =  new Coach();

        coach.setAreas(coachDTO.areas());
        coach.setName(coachDTO.name());
        coach.setDescription(coachDTO.description());
        coach.setAvailability(coachDTO.availability());

        return coach;
    }

    public CoachResponseDTO toCoachResponseDTO(Coach coach){

        return new CoachResponseDTO(
                coach.getName(),
                coach.getDescription(),
                coach.getAvailability(),
                coach.getAreas()
        );
    }
}
