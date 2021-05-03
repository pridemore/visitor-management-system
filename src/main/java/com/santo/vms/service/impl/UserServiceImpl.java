package com.santo.vms.service.impl;

import com.santo.vms.dto.UserDTO;
import com.santo.vms.model.User;
import com.santo.vms.repository.UserRepository;
import com.santo.vms.service.ifaces.UserService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.enums.SystemConstants;
import com.santo.vms.utilities.util.GenerateKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User createAccount(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmployee(userDTO.getEmployee());
        user.setRole(userDTO.getRole());
        user.setStatus("ACTIVE");
        user.setEntityStatus(EntityStatus.ACTIVE);
        user.setDateCreated(OffsetDateTime.now());
        user.setId(GenerateKey.generateEntityId());
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public User updateAccount(String id, UserDTO userDTO) {

        Optional<User> user = userRepository.findById(id);


        user.get().setUsername(userDTO.getUsername());
        user.get().setPassword(userDTO.getPassword());
        user.get().setEmployee(userDTO.getEmployee());
        user.get().setRole(userDTO.getRole());
        user.get().setLastUpdated(OffsetDateTime.now());
        User updatedUser = userRepository.save(user.get());

        return updatedUser;
    }

    @Override
    public Optional<User> findUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent())
            return SystemConstants.NOT_FOUND.getMessage();


        optionalUser.get().setStatus(EntityStatus.DELETED.name());
        optionalUser.get().setEntityStatus(EntityStatus.DELETED);
        userRepository.save(optionalUser.get());
        return SystemConstants.DELETED.getMessage();
    }

}
