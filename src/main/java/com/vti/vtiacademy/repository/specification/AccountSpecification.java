package com.vti.vtiacademy.repository.specification;

import com.vti.vtiacademy.modal.dto.AccountSearchRequest;
import com.vti.vtiacademy.modal.entity.Account;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> buildCondition (AccountSearchRequest request){
        return Specification.where(findByUsername(request))
                .or(findByEmail(request))
                .or(findByFullName(request));
    }

    public static Specification<Account> findByUsername(AccountSearchRequest request){
        if (!Utils.isBlank(request.getName())){
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
                return cri.like(root.get("username"), "%" + request.getName() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Account> findByEmail(AccountSearchRequest request){
        if (!Utils.isBlank(request.getName())){
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
                return cri.like(root.get("email"), "%" + request.getName() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Account> findByFullName(AccountSearchRequest request){
        if (!Utils.isBlank(request.getName())){
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
                return cri.like(root.get("fullName"), "%" + request.getName() + "%");
            };
        } else {
            return null;
        }
    }
}
