package com.booking.service;

import com.booking.domain.FunctionType;
import com.booking.domain.FunctionTypeDTO;
import com.booking.domain.FunctionTypeResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class FunctionTypeMapper {

    public FunctionType toFunctionType(FunctionTypeDTO dto){

        if (dto == null){
            throw new NullPointerException("The FunctionType DTO should no be null");
        }

        FunctionType functionType = new FunctionType();
        functionType.setName(dto.name());
        functionType.setDescription(dto.description());
//        functionType.setEquipment(dto.equipment());

        return functionType;
    }

    public FunctionTypeResponseDTO toFunctionTypeResponseDTO(FunctionType functionType){
        return new FunctionTypeResponseDTO(
                functionType.getName(),
                functionType.getDescription()
        );
    }
}
