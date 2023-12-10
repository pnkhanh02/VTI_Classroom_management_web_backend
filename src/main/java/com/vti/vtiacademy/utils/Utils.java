package com.vti.vtiacademy.utils;

import com.vti.vtiacademy.modal.dto.SearchBase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Utils {
    public static boolean isBlank(String text){ //null, ""
        return (text == null || "".equals(text));
    }

    public static PageRequest buildPageRequest(SearchBase searchBase){
        if ("DESC".equals(searchBase.getSortType())){
            // Giá trị page mà thư viện mong muốn để vào trang đầu tiên: 0
            // Giá trị mình muốn để lấy trang đầu tiên: 1 - 1
            return PageRequest.of(searchBase.getPageNumber(), searchBase.getPageSize(), Sort.by(searchBase.getSortField()).descending());
        } else {
            return PageRequest.of(searchBase.getPageNumber(), searchBase.getPageSize(), Sort.by(searchBase.getSortField()).ascending());
        }

    }
}
