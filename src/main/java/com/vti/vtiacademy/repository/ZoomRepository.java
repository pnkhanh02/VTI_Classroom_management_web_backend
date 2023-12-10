package com.vti.vtiacademy.repository;

import com.vti.vtiacademy.modal.entity.Zoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoomRepository extends JpaRepository<Zoom, Long>, JpaSpecificationExecutor<Zoom> {

}
