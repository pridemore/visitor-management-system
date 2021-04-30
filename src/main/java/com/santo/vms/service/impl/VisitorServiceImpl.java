package com.santo.vms.service.impl;

import com.santo.vms.dto.VisitorDTO;
import com.santo.vms.model.Visitor;
import com.santo.vms.repository.VisitorRepository;
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
}
