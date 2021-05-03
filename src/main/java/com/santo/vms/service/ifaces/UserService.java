package com.santo.vms.service.ifaces;

import com.santo.vms.dto.UserDTO;
import com.santo.vms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createAccount(UserDTO userDTO);
    User updateAccount(String id,UserDTO userDTO);
    Optional<User> findUserById(String id);
    List<User> getAllUsers();
    String deleteUser(String id);
}
