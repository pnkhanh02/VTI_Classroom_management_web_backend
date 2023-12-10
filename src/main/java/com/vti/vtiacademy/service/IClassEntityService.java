package com.vti.vtiacademy.service;

import com.vti.vtiacademy.modal.dto.*;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import com.vti.vtiacademy.modal.entity.Zoom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClassEntityService {
    List<ClassEntity> getAll();

    Page<ClassEntity> search(ClassEntitySearchRequest request);

    ClassEntity getById(long id);

    void delete(long id);

    ClassEntity create(ClassEntityCreateReq request);

    ClassEntity update(ClassEntityUpdateReq request);
}
