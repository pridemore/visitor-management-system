package com.santo.vms.repository;

import com.santo.vms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {
}
