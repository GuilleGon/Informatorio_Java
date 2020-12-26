package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String pass;
    @Column(name="fecha")
    private String fechaCreacion;
    private String ciudad;
    private String provincia;
    private String pais;

    @OneToMany(mappedBy = "autor", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Post> post;

    public List<Post> getPosts(){
        return post;
    }

    public void setPosts(List<Post> post){
        this.post = post;
    }

    @OneToMany(mappedBy = "autor", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Comentario> comentario;

    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void addPost(Post post){
        this.post.add(post);
        post.setAutor(this);
    }

    public void addComment(Comentario comment){
        this.comentario.add(comment);
        comment.setAutor(this);
    }


}
