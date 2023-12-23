package com.vti.vtiacademy.service.impl;

import com.vti.vtiacademy.exception.CustomException;
import com.vti.vtiacademy.exception.ErrorResponseEnum;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //Kiểm tra tất cả các dữ liệu đầu vào
        //Nếu không thỏa mãn -> throw ra lỗi
        String regex = "^[a-zA-Z0-9]*$";
        boolean checkedUserName = request.getUsername().matches(regex);
        if(!checkedUserName){
            throw new CustomException(ErrorResponseEnum.USERNAME_EXISTED);
        }


        if(accountRepository.existsByUsername(request.getUsername())){
            throw new CustomException(ErrorResponseEnum.USERNAME_EXISTED);
        }

        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        //Set lại mật khẩu đã được mã hóa
        account.setPassword(passwordEncoder.encode(request.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if(accountOptional.isPresent()){
            List<GrantedAuthority> authorities = new ArrayList<>();
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(accountOptional.get().getRole().toString());

            authorities.add(accountOptional.get().getRole());
            return new User(username, accountOptional.get().getPassword(), authorities);
        }else{
            throw new UsernameNotFoundException(username);
        }

    }
}
