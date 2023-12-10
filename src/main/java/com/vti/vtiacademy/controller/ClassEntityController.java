package com.vti.vtiacademy.controller;

import com.vti.vtiacademy.modal.dto.*;
import com.vti.vtiacademy.modal.entity.ClassEntity;
import com.vti.vtiacademy.modal.entity.Zoom;
import com.vti.vtiacademy.service.IClassEntityService;
import com.vti.vtiacademy.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classEntity")
@CrossOrigin("*")
public class ClassEntityController {
    @Autowired
    private IClassEntityService classEntityService;

    @GetMapping("/getAll")
    public List<ClassEntity> getAll(){
        return classEntityService.getAll();
    }

    @PostMapping("/search")
    public Page<ClassEntity> search(@RequestBody ClassEntitySearchRequest request){
        return classEntityService.search(request);
    }

    @GetMapping("/{id}")
    public ClassEntity getById (@PathVariable long id){
        return classEntityService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        classEntityService.delete(id);
    }

    @PostMapping("/create")
    public ClassEntity create(@RequestBody ClassEntityCreateReq request){
        return  classEntityService.create(request);
    }

    @PutMapping("/update")
    public ClassEntity update(@RequestBody ClassEntityUpdateReq request){
        return classEntityService.update(request);
    }
}
