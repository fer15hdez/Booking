package com.booking.controller;

import com.booking.domain.Coach;
import com.booking.domain.CoachDTO;
import com.booking.domain.CoachResponseDTO;
import com.booking.domain.CoachUpdateDTO;
import com.booking.service.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {
    private final CoachService service;

    public CoachController(CoachService service) {
        this.service = service;
    }


    @GetMapping
    public List<CoachResponseDTO> coaches(){
        return this.service.allCoaches();
    }

    @PostMapping
    public CoachResponseDTO createCoach(@RequestBody CoachDTO coachDTO){
        return this.service.createCoach(coachDTO);
    }

    @PutMapping("/update")
    public CoachResponseDTO updateCoach(@RequestBody CoachUpdateDTO coachUpdateDTO){
        return this.service.updateCoach(coachUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCoach(@PathVariable("id") Integer id){
        this.service.deleteCoach(id);
    }

}
