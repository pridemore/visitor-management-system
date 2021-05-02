package com.santo.vms.dto;

import com.santo.vms.model.VisitLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private String id;
    private String message;
    private VisitLog visitLog;
}
