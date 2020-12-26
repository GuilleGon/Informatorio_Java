package com.example.demo.service;


import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UsuarioService {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private UsuarioRepo usuarioRepo;

    //MODIFICAR USUARIO POR ID
    public Usuario putById(Long id, Usuario usuario) {
        return this.usuarioRepo.findById(id).map(aa -> {
            aa.setNombre(usuario.getNombre());
            aa.setApellido(usuario.getApellido());
            aa.setEmail(usuario.getEmail());
            aa.setFechaCreacion(date);
            aa.setPass(usuario.getPass());
            aa.setCiudad(usuario.getCiudad());
            aa.setProvincia(usuario.getProvincia());
            aa.setPais(usuario.getPais());
            return this.usuarioRepo.save(aa);
        })
                .orElseGet(() ->{
                    usuario.setId(id);
                    return this.usuarioRepo.save(usuario);
                });
    }


}
