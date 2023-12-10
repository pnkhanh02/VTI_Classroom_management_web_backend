package com.vti.vtiacademy.service;

import com.vti.vtiacademy.modal.dto.*;
import com.vti.vtiacademy.modal.entity.Account;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();

    List<Account> getAllMentor();

    Page<Account> search(AccountSearchRequest request);

    Account getById(long id);

    void delete(long id);

    Account create(AccountCreateReq request);

    Account update(AccountUpdateReq request);
}
