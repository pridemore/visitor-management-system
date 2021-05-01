package com.santo.vms.repository;

import com.santo.vms.enums.CheckInStatus;
import com.santo.vms.model.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitLogRepository extends JpaRepository<VisitLog, String> {
    long countVisitLogByCheckInStatus(CheckInStatus status);
}
