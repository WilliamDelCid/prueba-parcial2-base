package com.example.ejemplobackend.service;

import com.example.ejemplobackend.dto.DocenteDto;
import com.example.ejemplobackend.dto.Mensaje;
import com.example.ejemplobackend.entity.Docente;
import com.example.ejemplobackend.exception.CustomException;
import com.example.ejemplobackend.repository.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;

    public Page<Docente> findAll(Pageable pageable){
        return this.docenteRepository.findAll(pageable);
    }

    public List<Docente> findAllDocente(){
        return this.docenteRepository.findAll();
    }

    public Docente getOne(Long id){
        return docenteRepository.findById(id).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND,"Ese docente no existe"));
    }

    @Transactional
    public Mensaje saveDocente(DocenteDto docenteDto){
        if (docenteRepository.existsByNumeroUnico(docenteDto.numeroUnico())) {
            throw new CustomException(HttpStatus.CONFLICT, "Ese docente ya existe");
        }
        if (docenteRepository.existsByTelefono(docenteDto.telefono())) {
            throw new CustomException(HttpStatus.CONFLICT, "Ese numero telefonico ya existe");
        }
        if (docenteRepository.existsByCorreo(docenteDto.correo())) {
            throw new CustomException(HttpStatus.CONFLICT, "Ese correo ya existe");
        }
        Docente docente = new Docente(docenteDto.nombre(),docenteDto.numeroUnico(),docenteDto.especialidad(), docenteDto.institucion(), docenteDto.correo(), docenteDto.telefono());
        docenteRepository.save(docente);
        return new Mensaje(docente.getNombre() + " ha sido creada");
    }

    @Transactional
    public Mensaje updateDocente(Long id,DocenteDto docenteDto){
        Docente docente = getOne(id);
        if (!docente.getNumeroUnico().equals(docenteDto.numeroUnico())) {
            Optional<Docente> docenteOptionalNumeroUnico = docenteRepository.findByNumeroUnico(docenteDto.numeroUnico());
            if (docenteOptionalNumeroUnico.isPresent() && docenteOptionalNumeroUnico.get().getId() != id) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Ese numeroUnico ya existe");
            }
            docente.setNumeroUnico(docenteDto.numeroUnico());
        }
        docente.setNombre(docenteDto.nombre());
        docente.setEspecialidad(docenteDto.especialidad());
        docente.setInstitucion(docenteDto.institucion());
        docente.setCorreo(docenteDto.correo());
        docente.setTelefono(docenteDto.telefono());
        docenteRepository.save(docente);
        return new Mensaje(docente.getNombre() + " ha sido actualizado");
    }

    @Transactional
    public Mensaje deleteDocente(Long id) {
        Docente docente = getOne(id);
        docenteRepository.deleteById(id);
        return new Mensaje(docente.getNombre() + " ha sido eliminada");
    }

}
