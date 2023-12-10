package com.vti.vtiacademy.service.impl;

import com.vti.vtiacademy.modal.dto.ClassEntityCreateReq;
import com.vti.vtiacademy.modal.dto.ClassEntitySearchRequest;
import com.vti.vtiacademy.modal.dto.ClassEntityUpdateReq;
import com.vti.vtiacademy.modal.entity.Account;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import com.vti.vtiacademy.modal.entity.ClassRoom;
import com.vti.vtiacademy.modal.entity.Zoom;
import com.vti.vtiacademy.repository.AccountRepository;
import com.vti.vtiacademy.repository.ClassEntityRepository;
import com.vti.vtiacademy.repository.ClassRoomRepository;
import com.vti.vtiacademy.repository.ZoomRepository;
import com.vti.vtiacademy.repository.specification.ClassEntitySpecification;
import com.vti.vtiacademy.service.IClassEntityService;
import com.vti.vtiacademy.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassEntityService implements IClassEntityService {
    @Autowired
    private ClassEntityRepository classEntityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ZoomRepository zoomRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public List<ClassEntity> getAll() {
        return classEntityRepository.findAll();
    }

    @Override
    public Page<ClassEntity> search(ClassEntitySearchRequest request) {
        //Bước 1: Tạo đối tượng phân trang và sắp xếp
        PageRequest pageRequest = Utils.buildPageRequest(request);
        //Bước 2: Tạo điều kiện tìm kiếm
        Specification<ClassEntity> specification = ClassEntitySpecification.buildCondition(request);
        return classEntityRepository.findAll(specification, pageRequest);
    }

    @Override
    public ClassEntity getById(long id) {
        return classEntityRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        classEntityRepository.deleteById(id);
    }

    @Override
    public ClassEntity create(ClassEntityCreateReq request) {
        ClassEntity classEntity = new ClassEntity();
        BeanUtils.copyProperties(request, classEntity);

        //Gọi sang bên Account -> lấy giá trị
        Account account = accountRepository.findById(request.getAccountId()).get();

        Zoom zoom = zoomRepository.findById(request.getZoomId()).get();

        ClassRoom classRoom = classRoomRepository.findById(request.getClassRoomId()).get();

        //Set các đối tượng khóa ngoại vào classEntity
        classEntity.setMentor(account);
        classEntity.setZoom(zoom);
        classEntity.setClassRoom(classRoom);
        return classEntityRepository.save(classEntity);
    }

    @Override
    public ClassEntity update(ClassEntityUpdateReq request) {
        ClassEntity classEntity = classEntityRepository.findById(request.getId()).get();
        BeanUtils.copyProperties(request, classEntity);

        //Gọi sang bên Account -> lấy giá trị
        Account account = accountRepository.findById(request.getAccountId()).get();

        Zoom zoom = zoomRepository.findById(request.getZoomId()).get();

        ClassRoom classRoom = classRoomRepository.findById(request.getClassRoomId()).get();

        //Set các đối tượng khóa ngoại vào classEntity
        classEntity.setMentor(account);
        classEntity.setZoom(zoom);
        classEntity.setClassRoom(classRoom);
        return classEntityRepository.save(classEntity);
    }
}
