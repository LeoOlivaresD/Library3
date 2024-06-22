package com.duoc.library3.interfaces;

import com.duoc.library3.entities.Usuario;


public interface IOperacionesUsuario {
    public void registrarUsuario();
    public void mostrarListaUsuario();
    public Usuario getUsuarioPorId(int idUsuario);
    public void mostrarLibrosAlquilados(Integer id);
    public void actualizarListaUsuariosConLibrosPrestados();
    public void mostrarListaUsuariosConLibrosPrestados();
    public void devolverLibro(int idUsuario, int idLibro);
}
