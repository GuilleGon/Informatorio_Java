package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;


    //MODIFICAR POST POR ID
    public Post putById(Long id, Post post) {
        return this.postRepo.findById(id).map(aa -> {
            aa.setTitulo(post.getTitulo());
            aa.setDescripcion(post.getDescripcion());
            aa.setContenido(post.getContenido());
            aa.setFechaCreacion(new Date());
            aa.setPublicado(post.isPublicado());

            return this.postRepo.save(aa);
        })
                .orElseGet(() ->{
                    post.setId(id);
                    return this.postRepo.save(post);
                });
    }

}
