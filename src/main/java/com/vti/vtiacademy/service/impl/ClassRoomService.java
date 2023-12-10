package com.vti.vtiacademy.service.impl;

import com.vti.vtiacademy.modal.dto.ClassRoomCreateRequest;
import com.vti.vtiacademy.modal.dto.ClassRoomSearch;
import com.vti.vtiacademy.modal.dto.ClassRoomUpdateRequest;
import com.vti.vtiacademy.modal.dto.SearchClassRoom;
import com.vti.vtiacademy.modal.entity.ClassRoom;
import com.vti.vtiacademy.repository.ClassRoomRepository;
import com.vti.vtiacademy.repository.specification.ClassRoomSpecification;
import com.vti.vtiacademy.service.IClassRoomService;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService implements IClassRoomService {
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Override
    public List<ClassRoom> getAll() {
        return classRoomRepository.findAll();
    }

    @Override
    public List<ClassRoom> getAllByName(SearchClassRoom request) {
//        if(request.getSize() == null){
//            return classRoomRepository.findAllByNameContains(request.getName());
//        }
//
//        return classRoomRepository.findAllByNameContainsAndSize(request.getName(), request.getSize());
//        return classRoomRepository.findAllBySizeBetween(request.getMinSize(), request.getMaxSize());
        return classRoomRepository.searchV2(request.getMinSize(), request.getMaxSize());
    }

    @Override
    public Page<ClassRoom> searchV1(SearchClassRoom request) {
        //Tạo ra đối tượng phân trang: Pageable
        Pageable pageable = Pageable.ofSize(request.getPageSize())
                .withPage(request.getPageNumber());
        return classRoomRepository.findAllByNameContains(request.getName(), pageable);
    }

    @Override
    public Page<ClassRoom> searchV2(ClassRoomSearch request) {
        //Bước 1: Tạo đối tượng phân trang và sắp xếp
        PageRequest pageRequest = Utils.buildPageRequest(request);
        //Bước 2: Tạo điều kiện tìm kiếm
        Specification<ClassRoom> specification = ClassRoomSpecification.buildCondition(request);
        //Bước 3: Trả về danh sách tìm kiếm
        return classRoomRepository.findAll(specification, pageRequest);
    }

    @Override
    public ClassRoom getById(long id) {
        Optional<ClassRoom> optionalClassRoom = classRoomRepository.findById(id);
        if(optionalClassRoom.isPresent()){
            return optionalClassRoom.get();
        }
        return null;
    }

    @Override
    public void delete(long id) {
        classRoomRepository.deleteById(id);
    }

    @Override
    public ClassRoom create(ClassRoomCreateRequest request) {
        ClassRoom classRoom = new ClassRoom();
        BeanUtils.copyProperties(request, classRoom);
        return classRoomRepository.save(classRoom);
    }

    @Override
    public ClassRoom update(ClassRoomUpdateRequest request) {
        ClassRoom classRoom = getById(request.getId());
        if(classRoom != null){
            BeanUtils.copyProperties(request, classRoom);
            return classRoomRepository.save(classRoom);
        }
        return null;
    }
}
