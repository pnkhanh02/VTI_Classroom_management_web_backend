package com.vti.vtiacademy.repository.specification;

import com.vti.vtiacademy.modal.dto.ClassRoomSearch;
import com.vti.vtiacademy.modal.entity.ClassRoom;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class ClassRoomSpecification {

    public static Specification<ClassRoom> buildCondition(ClassRoomSearch request){
        return Specification.where(buildConditionName(request))
                .and(buildConditionAddress(request))
                .and(buildConditionSize(request));
    }

    public static Specification<ClassRoom> buildConditionName(ClassRoomSearch request){
        if (!Utils.isBlank(request.getName())){
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
            // root: Chọn cột, field, để tìm kiếm (giá trị là thuộc tính trong java)
            // cri: CriteriaBuilder Khai báo loại so sánh dữ liệu. ( lớn hơn, nhỏ hơn, equal, like,.... )
                return cri.like(root.get("name"), "%" + request.getName() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<ClassRoom> buildConditionAddress(ClassRoomSearch request){
        if (!Utils.isBlank(request.getAddress())){
            return (root, query, cri) -> {
                return cri.like(root.get("address"), "%" + request.getAddress() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<ClassRoom> buildConditionSize(ClassRoomSearch request){
        if (request.getMinSize() != null && request.getMaxSize() != null){
            return (root, query, cri) -> {
                return cri.between(root.get("size"), request.getMinSize(), request.getMaxSize());
            };
        } else {
            return null;
        }
    }

}
