package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UsuarioRepo;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostRepo postRepo;

    public UsuarioController() throws ParseException {
    }


    //MOSTRAR TODOS LOS USUARIOS

    @GetMapping("/all")
    public List<Usuario> getAllUsuario(){
        return usuarioRepo.findAll();
    }

    //MOSTRAR USUARIO POR CIUDAD

    @GetMapping("/ciudad/{ciudad}")
    public List<Usuario> getUsuarioByCiudad(@PathVariable(value = "ciudad") String ciudad){
        return usuarioRepo.findByCiudad(ciudad);
    }

    //MOSTRAR DESPUES POR FECHA

    @GetMapping("/fecha/{fechaAlta}")
    public List<Usuario> getUsuarioByFecha(@PathVariable String fechaAlta) {
        return usuarioRepo.findByFechaCreacionAfter(fechaAlta);
    }


    //CREAR USUARIO (ID Y FECHA AUTOGENERADA)

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        usuario.setFechaCreacion(date);
        return new ResponseEntity<>(usuarioRepo.save(usuario), HttpStatus.CREATED);
    }

    //BORRAR USUARIO POR ID

    @DeleteMapping(value = "/borrar/user:{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable Long id){
        usuarioRepo.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //MODIFICAR USUARIO POR ID
    @PutMapping(path = "/modificar/user:{id}")
    public Usuario putUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.putById(id, usuario);
    }


}