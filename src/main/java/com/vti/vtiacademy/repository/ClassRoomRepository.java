package com.vti.vtiacademy.repository;

import com.vti.vtiacademy.modal.entity.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long>, JpaSpecificationExecutor<ClassRoom> {
    List<ClassRoom> findAllByNameContainsAndSize(String name, Integer size);

    List<ClassRoom> findAllByNameContains(String name);

    List<ClassRoom> findAllBySizeBetween(Integer sizeMin, Integer sizeMax);

    Page<ClassRoom> findAllByNameContains(String name, Pageable pageable);

    @Query(value = "SELECT * FROM  ra78.class_room where size between :minSize and :maxSize", nativeQuery = true)
    List<ClassRoom> searchV2(Integer minSize, Integer maxSize);
}
