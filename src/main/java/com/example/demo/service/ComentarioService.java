package com.example.demo.service;


import com.example.demo.entity.Comentario;
import com.example.demo.repository.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ComentarioService {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private ComentarioRepo comentarioRepo;

    public Comentario PutByIdPost(Long id_com, Comentario comentario) {
        return this.comentarioRepo.findById(id_com).map(aa -> {
            aa.setComentario(comentario.getComentario());
            aa.setFechaCreacion(date);

            return this.comentarioRepo.save(aa);
        })
                .orElseGet(() ->{
                    comentario.setId(id_com);
                    return this.comentarioRepo.save(comentario);
                });
    }

}
