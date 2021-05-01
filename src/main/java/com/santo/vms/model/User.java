package com.santo.vms.model;

import com.santo.vms.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class User {
    @Id
    private String id;

    //Use employee email
    private String username;

    private String password;

    private String status;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private Role role;
}
