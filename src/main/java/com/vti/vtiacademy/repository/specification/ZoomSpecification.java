package com.vti.vtiacademy.repository.specification;

import com.vti.vtiacademy.modal.dto.ClassRoomSearch;
import com.vti.vtiacademy.modal.dto.ZoomSearchRequest;
import com.vti.vtiacademy.modal.entity.ClassRoom;
import com.vti.vtiacademy.modal.entity.Zoom;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class ZoomSpecification {
    public static Specification<Zoom> buildCondition(ZoomSearchRequest request){
        return Specification.where(buildConditionName(request));
    }

    public static Specification<Zoom> buildConditionName(ZoomSearchRequest request){
        if (!Utils.isBlank(request.getName())){
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
                // root: Chọn cột, field, để tìm kiếm (giá trị là thuộc tính trong java)
                // cri: CriteriaBuilder Khai báo loại so sánh dữ liệu. ( lớn hơn, nhỏ hơn, equal, like,.... )
                return cri.like(root.get("name"), "%" + request.getName() + "%");
            };
        }
        return null;
    }

    // Tạo thêm điều kiện để tìm kiếm
}
