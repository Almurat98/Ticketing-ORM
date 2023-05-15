package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;

import java.util.Arrays;
import java.util.List;

public interface ProjectService {

    ProjectDTO findByProjectCode(String code);

    List<ProjectDTO> listAllProjects();

    void save(ProjectDTO projectDTO);
   ProjectDTO update(ProjectDTO projectDTO);
    void delete(String code);

    void complete(String projectCode);

    List<ProjectDTO>listAllProjectDetails();

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);

    List<ProjectDTO> readAllByAssignedManager(User user);
}
