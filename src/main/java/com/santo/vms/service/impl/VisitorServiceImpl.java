package com.santo.vms.service.impl;

import com.santo.vms.dto.VisitorCheckInDTO;
import com.santo.vms.dto.VisitorDTO;
import com.santo.vms.enums.CheckInStatus;
import com.santo.vms.model.Visitor;
import com.santo.vms.model.VisitLog;
import com.santo.vms.repository.EmployeeRepository;
import com.santo.vms.repository.VisitorRepository;
import com.santo.vms.repository.VisitLogRepository;
import com.santo.vms.service.ifaces.VisitorService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.util.GenerateKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    VisitLogRepository visitLogRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Visitor createVisitor(VisitorDTO visitorDTO) {
        Visitor visitor = new Visitor();
        visitor.setId(GenerateKey.generateEntityId());
        visitor.setEntityStatus(EntityStatus.ACTIVE);
        visitor.setStatus("ACTIVE");
        visitor.setDateCreated(OffsetDateTime.now());
        visitor.setEntityVersion(1L);
        visitor.setDob(LocalDate.now());
        visitor.setSignature("VMS");
        visitor.setName(visitorDTO.getName());
        visitor.setSurname(visitorDTO.getSurname());
        visitor.setNationalId(visitorDTO.getNationalId());
        visitor.setGender(visitorDTO.getGender());
        visitor.setPhoneNo(visitorDTO.getPhoneNo());
        visitor.setEmail(visitorDTO.getEmail());
        Visitor savedVisitor = visitorRepository.save(visitor);
        return savedVisitor;
    }

    @Override
    public Optional<Visitor> findVisitorById(String id) {
        return visitorRepository.findById(id);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public VisitLog checkInVisitor(VisitorCheckInDTO visitorDTO) {
        VisitLog visitLog = new VisitLog();
        visitLog.setEmployee(visitorDTO.getEmployee());
        visitLog.setVisitor(visitorDTO.getVisitor());
        visitLog.setPurpose(visitorDTO.getPurpose());
        visitLog.setEndTime(visitorDTO.getEndTime());
        visitLog.setStartTime(visitorDTO.getStartTime());
        visitLog.setAccompaniedBy(visitLog.getAccompaniedBy());
        visitLog.setVisitUntil(visitorDTO.getVisitUntil());
        visitLog.setVisitDate(visitorDTO.getVisitDate());
        visitLog.setRepeatDays(visitorDTO.getRepeatDays());
        visitLog.setRepeatVisit(visitorDTO.isRepeatVisit());
        visitLog.setCheckInTime(OffsetDateTime.now());
        visitLog.setCheckOutTime(null);
        visitLog.setId(GenerateKey.generateEntityId());
        visitLog.setEntityStatus(EntityStatus.ACTIVE);
        visitLog.setCheckInStatus(CheckInStatus.PENDING_HOST_APPROVAL);
        visitLog.setDateCreated(OffsetDateTime.now());
        visitLog.setEntityVersion(1L);

        final VisitLog savedVisitLog = visitLogRepository.save(visitLog);
        return savedVisitLog;
    }

    @Override
    public Optional<VisitLog> findVisitLogById(String id) {
        return visitLogRepository.findById(id);
    }

    @Override
    public long getPendingCheckInCount() {
        return visitLogRepository.countVisitLogByCheckInStatus(CheckInStatus.PENDING_HOST_APPROVAL);
    }

    @Override
    public long getRegisteredVisitorsCount() {
        return visitorRepository.countVisitorByEntityStatus(EntityStatus.ACTIVE);
    }

    @Override
    public long getOverdueCheckOutCount() {
        return visitLogRepository.countVisitLogByCheckInStatus(CheckInStatus.OVERDUE);
    }
}
