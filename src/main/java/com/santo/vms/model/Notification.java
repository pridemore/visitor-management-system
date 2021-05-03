package com.santo.vms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    protected OffsetDateTime lastUpdated;
}
