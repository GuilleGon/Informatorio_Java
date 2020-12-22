package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UsuarioRepo;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostRepo postRepo;


    //MOSTRAR TODOS LOS USUARIOS

    @GetMapping("/All")
    public List<Usuario> getAllUsuario(){
        return usuarioRepo.findAll();
    }

    //MOSTRAR USUARIO POR CIUDAD

    @GetMapping("/{ciudad}")
    public List<Usuario> getUsuarioByCiudad(@PathVariable(value = "ciudad") String ciudad){
        return usuarioRepo.findByCiudad(ciudad);
    }

    //MOSTRAR DESPUES POR FECHA

    @GetMapping("/fecha")
    public List<Usuario> getUsuarioByFecha(@DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaAlta){
        return usuarioRepo.findByFechaCreacion(fechaAlta);
    }


    //CREAR USUARIO (ID Y FECHA AUTOGENERADA)

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        usuario.setFechaCreacion(new Date());
        return new ResponseEntity<>(usuarioRepo.save(usuario), HttpStatus.CREATED);
    }

    //BORRAR USUARIO POR ID

    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable Long id){
        usuarioRepo.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //MODIFICAR USUARIO POR ID
    @PutMapping(path = "/put/{id}")
    public Usuario putUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.putById(id, usuario);
    }



}