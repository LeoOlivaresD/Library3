package com.duoc.library3.interfaces;

import com.duoc.library3.entities.OperacionesUsuario;

public interface IOperacionesBiblioteca {
    public void mostrarListaLibros();
    public void alquilarLibro(OperacionesUsuario operacionesUsuario, int idUsuario);
    public void actualizarListalibrosDisponibles();
    public void mostrarListaConLibrosDisponibles();
    public void devolverLibro(OperacionesUsuario operacionesUsuario, int idUsuario, int idLibro);
}
