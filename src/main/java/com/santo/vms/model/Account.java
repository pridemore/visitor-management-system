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
public class Account {
    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Version
    @Column
    protected Long entityVersion;
    @Enumerated(EnumType.STRING)

    private EntityStatus entityStatus;

    private OffsetDateTime dateCreated;
}
