package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

    List<Usuario> findByCiudad(String ciudad);

    List<Usuario> findByFechaCreacionAfter(String fecha);
}
