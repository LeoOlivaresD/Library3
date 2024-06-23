/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duoc.library3.outputs;

import com.duoc.library3.entities.Usuario;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ProBook
 */
public class FileWriterUsuarios {

    public void almacenarUsuariosEnTxt(Usuario usuario) {
        String salto = System.lineSeparator();
        try (FileWriter writer = new FileWriter("lista usuarios.txt", true)) {
            writer.write(usuario.toString() + salto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar el archivo del usuario con su lista de libros alquilados
    public void actualizarLibrosUsuarioEnTxt(Usuario usuario) {
        String salto = System.lineSeparator();
        try (FileWriter writer = new FileWriter("usuario_" + usuario.getIdUsuario() + ".txt")) {
            writer.write("Usuario{" + "idUsuario=" + usuario.getIdUsuario()
                    + ", nombre=" + usuario.getNombre()
                    + ", residencia=" + usuario.getResidencia()
                    + ", librosAlquilados=[");
            for (int i = 0; i < usuario.getListaLibrosAlquilados().size(); i++) {
                writer.write(usuario.getListaLibrosAlquilados().get(i).toString());
                if (i < usuario.getListaLibrosAlquilados().size() - 1) {
                    writer.write(", ");
                }
            }
            writer.write("]}" + salto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
