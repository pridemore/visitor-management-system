package com.santo.vms.dto;

import lombok.Data;

@Data
public class EditDepartmentDTO {
    private String editId;
    private String nameEdit;
    private String codeEdit;
    private String buildingNameEdit = "N/A";
    private String floorLevelEdit = "N/A";
}
