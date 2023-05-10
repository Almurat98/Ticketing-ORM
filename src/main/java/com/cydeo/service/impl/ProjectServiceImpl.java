package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectMapper projectMapper;
    ProjectRepository projectRepository;
    UserMapper userMapper;
    UserService userService;
    TaskService taskService;

    public ProjectServiceImpl(ProjectMapper projectMapper,TaskService taskService,UserService userService, ProjectRepository projectRepository,UserMapper userMapper) {
        this.projectMapper = projectMapper;
        this.projectRepository= projectRepository;
        this.userMapper=userMapper;
        this.userService=userService;
        this.taskService=taskService;
    }

    @Override
    public ProjectDTO findByProjectCode(String code) {
        Project project= projectRepository.findByProjectCode(code);
        return projectMapper.convertToDTO(project);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN);
        Project project= projectMapper.convertToEntity(projectDTO);
       projectRepository.save(project);
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        Project project =projectRepository.findByProjectCode(projectDTO.getProjectCode());
        Project convertedProject= projectMapper.convertToEntity(projectDTO);
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);

        return findByProjectCode(projectDTO.getProjectCode());

    }

    @Override
    public void delete(String code) {
        Project project= projectRepository.findByProjectCode(code);
        project.setDeleted(true);
        projectRepository.save(project);
    }

    @Override
    public void complete(String projectCode) {
        Project project= projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);
    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        UserDTO currentUserDTO = userService.findByUserName("harold@manager.com");
        User user=userMapper.convertToEntity(currentUserDTO);

        List<Project> list= projectRepository.findAllByAssignedManager(user);


        return list.stream().map(project -> {
                    ProjectDTO obj = projectMapper.convertToDTO(project);

                    obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTask(project.getProjectCode()));
                    obj.setCompleteTaskCounts(taskService.totalCompletedTask(project.getProjectCode()));

                    return obj;
                }
                ).collect(Collectors.toList());

    }

     @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        return  projectRepository.findAllByAssignedManager(userMapper.convertToEntity(manager)).stream().map(projectMapper::convertToDTO).collect(Collectors.toList());


    }
}
