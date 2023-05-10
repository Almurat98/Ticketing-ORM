package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;

import java.util.List;

public interface ProjectService {

    ProjectDTO findByProjectCode(String code);

    List<ProjectDTO> listAllProjects();

    void save(ProjectDTO projectDTO);
   ProjectDTO update(ProjectDTO projectDTO);
    void delete(String code);

    void complete(String projectCode);
}
