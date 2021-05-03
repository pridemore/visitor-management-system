package com.santo.vms.dto;

import com.santo.vms.enums.Role;
import com.santo.vms.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private Employee employee;
    private Role role;
}
