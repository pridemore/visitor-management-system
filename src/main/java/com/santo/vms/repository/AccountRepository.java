package com.santo.vms.repository;

import com.santo.vms.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account, String> {
}
