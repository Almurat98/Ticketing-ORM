package com.cydeo.mapper;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final ModelMapper mapper;


    public TaskMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public TaskDTO convertToDTO(Task task){
        return mapper.map(task,TaskDTO.class);
    }


    public Task convertToEntity(TaskDTO dto){
        return mapper.map(dto,Task.class);
    }


}
