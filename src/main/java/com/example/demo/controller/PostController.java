package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UsuarioRepo;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private PostService postService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/ver/false")
    public List<Post> getAllPosts() {
        return postRepo.findAllByPublicado(false);
    }

    //MOSTRAR TODOS LOS POSTS

    @GetMapping("/all")
    public List<Post> getAllPost() {
        return postRepo.findAll();
    }


    //BUSCAR POR TITULO---------------------------------------------------------------------------------------
    @GetMapping(path = "/geT/{titulo}")
    public List<Post> getTitulos(@PathVariable String titulo){
        return postRepo.findByTituloContaining(titulo);
    }


    //CREAR POST
    @PostMapping("/user:{user_id}/crear")
    public ResponseEntity<?> crearPost(@PathVariable Long user_id, @RequestBody Post post){
        post.setFechaCreacion(date);
        Usuario user = usuarioRepo.getOne(user_id);
        user.addPost(post);
        return new ResponseEntity<>(postRepo.save(post), HttpStatus.CREATED);
    }

    //MODIFICAR POST POR ID
    @PutMapping(path = "/modificar/post:{id}")
    public Post putPost(@PathVariable Long id, @RequestBody Post post){
        return postService.putById(id,post);
    }


    //BORRAR POST POR ID
    @DeleteMapping(path = "/borrar/post:{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postRepo.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }




}
