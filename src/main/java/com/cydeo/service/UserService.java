package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllUsers();
    UserDTO findByUserName(String name);

    void save(UserDTO user);

    UserDTO update(UserDTO user);

    void deleteByUserName(String name);




}
