package com.vti.vtiacademy.service;

import com.vti.vtiacademy.modal.dto.ZoomCreateRequest;
import com.vti.vtiacademy.modal.dto.ZoomSearchRequest;
import com.vti.vtiacademy.modal.dto.ZoomUpdateRequest;
import com.vti.vtiacademy.modal.entity.Zoom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IZoomService{
    List<Zoom> getAll();

    Page<Zoom> search(ZoomSearchRequest request);

    Zoom getById(long id);

    void delete(long id);

    Zoom create(ZoomCreateRequest request);

    Zoom update(ZoomUpdateRequest request);
}
