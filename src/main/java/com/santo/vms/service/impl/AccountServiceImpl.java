package com.santo.vms.service.impl;

import com.santo.vms.dto.AccountDTO;
import com.santo.vms.model.Account;
import com.santo.vms.service.ifaces.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    @Override
    public Account createAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Account updateAccount(String id, AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Optional<Account> findAccountById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Account> getAllEmployees() {
        return null;
    }

    @Override
    public String deleteEmployee(String id) {
        return null;
    }
}
