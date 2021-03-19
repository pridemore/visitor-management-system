package com.santo.vms.domain.embeddables;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Godwin Tavirimirwa
 * created at 03-Sep-19 14:21
 */
@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersistenceDates {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @PrePersist
    protected void init() {
        if (Objects.isNull(createdDate)) {
            createdDate = LocalDateTime.now();
        }
        if (Objects.isNull(modifiedDate)) {
            modifiedDate = LocalDateTime.now();
        }
    }


}
