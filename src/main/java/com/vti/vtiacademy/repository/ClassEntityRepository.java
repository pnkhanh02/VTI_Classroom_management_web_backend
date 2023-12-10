package com.vti.vtiacademy.repository;

import com.vti.vtiacademy.modal.entity.Account;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Long>, JpaSpecificationExecutor<ClassEntity> {
    List<ClassEntity> findAllByZoom_Id(Long zoomId);

}
