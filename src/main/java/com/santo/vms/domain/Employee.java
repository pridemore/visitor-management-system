package com.santo.vms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long employeeId;
    private String name;
    private String surname;
    private String employeeNo;
    private String nationalId;
    private String departmentId;
    private LocalDate dob;
    private String gender;
    private String locationId;
    private String phoneNo;
    private String email;
    private String status;
}
