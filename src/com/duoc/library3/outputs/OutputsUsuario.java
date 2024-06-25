package com.duoc.library3.outputs;
import com.duoc.library3.entities.Usuario;
import java.io.FileWriter;
import java.io.IOException;

public class OutputsUsuario {
    
    public void almacenarUsuariosEnTxt(Usuario usuario) {
        String salto = System.lineSeparator();
        try (FileWriter writer = new FileWriter("lista usuarios.txt", true)) {
            writer.write(usuario.toString() + salto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
