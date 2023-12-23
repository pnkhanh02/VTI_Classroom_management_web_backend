package com.vti.vtiacademy.controller;

import com.vti.vtiacademy.modal.dto.ZoomCreateRequest;
import com.vti.vtiacademy.modal.dto.ZoomSearchRequest;
import com.vti.vtiacademy.modal.dto.ZoomUpdateRequest;
import com.vti.vtiacademy.modal.entity.Zoom;
import com.vti.vtiacademy.service.IZoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/zoom")
@CrossOrigin("*")
@Validated
public class ZoomController {
    @Autowired
    private IZoomService zoomService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public List<Zoom> getAll(){
        return zoomService.getAll();
    }

//    @PreAuthorize("hasAuthority('MENTOR')")
    @PostMapping("/search")
    public Page<Zoom> search(@RequestBody ZoomSearchRequest request){
        return zoomService.search(request);
    }

    @GetMapping("/{id}")
    public Zoom getById (@PathVariable long id){
        return zoomService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        zoomService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public Zoom create(@RequestBody @Valid ZoomCreateRequest request){
        return  zoomService.create(request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public Zoom update(@RequestBody ZoomUpdateRequest request){
        return zoomService.update(request);
    }
}
