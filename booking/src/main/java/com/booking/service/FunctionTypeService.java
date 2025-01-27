package com.booking.service;

import com.booking.domain.FunctionType;
import com.booking.domain.FunctionTypeDTO;
import com.booking.domain.FunctionTypeRepository;
import com.booking.domain.FunctionTypeResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class FunctionTypeService {

    private final FunctionTypeRepository repository;
    private final FunctionTypeMapper mapper;

    public FunctionTypeService(FunctionTypeRepository repository, FunctionTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FunctionTypeResponseDTO createFunctionType(FunctionTypeDTO dto){
        FunctionType functionType = this.mapper.toFunctionType(dto);

       return this.mapper.toFunctionTypeResponseDTO(this.repository.save(functionType));
    }

    public void deleteFunctionType(Integer id){
        this.repository.deleteById(id);
    }
}
