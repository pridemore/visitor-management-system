package com.santo.vms.service.ifaces;

import com.santo.vms.dto.NotificationDTO;
import com.santo.vms.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
 Notification createNotification(NotificationDTO notificationDTO);
 Optional<Notification> findNotificationById(String id);
 Notification updateNotification(String id, NotificationDTO notificationDTO);
 List<Notification> getAllUsers();
 String deleteUser(String id);

}
