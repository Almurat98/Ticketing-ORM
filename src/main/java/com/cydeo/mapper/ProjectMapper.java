package com.cydeo.mapper;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import org.modelmapper.ModelMapper;

public class ProjectMapper {

    private final ModelMapper mapper;

    public ProjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Project convertToEntity(ProjectDTO dto){
        return mapper.map(dto,Project.class);
    }

    public ProjectDTO convertToDTO(Project project){
        return mapper.map(project,ProjectDTO.class);
    }
}