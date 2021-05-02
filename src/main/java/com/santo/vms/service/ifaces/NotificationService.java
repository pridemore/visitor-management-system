package com.santo.vms.service.ifaces;

import com.santo.vms.dto.NotificationDTO;
import com.santo.vms.model.Notification;

public interface NotificationService {
 Notification createNotification(NotificationDTO notificationDTO);
}
