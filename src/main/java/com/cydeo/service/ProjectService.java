package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO findByProjectCode(String code);

    List<ProjectDTO> listAllProjects();

    void save(ProjectDTO projectDTO);
    void update(ProjectDTO projectDTO);
    void delete(String code);
}
