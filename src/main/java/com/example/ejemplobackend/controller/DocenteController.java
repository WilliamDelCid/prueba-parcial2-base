package com.example.ejemplobackend.controller;

import com.example.ejemplobackend.dto.DocenteDto;
import com.example.ejemplobackend.dto.Mensaje;
import com.example.ejemplobackend.entity.Docente;
import com.example.ejemplobackend.service.DocenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/docente")
public class DocenteController {

    private final DocenteService docenteService;

    @GetMapping("/listDocente")
    public ResponseEntity<Page<Docente>> list(Pageable pageable) {
        return ResponseEntity.ok(docenteService.findAll(pageable));
    }

    @GetMapping("/listAllDocente")
    public ResponseEntity<List<Docente>> listAllDocente( ) {
        return ResponseEntity.ok(docenteService.findAllDocente());
    }

    @GetMapping("/getDocenteById/{id}")
    public ResponseEntity<Docente> getDocenteById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(docenteService.getOne(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody DocenteDto dto) {
        return ResponseEntity.ok(docenteService.saveDocente(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@Valid @PathVariable("id") Long id,@Valid @RequestBody DocenteDto dto) {
        return ResponseEntity.ok(docenteService.updateDocente(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(docenteService.deleteDocente(id));
    }

}
