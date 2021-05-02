package com.santo.vms.repository;

import com.santo.vms.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<Notification, String> {
}
