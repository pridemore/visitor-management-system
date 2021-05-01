package com.santo.vms.service.ifaces;

import com.santo.vms.dto.VisitorCheckInDTO;
import com.santo.vms.dto.VisitorDTO;
import com.santo.vms.model.Visitor;
import com.santo.vms.model.VisitLog;

import java.util.List;
import java.util.Optional;

public interface VisitorService {
    Visitor createVisitor(VisitorDTO visitorDTO);

    Optional<Visitor> findVisitorById(String id);

    List<Visitor> getAllVisitors();

    VisitLog checkInVisitor(VisitorCheckInDTO visitorDTO);

    Optional<VisitLog> findVisitLogById(String id);

    long getPendingCheckInCount();

    long getRegisteredVisitorsCount();

    long getOverdueCheckOutCount();
}
