package com.booking.service;

import com.booking.domain.FunctionType;
import com.booking.domain.FunctionTypeDTO;
import com.booking.domain.FunctionTypeRepository;
import com.booking.domain.FunctionTypeResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class FunctionTypeService {

    private final FunctionTypeRepository repository;
    private final FunctionTypeMapper mapper;
    Logger logger = Logger.getLogger(getClass().getName());

    public FunctionTypeService(FunctionTypeRepository repository, FunctionTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FunctionTypeResponseDTO createFunctionType(FunctionTypeDTO dto){
        FunctionType functionType = this.mapper.toFunctionType(dto);

       return this.mapper.toFunctionTypeResponseDTO(this.repository.save(functionType));
    }

    public void deleteFunctionType(Integer id){

        this.repository.findById(id).orElseThrow(
                () -> {
                    logger.info("No found entity FunctionType with id: " + id);
                    return new EntityNotFoundException("No found");
                }
                );

        this.repository.deleteById(id);
    }
}
