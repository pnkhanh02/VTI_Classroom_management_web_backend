package com.vti.vtiacademy.controller;

import com.vti.vtiacademy.exception.CustomException;
import com.vti.vtiacademy.exception.ErrorResponseEnum;
import com.vti.vtiacademy.modal.dto.LoginDto;
import com.vti.vtiacademy.modal.dto.LoginRequest;
import com.vti.vtiacademy.modal.dto.RegisterAccount;
import com.vti.vtiacademy.modal.entity.Account;
import com.vti.vtiacademy.modal.entity.Role;
import com.vti.vtiacademy.modal.entity.Status;
import com.vti.vtiacademy.repository.AccountRepository;
import com.vti.vtiacademy.service.impl.MailSenderService;
import com.vti.vtiacademy.utils.JWTTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
@Validated
public class AuthController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JWTTokenUtils jwtTokenUtils;

    @Autowired
    MailSenderService mailSenderService;

    @PostMapping("/login-v1") //Thực hiện theo cách này thì cần mở public API này ra
    public LoginDto login(@RequestParam String username,@RequestParam String password){
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if(accountOptional.isEmpty()){
            throw new CustomException(ErrorResponseEnum.USERNAME_NOT_FOUND);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account account = accountOptional.get();


        //So sánh password
        boolean checkPassword = passwordEncoder.matches(password, account.getPassword());
        if(!checkPassword){
            throw new CustomException(ErrorResponseEnum.PASSWORD_FAILS);
        }

        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
        return loginDto;
    }

    @PostMapping("/login-jwt") //Thực hiện theo cách này thì cần mở public API này ra
    public LoginDto loginJwt(@RequestBody LoginRequest request){
        Optional<Account> accountOptional = accountRepository.findByUsername(request.getUsername());
        if(accountOptional.isEmpty()){
            throw new CustomException(ErrorResponseEnum.USERNAME_NOT_FOUND);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account account = accountOptional.get();


        //So sánh password
        boolean checkPassword = passwordEncoder.matches(request.getPassword(), account.getPassword());
        if(!checkPassword){
            throw new CustomException(ErrorResponseEnum.PASSWORD_FAILS);
        }
        //Check account status
        if(account.getStatus() != Status.ACTIVE){
            throw new CustomException(ErrorResponseEnum.ACC_NOT_ACTIVE);
        }
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);

        //Tạo ra token để return
        String token = jwtTokenUtils.createAccessToken(loginDto);
        loginDto.setToken(token);
        return loginDto;
    }

    @GetMapping("/active/{id}")
    public String active(@PathVariable long id){
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isEmpty()){
            return "Người dùng không tồn tại";
        }
        Account account = accountOptional.get();
        account.setStatus(Status.ACTIVE);
        accountRepository.save(account);
        return "Tài khoản đã được kích hoạt";
    }

    @PostMapping("/register")
    public void registerAccount(@RequestBody RegisterAccount request) throws MessagingException {
        //Lưu các thông tin vào DB
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        account.setStatus(Status.PENDING);
        account.setRole(Role.STUDENT);
        String encodePass = new BCryptPasswordEncoder().encode(request.getPassword());
        account.setPassword(encodePass);

        account = accountRepository.save(account);

        //Tạo ra api kích hoạt
        // /api/v1/auth/active/userID {đoạn mã gắn liền với account}
        //đoạn mã được sinh ra ngẫu nhiên -> lưu vào 1 bảng trong DB (kèm theo thông tin user(userID, username,...)), đặt nó là khóa chính, unqique,...
        //

        String toMail = account.getEmail();
        String subject = "Kích hoạt tài khoản";
        String apiActive = "http://localhost:8686/api/v1/auth/active/" + account.getId();
        String text = "<div>\n" +
                "<h1>You have signed up account, click link below to activate account</h1>\n" +
                "<h3>\n" +
                "<a href=\"" + apiActive + "\" target=\"_blank\">Activate</a>\n" +
                "</h3>\n" +
                "\n" +
                "<img src=\"https://vtiacademy.edu.vn/upload/images/logo/academy-02-01-01-01.png\" alt=\"Girl in a jacket\" height=\"200\">\n" +
                "</div>";
        mailSenderService.sendMessageWithAttachment(toMail, subject, text);
    }
}
