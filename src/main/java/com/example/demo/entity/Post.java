package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bytecode.Removal;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String contenido;
    @Column(name = "fecha")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaCreacion;
    private boolean publicado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autorPost", referencedColumnName = "id")
    private Usuario autor;

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario usuario) {
        this.autor = usuario;
    }




    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Comentario> comentarios;

    public List<Comentario> getComentario() {
        return comentarios;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentarios = comentario;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public void addComment(Comentario comment){
        this.comentarios.add(comment);
        comment.setPost(this);
    }


}
