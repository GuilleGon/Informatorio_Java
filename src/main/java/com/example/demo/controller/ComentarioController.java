package com.example.demo.controller;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ComentarioRepo;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/comment")
public class ComentarioController {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;


    @GetMapping("/all")
    public List<Comentario> findAll(){
        return  comentarioRepo.findAll();
    }

    //POST Crear un COMMENT
    @PostMapping("/{id_post}/crear")
    public ResponseEntity<?> createComment(@RequestBody Comentario comentario, @PathVariable Long id_post) {
        comentario.setFechaCreacion(new Date());

        Post post = postRepo.getOne(id_post);
        Usuario user = usuarioRepo.ge

        post.addComment(comentario);
        user.addComment(comentario);
        return new ResponseEntity<>(comentarioRepo.save(comentario), HttpStatus.CREATED);
    }


    //2- Buscar autor del comentari
    // Long autorComentarioId = commentDTO.getIdAutor()
    // User user = userRepository.getOne(autorComentarioId)

    //3 - Crear Comment y asociar
    // Comment comment = new Comentario(...
    // setter
    // post.agregarComentario
    // user.agregarComentario
    //comentarioRepository.save(comment)


}
