package com.duoc.library3.inputs;

import com.duoc.library3.entities.Biblioteca;
import com.duoc.library3.entities.Libro;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputLibros {
    
    public List<Libro> csvAListaLibros(Biblioteca biblioteca, String ruta) {
        List<Libro> listaLibrosDesdeCsv = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            String separator = ",";
            boolean esPrimerLinea = true;
            while ((line = br.readLine()) != null) {
                if (esPrimerLinea) {
                    esPrimerLinea = false;
                    continue;
                }
                String[] values = line.split(separator);
                Libro libro = new Libro(
                        values[0], //titulo
                        values[1], //autor
                        Boolean.parseBoolean(values[2]), // parseo de disponibilidad (String a boolean)
                        Integer.parseInt(values[3]) //parseo de id (String a Integer)
                );
                listaLibrosDesdeCsv.add(libro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Copio toda la lista de libros a mi lista de biblioteca original. 
        Aca decidi variar un poquito traves de una expresion lambda(programacion funcional)
        y de esa forma evitar un poco el uso de for each y ademas recordar como usar este tipo de expresiones :D*/
        listaLibrosDesdeCsv.forEach(libros -> biblioteca.getListaLibros().add(libros)); 
        return listaLibrosDesdeCsv;
    }
}
