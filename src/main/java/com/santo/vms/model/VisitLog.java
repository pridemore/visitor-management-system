package com.santo.vms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santo.vms.enums.CheckInStatus;
import com.santo.vms.utilities.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class VisitLog {
    @Id
    private String id;

    @OneToOne
    private Employee employee;

    @OneToOne
    private Visitor visitor;

    private String purpose;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate visitDate;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate visitUntil;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    private boolean isRepeatVisit;

    @JsonIgnore
    @ElementCollection
    private List<String> repeatDays;

    private OffsetDateTime checkInTime;

    private OffsetDateTime checkOutTime;

    @Enumerated(EnumType.STRING)
    private CheckInStatus checkInStatus;

    private int accompaniedBy;

    @OneToOne
    private Notification notification;

    @Version
    @Column
    protected Long entityVersion;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    private OffsetDateTime dateCreated;

    @Override
    public String toString() {
        return "VisitLog{" +
                "id='" + id + '\'' +
                ", purpose='" + purpose + '\'' +
                ", visitDate=" + visitDate +
                ", visitUntil=" + visitUntil +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isRepeatVisit=" + isRepeatVisit +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", checkInStatus=" + checkInStatus +
                ", accompaniedBy=" + accompaniedBy +
                '}';
    }
}
