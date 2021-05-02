package com.santo.vms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Notification {
    @Id
    private String id;
    private String message;

    @OneToOne(mappedBy = "notification", targetEntity = VisitLog.class)
    private VisitLog visitLog;

    private String status;

    private OffsetDateTime dateCreated;
}
