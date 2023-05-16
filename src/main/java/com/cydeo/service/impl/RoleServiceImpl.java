package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.MapperUtils;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.RoleService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    private final MapperUtils mapperUtils;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, MapperUtils mapperUtils) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<RoleDTO> findAllRole() {
                List<Role>roleList=roleRepository.findAll();
               return roleList.stream().map(role -> mapperUtils.convert(role,new RoleDTO())).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return mapperUtils.convert(roleRepository.findById(id).get(),new RoleDTO());
    }
}
