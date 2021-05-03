package com.santo.vms.model;

import com.santo.vms.enums.Role;
import com.santo.vms.utilities.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class User {
    @Id
    private String id;

    //Use employee email
    private String username;

    private String password;

    private String status;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private Role role;

    private EntityStatus entityStatus;

    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    protected OffsetDateTime lastUpdated;
}
