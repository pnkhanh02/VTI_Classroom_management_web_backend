package com.vti.vtiacademy.controller;

import com.vti.vtiacademy.modal.dto.ClassRoomCreateRequest;
import com.vti.vtiacademy.modal.dto.ClassRoomSearch;
import com.vti.vtiacademy.modal.dto.ClassRoomUpdateRequest;
import com.vti.vtiacademy.modal.dto.SearchClassRoom;
import com.vti.vtiacademy.modal.entity.ClassRoom;
import com.vti.vtiacademy.service.IClassRoomService;
import com.vti.vtiacademy.service.impl.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classRoom")
@CrossOrigin("*")
public class ClassRoomController {
    @Autowired
    private IClassRoomService classRoomService;

    @GetMapping("/getAll")
    public List<ClassRoom> getAll(){
        return classRoomService.getAll();
    }

    @PostMapping("/search-name")
    public List<ClassRoom> searchName(@RequestBody SearchClassRoom request){
        return classRoomService.getAllByName(request);
    }

    @PostMapping("/search-v1")
    public Page<ClassRoom> search(@RequestBody SearchClassRoom request){
        return classRoomService.searchV1(request);
    }

    @PostMapping("/search-v2")
    public Page<ClassRoom> searchV2(@RequestBody ClassRoomSearch request){
        return classRoomService.searchV2(request);
    }

    @GetMapping("/{id}")
    public ClassRoom getById (@PathVariable long id){
        return classRoomService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        classRoomService.delete(id);
    }

    @PostMapping("/create")
    public ClassRoom create(@RequestBody ClassRoomCreateRequest request){
        return  classRoomService.create(request);
    }

    @PutMapping("/update")
    public ClassRoom update(@RequestBody ClassRoomUpdateRequest request){
        return classRoomService.update(request);
    }
}
