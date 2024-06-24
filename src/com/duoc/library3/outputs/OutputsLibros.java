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
                bw.write("idLibro,titulo,autor,disponibilidad");
                bw.newLine();
                bw.write("1,El alquimista,Paulo Coelho,true");
                bw.newLine();
                bw.write("2,La odisea,Homero,true");
                bw.newLine();
                bw.write("3,Romeo y Julieta,William Shakespeare,true");
                bw.newLine();
                bw.write("4,Don Quijote,Miguel de Cervantes,true");
                bw.newLine();
                bw.write("5,The Hobbit,J.R.R. Tolkien,true");
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
