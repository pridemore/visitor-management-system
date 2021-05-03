package com.santo.vms.service.impl;

import com.santo.vms.dto.NotificationDTO;
import com.santo.vms.model.Notification;
import com.santo.vms.model.VisitLog;
import com.santo.vms.repository.NotificationRepository;
import com.santo.vms.repository.VisitLogRepository;
import com.santo.vms.service.ifaces.NotificationService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.enums.SystemConstants;
import com.santo.vms.utilities.util.GenerateKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    VisitLogRepository visitLogRepository;

    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {

        Optional<VisitLog> visitLog = visitLogRepository.findById(notificationDTO.getVisitLog().getId());

//        if(!visitLog.isPresent())
//            return ""

        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());
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

    @Override
    public Notification updateNotification(String id, NotificationDTO notificationDTO) {

        Optional<Notification> notification = notificationRepository.findById(id);


        notification.get().setMessage(notificationDTO.getMessage());
        notification.get().setVisitLog(notificationDTO.getVisitLog());
        notification.get().setLastUpdated(OffsetDateTime.now());
        Notification updatedNotification = notificationRepository.save(notification.get());

        return updatedNotification;
    }


    @Override
    public List<Notification> getAllUsers() {
        return notificationRepository.findAll();
    }

    @Override
    public String deleteUser(String id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if (!optionalNotification.isPresent())
            return SystemConstants.NOT_FOUND.getMessage();


        optionalNotification.get().setStatus(EntityStatus.DELETED.name());
        optionalNotification.get().setEntityStatus(EntityStatus.DELETED);
        notificationRepository.save(optionalNotification.get());
        return SystemConstants.DELETED.getMessage();
    }

}
