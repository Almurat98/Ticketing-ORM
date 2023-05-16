package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtils {

        private final ModelMapper mapper;


    public MapperUtils(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <T> T convert(Object objectToConvert, T convertedObj){
        return mapper.map(objectToConvert,(Type)convertedObj.getClass());
    }
}
