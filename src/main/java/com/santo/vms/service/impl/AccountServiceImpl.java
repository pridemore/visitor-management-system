package com.santo.vms.service.impl;

import com.santo.vms.dto.AccountDTO;
import com.santo.vms.model.Account;
import com.santo.vms.repository.AccountRepository;
import com.santo.vms.service.ifaces.AccountService;
import com.santo.vms.utilities.enums.EntityStatus;
import com.santo.vms.utilities.util.GenerateKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(AccountDTO accountDTO) {

        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setStatus("ACTIVE");
        account.setEntityStatus(EntityStatus.ACTIVE);
        account.setDateCreated(OffsetDateTime.now());
        account.setId(GenerateKey.generateEntityId());
        Account savedAccount = accountRepository.save(account);

        return savedAccount;
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
