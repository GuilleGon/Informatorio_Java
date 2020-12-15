package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/All")         //MOSTRAR TODO
    public List<Usuario> getAllUsuario(){
        return usuarioRepo.findAll();
    }

   /* @GetMapping("/nombre")    //MOSTRAR USUARIO POR NOMBRE
    public List<Usuario> getNombreUsuario(){
        return getAllUsuario().
    }*/


    @PostMapping            //CREAR USUARIO
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        usuario.setFechaCreacion(new Date());
        return new ResponseEntity<>(usuarioRepo.save(usuario), HttpStatus.CREATED);
    }

    //BORRAR USUARIO

}