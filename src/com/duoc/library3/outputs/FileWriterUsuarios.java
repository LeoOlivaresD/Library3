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
}
