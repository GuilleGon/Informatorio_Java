package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class PostService {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private PostRepo postRepo;


    //MODIFICAR POST POR ID
    public Post putById(Long id, Post post) {
        return this.postRepo.findById(id).map(aa -> {
            aa.setTitulo(post.getTitulo());
            aa.setDescripcion(post.getDescripcion());
            aa.setContenido(post.getContenido());
            aa.setFechaCreacion(date);
            aa.setPublicado(post.isPublicado());

            return this.postRepo.save(aa);
        })
                .orElseGet(() ->{
                    post.setId(id);
                    return this.postRepo.save(post);
                });
    }

    /*public List<Post> findByTitulo(String titulo) {
        List<Post> post = postRepo.findAll();
        if (post.listIterator().next().getTitulo().contains(titulo)){
            return post.
        }
    }*/
}
