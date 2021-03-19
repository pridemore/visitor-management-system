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
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long visitId;
    private String employeeId;
    private String guestId;
    private String purpose;
    private String checkInTime;
    private String checkOutTime;
    private String status;
}
