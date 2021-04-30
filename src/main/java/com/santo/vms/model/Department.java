package com.santo.vms.model;

import com.santo.vms.utilities.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Department {
    @Id
    private String id;

    private String code;

    private String name;

    @Column(columnDefinition = "varchar(255) default 'NA'")
    private String buildingName;

    @Column(columnDefinition = "varchar(255) default 'NA'")
    private String floorLevel;

    private String status;

    @OneToMany(mappedBy = "department", targetEntity = Employee.class)
    private List<Employee> employees;

    @Version
    @Column
    protected Long entityVersion;
    @Enumerated(EnumType.STRING)

    private EntityStatus entityStatus;

    private OffsetDateTime dateCreated;

}
