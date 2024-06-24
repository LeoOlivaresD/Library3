package com.duoc.library3.outputs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputsLibros {
    // Método para escribir libros predeterminados en un archivo CSV

    public void crearArchivoLibrosCSV(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
                bw.write("titulo,autor,disponibilidad,idLibro");
                bw.newLine();
                bw.write("El alquimista,Paulo Coelho,true,1");
                bw.newLine();
                bw.write("La odisea,Homero,true,2");
                bw.newLine();
                bw.write("Romeo y Julieta,William Shakespeare,true,3");
                bw.newLine();
                bw.write("Don Quijote,Miguel de Cervantes,true,4");
                bw.newLine();
                bw.write("The Hobbit,J.R.R. Tolkien,true,5");
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
