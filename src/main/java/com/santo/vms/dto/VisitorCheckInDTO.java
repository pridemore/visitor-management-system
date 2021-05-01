package com.santo.vms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santo.vms.model.Employee;
import com.santo.vms.model.Visitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorCheckInDTO {
    private Employee employee;

    private Visitor visitor;

    private String purpose;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DATE)
    private LocalDate visitDate;

   //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DATE)
    private LocalDate visitUntil;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    private boolean isRepeatVisit;

    @JsonIgnore
    private List<String> repeatDays;

    private int accompaniedBy;
}
