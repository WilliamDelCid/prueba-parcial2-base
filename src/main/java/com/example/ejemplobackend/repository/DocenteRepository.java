package com.example.ejemplobackend.repository;

import com.example.ejemplobackend.entity.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente,Long> {

    Page<Docente> findAll(Pageable pageable);
    boolean existsByNumeroUnico(String numeroUnico);
    Optional<Docente> findByNumeroUnico(String numero);
    boolean existsByTelefono(String telefono);
    boolean existsByCorreo(String correo);

}
