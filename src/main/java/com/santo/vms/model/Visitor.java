package com.santo.vms.model;

import com.santo.vms.utilities.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Visitor {
    @Id
    private String id;

    private String name;

    private String surname;

    private String nationalId;

    private LocalDate dob;

    private String gender;

    private String phoneNo;

    private String email;

    private String status;

    private String signature;

    private String organization;

    private String nationality;

    private String address;

    private String city;

    private String country;

    //Set this value to true to blacklist a visitor
    private boolean blacklist;

    @OneToOne
    private VisitLog visit;

    @Version
    @Column
    protected Long entityVersion;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    private OffsetDateTime dateCreated;

    @Override
    public String toString() {
        return "Visitor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", city='" + city + '\'' +
                ", blacklist=" + blacklist +
                '}';
    }
}
