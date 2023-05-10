package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper=userMapper;
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
    public void save(UserDTO user) {
        userRepository.save(userMapper.convertToEntity(user));
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
        userRepository.deleteByUserName(name);
    }


    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findAllByROles(String role) {
        return userRepository.findAllByRole_DescriptionIgnoreCase(role).stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }
}
