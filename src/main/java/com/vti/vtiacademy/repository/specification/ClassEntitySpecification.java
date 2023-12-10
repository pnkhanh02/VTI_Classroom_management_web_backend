package com.vti.vtiacademy.repository.specification;

import com.vti.vtiacademy.modal.dto.ClassEntitySearchRequest;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import com.vti.vtiacademy.modal.entity.Zoom;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class ClassEntitySpecification {
    //Hàm tổng -> tạo điều kiện

    public static Specification<ClassEntity> buildCondition(ClassEntitySearchRequest request){
        return Specification.where(findByClassName(request))
                .and(findByStartDate(request))
                .and(findByEndDate(request))
                .and(findByClassStatus(request))
                .and(findByTeachingForm(request))
                .and(findByZoomId(request));
    }

    public static Specification<ClassEntity> findByClassName(ClassEntitySearchRequest request){
        if (!Utils.isBlank(request.getClassName())){
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
                return cri.like(root.get("className"), "%" + request.getClassName() + "%");
            };
        } else {
            return null;
        }

    }

    public static Specification<ClassEntity> findByStartDate(ClassEntitySearchRequest request){
        if (request.getStartDateMin() != null){
            return (root, query, cri) -> {
                return cri.greaterThanOrEqualTo(root.get("startDate"), request.getStartDateMin());
            };
        }
        return null;
    }

    public static Specification<ClassEntity> findByEndDate(ClassEntitySearchRequest request){
        if (request.getEndDateMin() != null){
            return (root, query, cri) -> {
                return cri.greaterThanOrEqualTo(root.get("endDateMin"), request.getEndDateMin());
            };
        }
        return null;
    }

    public static Specification<ClassEntity> findByClassStatus(ClassEntitySearchRequest request){
        if (request.getClassStatus() != null){
            return (root, query, cri) -> {
                return cri.equal(root.get("classStatus"), request.getClassStatus());
            };
        }
        return null;
    }

    public static Specification<ClassEntity> findByTeachingForm(ClassEntitySearchRequest request){
        if (request.getTeachingForm() != null){
            return (root, query, cri) -> {
                return cri.equal(root.get("teachingForm"), request.getTeachingForm());
            };
        }
        return null;
    }

    public static Specification<ClassEntity> findByZoomId(ClassEntitySearchRequest request){
        if (request.getZoomId() != null){
            return (root, query, cri) -> {
                Join<ClassEntity, Zoom> joiner = root.join("zoom");
                return cri.equal(joiner.get("id"), request.getZoomId());
            };
        }
        return null;
    }
}
