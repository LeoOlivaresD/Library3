package com.duoc.library3.entities;

public class Libro {
    
    //Atributos
    private String titulo;
    private String autor;
    private Boolean disponibilidad;
    private Integer idLibro;

    //Constructores
    public Libro() {
    }

    public Libro(String titulo, String autor, Boolean disponibilidad, Integer idLibro) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponibilidad = disponibilidad;
        this.idLibro = idLibro;
    }

    //Getters & Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", idLibro=" + idLibro + '}';
    }
    
}
