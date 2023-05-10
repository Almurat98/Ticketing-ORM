package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.repository.TaskRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.TaskService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;
    ProjectRepository projectRepository;
    UserRepository userRepository;
    TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        taskRepository.save(taskMapper.convertToEntity(dto));
    }

    @Override
    public void update(TaskDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return 0;
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return 0;
    }

    @Override
    public void deleteByProject(ProjectDTO project) {

    }

    @Override
    public void completeByProject(ProjectDTO project) {

    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {
        return null;
    }

    @Override
    public void updateStatus(TaskDTO task) {

    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {
        return null;
    }

    @Override
    public List<TaskDTO> readAllByAssignedEmployee(User assignedEmployee) {
        return null;
    }
}
