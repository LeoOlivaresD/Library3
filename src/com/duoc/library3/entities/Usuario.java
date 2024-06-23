package com.duoc.library3.entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario> {

    //Atrbutos
    private Integer idUsuario;
    private String nombre;
    private String residencia;
    private List<Libro> listaLibrosAlquilados = new ArrayList<>();

    //Contructores
    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombre, String residencia) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.residencia = residencia;
        this.listaLibrosAlquilados = new ArrayList<>();
    }

    //Getters & Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public List<Libro> getListaLibrosAlquilados() {
        return listaLibrosAlquilados;
    }

    public void setListaLibrosAlquilados(List<Libro> listaLibrosAlquilados) {
        this.listaLibrosAlquilados = listaLibrosAlquilados;
    }

    @Override
    public String toString() {
        // Utilizo un StringBuilder para construir la cadena de texto de manera eficiente.
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{idUsuario=").append(idUsuario)
                .append(", nombre=").append(nombre)
                .append(", residencia=").append(residencia)
                .append(", librosAlquilados=[");
        // Itero sobre la lista de libros alquilados y llamo al método 
        // toString de cada libro para añadir su información a la cadena
        for (int i = 0; i < listaLibrosAlquilados.size(); i++) {
            sb.append(listaLibrosAlquilados.get(i).toString());
            if (i < listaLibrosAlquilados.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public int compareTo(Usuario otroUsuario) {
        return Integer.compare(this.idUsuario, otroUsuario.getIdUsuario());
    }

}
