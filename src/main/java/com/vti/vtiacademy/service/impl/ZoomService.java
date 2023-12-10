package com.vti.vtiacademy.service.impl;

import com.vti.vtiacademy.modal.dto.ZoomCreateRequest;
import com.vti.vtiacademy.modal.dto.ZoomSearchRequest;
import com.vti.vtiacademy.modal.dto.ZoomUpdateRequest;
import com.vti.vtiacademy.modal.entity.ClassRoom;
import com.vti.vtiacademy.modal.entity.Zoom;
import com.vti.vtiacademy.repository.ZoomRepository;
import com.vti.vtiacademy.repository.specification.ClassRoomSpecification;
import com.vti.vtiacademy.repository.specification.ZoomSpecification;
import com.vti.vtiacademy.service.IZoomService;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoomService implements IZoomService {
    @Autowired
    private ZoomRepository zoomRepository;

    @Override
    public List<Zoom> getAll() {
        return zoomRepository.findAll();
    }

    @Override
    public Page<Zoom> search(ZoomSearchRequest request) {
        PageRequest pageRequest = Utils.buildPageRequest(request);
        Specification<Zoom> specification = ZoomSpecification.buildCondition(request);
        return zoomRepository.findAll(specification, pageRequest);
    }

    @Override
    public Zoom getById(long id) {
        Optional<Zoom> optionalZoom = zoomRepository.findById(id);
        if(optionalZoom.isPresent()){
            return optionalZoom.get();
        }
        return null;
    }

    @Override
    public void delete(long id) {
        zoomRepository.deleteById(id);
    }

    @Override
    public Zoom create(ZoomCreateRequest request) {
        Zoom zoom = new Zoom();
        BeanUtils.copyProperties(request, zoom);
        return zoomRepository.save(zoom);
    }

    @Override
    public Zoom update(ZoomUpdateRequest request) {
        Zoom zoom = getById(request.getId());
        if(zoom != null){
            BeanUtils.copyProperties(request, zoom);
            return zoomRepository.save(zoom);
        }
        return null;
    }


}
