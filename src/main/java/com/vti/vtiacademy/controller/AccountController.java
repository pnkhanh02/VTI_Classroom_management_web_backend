package com.vti.vtiacademy.controller;

import com.vti.vtiacademy.modal.dto.*;
import com.vti.vtiacademy.modal.entity.Account;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import com.vti.vtiacademy.service.IAccountService;
import com.vti.vtiacademy.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/getAll")
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/getAll-mentor")
    public List<Account> getAllMentor(){
        return accountService.getAllMentor();
    }

    @PostMapping("/search")
    public Page<Account> search(@RequestBody AccountSearchRequest request){
        return accountService.search(request);
    }

    @GetMapping("/{id}")
    public Account getById (@PathVariable long id){
        return accountService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        accountService.delete(id);
    }

    @PostMapping("/create")
    public Account create(@RequestBody AccountCreateReq request){
        return  accountService.create(request);
    }

    @PutMapping("/update")
    public Account update(@RequestBody AccountUpdateReq request){
        return accountService.update(request);
    }
}
