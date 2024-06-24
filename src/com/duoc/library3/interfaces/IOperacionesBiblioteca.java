package com.duoc.library3.interfaces;

import com.duoc.library3.entities.OperacionesUsuario;
import com.duoc.library3.outputs.OutputsUsuario;

public interface IOperacionesBiblioteca {
    public void mostrarListaLibros();
    public void alquilarLibro(OutputsUsuario fw ,OperacionesUsuario operacionesUsuario, int idUsuario);
    public void actualizarListalibrosDisponibles();
    public void mostrarListaConLibrosDisponibles();
    public void devolverLibro(OutputsUsuario fw ,OperacionesUsuario operacionesUsuario, int idUsuario, int idLibro);
}
