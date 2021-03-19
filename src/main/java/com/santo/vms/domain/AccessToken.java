package com.santo.vms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Godwin Tavirimirwa
 * created at 12-Feb-20 12:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;

    @Column(name = "token", columnDefinition = "TEXT")
    String token;

    @Column(name = "expire_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    Date expireDate;

    public AccessToken(String token, Date expireDate) {
        this.token = token;
        this.expireDate = expireDate;
    }
}
