package com.duoc.library3;

import com.duoc.library3.entities.Biblioteca;
import com.duoc.library3.entities.OperacionesUsuario;
import com.duoc.library3.excepciones.LibroNoEcontradoException;
import com.duoc.library3.excepciones.LibroYaPrestadoException;
import com.duoc.library3.inputs.InputLibros;
import com.duoc.library3.inputs.InputsUsuarios;
import com.duoc.library3.outputs.OutputsLibros;
import com.duoc.library3.outputs.OutputsUsuario;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Que tal profesor !, por aca unas pequeñas notas.
Al menos en este proyecto me enfoque en usar todas las colecciones disponibles
como lo planteaba la rubrica. Para ello dentro del menu hice algunas validaciones
simples y trate de manejar errores con blokes try cacth o excepciones personalizadas
en el caso que se busque un libro que ya esta prestado o no existe.
Volviendo al tema de las Collecciones como para resumir: Ocupe ArrayLst en Libros
para partir con una lista inicial de todos ellos y luego use un HashSet para solo almacenar libros
que estuvieran disponibles dejando afuera los ya alquilados. En cuanto a Usuarios
Parto guardandolos en un HashMap ocupando su id como clave y luego hago una lista
de usuarios con TreeSet para almacenar solo usuarios que contengan listas de libros.
Todo esto lo podra probar en los case del menu que mostraran datos segun cada parametro.
De antemano muchas gracias por la revision y espero se entienda el codigo con la documentacion que realice en cada paso
 */
public class Library3 {

    static Scanner sc = new Scanner(System.in);
    static Integer opcionMenu = 0;
    static boolean salirMenu = true;
    static String archivoCsvLibros = "libros.csv";

    public static void main(String[] args) throws LibroYaPrestadoException, LibroNoEcontradoException {
        //Creacion de objetos a utilizar
        OperacionesUsuario operacionesUsuario = new OperacionesUsuario();
        Biblioteca biblioteca = new Biblioteca();
        //Manejo de archivos txt y csv
        OutputsLibros outPutsLibros = new OutputsLibros();
        InputLibros inputLibros = new InputLibros();
        InputsUsuarios inputUsuarios = new InputsUsuarios();
        OutputsUsuario outputsUsuario = new OutputsUsuario();
        //Con este metodo estare creando un archivo csv que contenga todos los libros predeterminados
        outPutsLibros.crearArchivoLibrosCSV(archivoCsvLibros);
        //Aca leere el archivo csv de los libros y creare esos objetos para luego añadirlos a mi lista dentro del mismo metodo
        inputLibros.csvAListaLibros(biblioteca, archivoCsvLibros);

        System.out.println("Bienvenido a la biblioteca virtual");

        //Menu
        do {
            try {
                System.out.println("Indique que accion desea realizar marcando el numero correspondiente");
                System.out.println("1: Registrar usuario.\n"
                        + "2: Alquilar un libro.\n"
                        + "3: Devolver libro.\n"
                        + "4: Mostrar libros alquilados por id de usuario.\n"
                        + "5: Mostrar lista de libros disponibles.\n"
                        + "6: Mostrar lista de usuarios que posean libros prestados.\n"
                        + "7: Salir");
                opcionMenu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Lo sentimos, debe ingresar un numero \"entero\"");
                sc.next();//Limpio el buffer de scanner    
            }
            switch (opcionMenu) {
                //REGISTRO DE USUARIO
                case 1:
                    System.out.println("Registrando usuario");
                    operacionesUsuario.registrarUsuario();
                    break;
                //ALQUILAR UN LIBRO
                case 2:
                    try {
                        System.out.println("Mostrando Id usuarios registrados");
                        operacionesUsuario.mostrarListaUsuario();
                        System.out.println("Antes de alquilar un libro, por favor ingrese un id de usuario "
                                + "el cual estara asignado para el libro");
                        int idUsuarioAlquilar = sc.nextInt();
                        biblioteca.alquilarLibro(outputsUsuario, operacionesUsuario, idUsuarioAlquilar);
                    } catch (InputMismatchException e) {
                        System.out.println("Lo sentimos, debe ingresar un numero id valido");
                        sc.nextLine(); //Limpio el buffer de scanner
                    }
                    break;
                //DEVOLVER LIBRO    
                case 3:
                    try {
                        System.out.println("Devolviendo un libro");
                        operacionesUsuario.mostrarListaUsuario(); //Muestro lista de usuario para visualizar a todos los que hayan y se pueda ver el id de cada uno
                        System.out.println("Ingrese id de usuario que devolverá libro");
                        int idUsuarioDevolver = sc.nextInt();
                        operacionesUsuario.mostrarLibrosAlquilados(idUsuarioDevolver);//Muestro los lbros con su id respectivo alquilados por el usuario
                        System.out.println("Ahora ingrese id del libro a devolver");
                        int idLibroDevolver = sc.nextInt();
                        biblioteca.devolverLibro(outputsUsuario, operacionesUsuario, idUsuarioDevolver, idLibroDevolver);
                    } catch (InputMismatchException e) {
                        System.out.println("Lo sentimos, debe ingresar un numero id valido para usuario o libro");
                        sc.nextLine(); //Limpio el buffer de scanner
                    }
                    break;
                //MOSTRAR LIBROS ALQUILADOS POR ID DE USUARIO     
                case 4:
                    try {
                        System.out.println("Mostrando usuarios registrdos");
                        operacionesUsuario.mostrarListaUsuario();
                        System.out.println("Ingrese el ID de usuario para mostrar los libros alquilados por él:");
                        int idUsuarioMostrar = sc.nextInt();
                        operacionesUsuario.mostrarLibrosAlquilados(idUsuarioMostrar);
                    } catch (InputMismatchException e) {
                        System.out.println("Lo sentimos, debe ingresar un numero id valido para usuario");
                        sc.nextLine(); //Limpio el buffer de scanner
                    }
                    break;
                //MOSTRAR LISTA DE LIBROS DISPONIBLES EN BIBLIOTECA    
                case 5:
                    biblioteca.mostrarListaConLibrosDisponibles();
                    break;
                //MOSTRAR LISTA DE USUARIO CON LIBROS PRESTADOS    
                case 6:
                    operacionesUsuario.mostrarListaUsuariosConLibrosPrestados();
                    break;
                //SALIDA DEL SISTEMA    
                case 7:
                    System.out.println("Saliendo de la biblioteca virtual..");
                    salirMenu = false;
                    break;
                default:
                    System.out.println("Lo sentimos, la opcion ingresada no existe, intente nuevamente");
            }
        } while (salirMenu);
        sc.close(); //Se cierra scaSnner
    }
}
