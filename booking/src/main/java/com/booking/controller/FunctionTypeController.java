package com.booking.controller;

import com.booking.domain.FunctionType;
import com.booking.domain.FunctionTypeDTO;
import com.booking.domain.FunctionTypeResponseDTO;
import com.booking.service.FunctionTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/functions")
public class FunctionTypeController {

    private final FunctionTypeService service;

    public FunctionTypeController(FunctionTypeService service) {
        this.service = service;
    }

    @PostMapping
    public FunctionTypeResponseDTO createFunctionType(@RequestBody FunctionTypeDTO functionType){
        return this.service.createFunctionType(functionType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFunctionType(@PathVariable("id") Integer id){
        this.service.deleteFunctionType(id);
    }
}
