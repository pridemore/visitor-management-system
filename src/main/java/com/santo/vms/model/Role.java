package com.santo.vms.model;

import com.santo.vms.utilities.enums.EntityStatus;
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
public class Role {
    @Id
    private String id;
    private String name;
    private String status;
    @OneToOne
    private User user;

    @Version
    @Column
    protected Long entityVersion;
    @Enumerated(EnumType.STRING)

    private EntityStatus entityStatus;

    private OffsetDateTime dateCreated;
}
