package com.santo.vms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private String name;
    private String code;
    private String buildingName = "N/A";
    private String floorLevel = "N/A";
}
