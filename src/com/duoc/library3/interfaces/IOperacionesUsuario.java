package com.duoc.library3.interfaces;

import com.duoc.library3.entities.Usuario;
import com.duoc.library3.outputs.OutputsUsuario;


public interface IOperacionesUsuario {
    public void registrarUsuario(OutputsUsuario fw);
    public void mostrarListaUsuario();
    public Usuario getUsuarioPorId(int idUsuario);
    public void mostrarLibrosAlquilados(Integer id);
    public void actualizarListaUsuariosConLibrosPrestados();
    public void mostrarListaUsuariosConLibrosPrestados();
    public void devolverLibro(int idUsuario, int idLibro);
}
