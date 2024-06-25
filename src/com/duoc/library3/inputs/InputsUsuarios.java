package com.duoc.library3.inputs;
import com.duoc.library3.entities.OperacionesUsuario;
import com.duoc.library3.entities.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputsUsuarios {
// Método para cargar usuarios desde un archivo de texto
    public List<Usuario> cargarUsuariosDesdeTxt(OperacionesUsuario operacionesUsuario,String rutaArchivo) {
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Eliminar los caracteres de inicio y fin de la línea
                line = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));

                // Dividir la línea en partes por la coma
                String[] partes = line.split(", ");

                // Inicializar variables para almacenar los datos del usuario
                int idUsuario = -1;
                String nombre = null;
                String residencia = null;

                // Iterar sobre las partes para extraer los valores
                for (String parte : partes) {
                    String[] atributoValor = parte.split("=");
                    String atributo = atributoValor[0].trim();
                    String valor = atributoValor[1].trim();

                    switch (atributo) {
                        case "idUsuario":
                            idUsuario = Integer.parseInt(valor);
                            break;
                        case "nombre":
                            nombre = valor;
                            break;
                        case "residencia":
                            residencia = valor;
                            break;
                        default:
                            // Tratar cualquier otro atributo si es necesario
                            break;
                    }
                }

                // Crear y agregar el usuario a la lista
                Usuario usuario = new Usuario(idUsuario, nombre, residencia);
                listaUsuarios.add(usuario);
            }
            listaUsuarios.forEach(usuarios -> operacionesUsuario.getListaUsuarios().put(usuarios.getIdUsuario(), usuarios));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }
}
