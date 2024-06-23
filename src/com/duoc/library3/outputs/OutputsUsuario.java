package com.duoc.library3.outputs;

import com.duoc.library3.entities.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputsUsuario {

    //Con este metodo almacenare obejtos usuarios en un archivo txt excluyendo lstas de libros
    public void almacenarUsuariosEnTxt(Usuario usuario) {
        String salto = System.lineSeparator();
        try (FileWriter writer = new FileWriter("lista usuarios.txt", true)) {
            writer.write(usuario.toString() + salto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// Método para actualizar el archivo del usuario con su lista de libros alquilados
    public void actualizarListaUsuariosEnTxtConLibros(Usuario usuario) {
        String archivo = "lista usuarios.txt";
        //Esta lista sera encargada de leer el contenido completo de mi txt en una lista de lineas
        List<String> lineas = new ArrayList<>();
        String linea;
        boolean usuarioEncontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            while ((linea = reader.readLine()) != null) {
                //Itero sobre estas líneas para encontrar el usuario que queremos actualizar.
                if (linea.contains("idUsuario=" + usuario.getIdUsuario())) {
                    usuarioEncontrado = true;
                    // Actualizamos la línea con la información del usuario y sus libros alquilados
                    lineas.add(usuario.toString());
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!usuarioEncontrado) {
            // Si el usuario no se encontró, lo agregamos al final del archivo
            lineas.add(usuario.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String lineaActualizada : lineas) {
                writer.write(lineaActualizada);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
