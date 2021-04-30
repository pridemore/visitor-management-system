package com.santo.vms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDTO {
    private String name;
    private String surname;
    private String nationalId;
    private String gender;
    private String phoneNo;
    private String email;
}
