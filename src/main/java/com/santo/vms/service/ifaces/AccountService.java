package com.santo.vms.service.ifaces;

import com.santo.vms.dto.AccountDTO;
import com.santo.vms.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(AccountDTO accountDTO);
    Account updateAccount(String id,AccountDTO accountDTO);
    Optional<Account> findAccountById(String id);
    List<Account> getAllEmployees();
    String deleteEmployee(String id);
}
