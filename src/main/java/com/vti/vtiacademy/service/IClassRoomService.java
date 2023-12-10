package com.vti.vtiacademy.service;

import com.vti.vtiacademy.modal.dto.ClassRoomCreateRequest;
import com.vti.vtiacademy.modal.dto.ClassRoomSearch;
import com.vti.vtiacademy.modal.dto.ClassRoomUpdateRequest;
import com.vti.vtiacademy.modal.dto.SearchClassRoom;
import com.vti.vtiacademy.modal.entity.ClassRoom;
import org.springframework.data.domain.Page;


import java.util.List;

public interface IClassRoomService {
    List<ClassRoom> getAll();

    List<ClassRoom> getAllByName(SearchClassRoom request);

    Page<ClassRoom> searchV1(SearchClassRoom request);

    Page<ClassRoom> searchV2(ClassRoomSearch request);

    ClassRoom getById(long id);

    void delete(long id);

    ClassRoom create(ClassRoomCreateRequest request);

    ClassRoom update(ClassRoomUpdateRequest request);
}
