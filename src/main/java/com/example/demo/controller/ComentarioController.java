package com.example.demo.controller;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ComentarioRepo;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UsuarioRepo;
import com.example.demo.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/comment")
public class ComentarioController {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String date  = format.format(new Date());

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ComentarioService comentarioService;


    @GetMapping("/all")
    public List<Comentario> findAll(){
        return  comentarioRepo.findAll();
    }

    //POST Crear un COMMENT
    @PostMapping("/crear/post:{id_post}/user:{id_user}")
    public ResponseEntity<?> createComment(@RequestBody Comentario comentario, @PathVariable Long id_post, @PathVariable Long id_user) {
        if(comentario.getComentario().length() <= 200) {
            comentario.setFechaCreacion(date);

            Post post = postRepo.getOne(id_post);
            Usuario user = usuarioRepo.getOne(id_user);

            post.addComment(comentario);
            user.addComment(comentario);
            return new ResponseEntity<>(comentarioRepo.save(comentario), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Error Solo hasta 200 caracteres",HttpStatus.FORBIDDEN);
    }

    //BORRAR COMENTARIO POR ID
    @DeleteMapping(path = "/borrar/{id_com}")
    public ResponseEntity<?> borrarComment(@PathVariable Long id_com) {
        comentarioRepo.deleteById(id_com);
        return new ResponseEntity<>(id_com, HttpStatus.OK);
    }

    //MODIFICAR COMENTARIO POR ID
    @PutMapping(path = "/modificar/{id_com}")
    public Comentario modificarComentario(@PathVariable Long id_com, @RequestBody Comentario comentario){
        return comentarioService.PutByIdPost(id_com, comentario);
    }

    //TRAER LOS COMENTARIOS MAS NUEVOS
    @GetMapping(path = "/post:{id_post}/traer:{cant}")
    public List<Comentario> traerComentarios(@PathVariable int cant, @PathVariable Long id_post){
        return postRepo.getOne(id_post).getComentario().subList(
                ((postRepo.getOne(id_post).getComentario().size() - 1) + 1) - cant,
                (postRepo.getOne(id_post).getComentario().size() - 1) + 1);
    }

    @GetMapping(path = "/post:{id_post}/traer:")
    public List<Comentario> traerComentarios(@PathVariable Long id_post){
        return postRepo.getOne(id_post).getComentario();
    }





}
