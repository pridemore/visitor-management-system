package com.santo.vms.service.impl;

import com.santo.vms.dto.NotificationDTO;
import com.santo.vms.model.Employee;
import com.santo.vms.model.Notification;
import com.santo.vms.model.VisitLog;
import com.santo.vms.repository.NotificationRepository;
import com.santo.vms.repository.VisitLogRepository;
import com.santo.vms.service.ifaces.NotificationService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.util.GenerateKey;
import org.jboss.jdeparser.FormatPreferences;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.Optional;

public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    VisitLogRepository visitLogRepository;

    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {

        Optional<VisitLog> visitLog=visitLogRepository.findById(notificationDTO.getVisitLog().getId());

//        if(!visitLog.isPresent())
//            return ""

        Notification notification = new Notification();

        notification.setStatus("ACTIVE");
        notification.setStatus(notificationDTO.getMessage());
        notification.setVisitLog(notificationDTO.getVisitLog());
        notification.setDateCreated(OffsetDateTime.now());
        notification.setId(GenerateKey.generateEntityId());
        Notification savedNotification = notificationRepository.save(notification);

        return savedNotification;
    }

    @Override
    public Optional<Notification> findNotificationById(String id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        return optionalNotification;
    }



}
