package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.repository.TaskRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    ProjectService projectService;
     ProjectRepository projectRepository;
     ProjectMapper projectMapper;
     TaskRepository taskRepository;
     TaskService taskService;
     PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,ProjectMapper projectMapper, UserMapper userMapper, @Lazy ProjectService projectService, ProjectRepository projectRepository, TaskRepository taskRepository, TaskService taskService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.projectMapper= projectMapper;
        this.passwordEncoder= passwordEncoder;

    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String name) {
        return userMapper.convertToDTO(userRepository.findByUserName(name));
    }

    @Override
    public void save(UserDTO dto) {
        User obj= userMapper.convertToEntity(dto);
        obj.setPassWord(passwordEncoder.encode(obj.getPassWord()));
        userRepository.save(obj);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
       User user= userRepository.findByUserName(userDTO.getUserName());
       User convertedUser = userMapper.convertToEntity(userDTO);
       convertedUser.setId(user.getId());
        userRepository.save(convertedUser);

        return findByUserName(userDTO.getUserName());
    }

    @Override
    public void deleteByUserName(String name) {
        if(checkIfUserCanBeDeleted(userMapper.convertToEntity(findByUserName(name))))
        userRepository.deleteByUserName(name);
    }

    private boolean checkIfUserCanBeDeleted(User user){
        switch (user.getRole().getDescription()){
            case "Manager":
               List<ProjectDTO> projectDTOList = projectService.readAllByAssignedManager(user);
                    return projectDTOList.size()==0;
            case "Employee":
                List<TaskDTO> taskList= taskService.readAllByAssignedEmployee(user);
                return taskList.size()==0;

            default: return true;
        }
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        if(checkIfUserCanBeDeleted(user)){
        user.setDeleted(true);
        userRepository.save(user);
    }
}

    @Override
    public List<UserDTO> findAllByROles(String role) {
        return userRepository.findAllByRole_DescriptionIgnoreCase(role).stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }
}
