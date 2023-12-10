package com.vti.vtiacademy.service.impl;

import com.vti.vtiacademy.modal.dto.AccountCreateReq;
import com.vti.vtiacademy.modal.dto.AccountSearchRequest;
import com.vti.vtiacademy.modal.dto.AccountUpdateReq;
import com.vti.vtiacademy.modal.entity.Account;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import com.vti.vtiacademy.modal.entity.Role;
import com.vti.vtiacademy.repository.AccountRepository;
import com.vti.vtiacademy.repository.ClassEntityRepository;
import com.vti.vtiacademy.repository.specification.AccountSpecification;
import com.vti.vtiacademy.repository.specification.ClassEntitySpecification;
import com.vti.vtiacademy.service.IAccountService;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClassEntityRepository classEntityRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAllMentor() {
        return accountRepository.findAllByRole(Role.MENTOR);
    }

    @Override
    public Page<Account> search(AccountSearchRequest request) {
        //Bước 1: Tạo đối tượng phân trang và sắp xếp
        PageRequest pageRequest = Utils.buildPageRequest(request);
        //Bước 2: Tạo điều kiện tìm kiếm
        Specification<Account> specification = AccountSpecification.buildCondition(request);
        return accountRepository.findAll(specification, pageRequest);
    }

    @Override
    public Account getById(long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account create(AccountCreateReq request) {
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        //Kiểm tra Account có role là STUDENT ko, nếu không phải STUDENT -> kiểm tra có truyền classEntityId -> bắn lỗi
        //nếu có -> sẽ tìm ra đối tượng ClassEntity theo classEntityId từ request
        if(account.getRole() != Role.STUDENT){
            //Kiểm tra classEntityId có giá trị hay không
            if(request.getClassEntityId() != null){
                //Bắn ra lỗi
            }
        }else{
            //Trường hợp Account là STUDENT
            //-> Tìm đối tượng ClassEntity theo id truyền vào
            classEntityRepository.findById(request.getClassEntityId());
        }
        return accountRepository.save(account);
    }

    @Override
    public Account update(AccountUpdateReq request) {
        Account account = accountRepository.findById(request.getAccountId()).get();
        BeanUtils.copyProperties(request, account);
        //Kiểm tra Account có role là STUDENT ko, nếu không phải STUDENT -> kiểm tra có truyền classEntityId -> bắn lỗi
        //nếu có -> sẽ tìm ra đối tượng ClassEntity theo classEntityId từ request
        if(account.getRole() != Role.STUDENT){
            //Kiểm tra classEntityId có giá trị hay không
            if(request.getClassEntityId() != null){
                //Bắn ra lỗi
            }else{
                //Trường hợp Account là STUDENT
                //-> Tìm đối tượng ClassEntity theo id truyền vào
                classEntityRepository.findById(request.getClassEntityId());
            }
        }
        return accountRepository.save(account);
    }
}
