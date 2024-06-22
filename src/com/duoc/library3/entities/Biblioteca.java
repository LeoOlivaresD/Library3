package com.duoc.library3.entities;

import com.duoc.library3.excepciones.LibroNoEcontradoException;
import com.duoc.library3.excepciones.LibroYaPrestadoException;
import com.duoc.library3.interfaces.IOperacionesBiblioteca;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Biblioteca implements IOperacionesBiblioteca {

    //USO DE ARRAYLIST PAPA ALMACENAR LIBROS
    private List<Libro> listaLibros = new ArrayList<>();
    //USO EL HASHSET PARA ALMACENAR SOLO LOS LIBROS DISPONIBLES Y PODER MOSTRARLOS DESPUES
    private HashSet<Libro> listaLibrosDisponibles = new HashSet<>();

    //Constructores
    public Biblioteca() {
        //Creacion de libros y agregado a listas por medio del constructor
        Libro libro = new Libro("El alquimista", "Paulo Cohelo", true, 1);
        listaLibros.add(libro);
        Libro libro2 = new Libro("La odisea", "Homero", true, 2);
        listaLibros.add(libro2);
        Libro libro3 = new Libro("Romeo y Julieta", "William Shakespeare", true, 3);
        listaLibros.add(libro3);
        Libro libro4 = new Libro("Don Quijote", "Miguel de Cervantes", true, 4);
        listaLibros.add(libro4);
        Libro libro5 = new Libro("The Hobbit", "J.R.R. Tolkien", true, 5);
        listaLibros.add(libro5);
        //Llamo al método actualizarListaLibrosDisponibles en el constructor para asegurar que la lista de libros disponibles esté actualizada desde el inicio
        actualizarListalibrosDisponibles();
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    //Getters & Setters
    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public HashSet<Libro> getListaLibrosDisponibles() {
        return listaLibrosDisponibles;
    }

    public void setListaLibrosDisponibles(HashSet<Libro> listaLibrosDisponibles) {
        this.listaLibrosDisponibles = listaLibrosDisponibles;
    }

//METODO TO STRING
    @Override
    public String toString() {
        return "Biblioteca{" + "listaLibros=" + listaLibros + '}';
    }
//METODOS IMPLEMETADOS DE MI INTERFACE
    //ESTE METODO LO LLAMO PARA MOSTRAR TODOS LIBROS EN BBLIOTECA AUNQUE HAYAN SIDO ALQUILADOS, ESTO CON LA INTENCION QUE EL USUARIO VEA LIBROS EN LISTADO Y ELIJA UNO NO DISPONIBLE PARA QUE SE LANCE LA EXCEPCION
    @Override
    public void mostrarListaLibros() {
        System.out.println("Mostrando lista de libros\n"
                + "1 El alquimista\n"
                + "2 La odisea\n"
                + "3 Romeo y Julieta\n"
                + "4 Don Quijote\n"
                + "5 The Hobbit");
    }

    @Override
    public void alquilarLibro(OperacionesUsuario operacionesUsuario, int idUsuario) {
        Usuario usuario = operacionesUsuario.getUsuarioPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado, por favor registrese antes de alquilar un libro.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        Boolean cicloDoWhile = false;
        int libroElegido = 0;
        do {
            actualizarListalibrosDisponibles();
            mostrarListaLibros();
            //Uso de bloque try catch en donde capturare 3 tipos de exceciones, dos de ellas creadas personalmente
            try {
                System.out.println("Que libro desea alquilar ?\n"
                        + "Indique el numero del libro segun corresponda");
                libroElegido = sc.nextInt();

                if (libroElegido > 5 || libroElegido < 1) {
                    throw new LibroNoEcontradoException("lo sentimos, el libro escogido no existe");

                } else {
                    for (Libro libroComparado : listaLibros) {
                        if (libroElegido == libroComparado.getIdLibro() && libroComparado.getDisponibilidad() == false) {
                            throw new LibroYaPrestadoException("Lo sentimos, el libro escogido ya esta prestado");

                        } else if (libroElegido == libroComparado.getIdLibro() && libroComparado.getDisponibilidad() == true) {
                            //Cambio la disponibilidad de un libro
                            libroComparado.setDisponibilidad(false);
                            System.out.println("Libro alquilado con exito");
                            //agrego el libro alquilado a una lista de libros que tendra el usuario
                            usuario.getListaLibrosAlquilados().add(libroComparado);
                            //Actualizo la lista de libros disponibles de la biblioteca
                            actualizarListalibrosDisponibles();
                            //actualizo la lista de usuarios con libros prestados
                            operacionesUsuario.actualizarListaUsuariosConLibrosPrestados();
                            System.out.println("Saliendo del sistema");
                            cicloDoWhile = true;
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Lo sentimos la opcion ingresada no es valida, por favor digite un numero");
                cicloDoWhile = false;
            } catch (LibroNoEcontradoException e) {
                System.out.println(e.getMessage()); //lanzo los mensajes de mis excepciones personalizadas
                cicloDoWhile = false;

            } catch (LibroYaPrestadoException e) {
                System.out.println(e.getMessage());
                cicloDoWhile = false;
            } finally {
                libroElegido = 0;
                sc.nextLine();
            }
        } while (!cicloDoWhile);
    }

    //CON ESTE METODO RECORRO MI LISTA DE LIBROS BUSCANDO TODOS LOS QUE TENGAN DISPONIBILIDAD EN TRUE PARA AGREGARLOS A MI LISTA DE DISPONIBLES
    @Override
    public void actualizarListalibrosDisponibles() {
        listaLibrosDisponibles.clear(); //ANTES DE AGREGAR DECIDO LIMPIAR PARA NO TENER ELEMENTOS DUPLICADOS
        for (Libro libro : listaLibros) {
            if (libro.getDisponibilidad()) {
                listaLibrosDisponibles.add(libro);
            }
        }
    }

    //MUESTRA TODOS LOS LIBROS DISPONIBLES EN BIBLIOTECA
    @Override
    public void mostrarListaConLibrosDisponibles() {
        int contador = 0;
        actualizarListalibrosDisponibles();//Me aseguro de actualizar la lista antes de mostrarla
        if (listaLibrosDisponibles.isEmpty()) {
            System.out.println("Lo sentimos, en este momento todos los libros han sido alquilados");
        } else {
            System.out.println("Libros disponibles en biblioteca: ");
            for (Libro libro : listaLibrosDisponibles) {
                System.out.println("Id " + (contador + 1) + " " + libro.getTitulo());
                contador++;
            }
        }
    }

    //DEVUELVE UN LIBRO Y ACTUALIZA LAS LISTAS DE USUARIOS Y LIBROS
    @Override
    public void devolverLibro(OperacionesUsuario operacionesUsuario, int idUsuario, int idLibro) {
        Usuario usuario = operacionesUsuario.getUsuarioPorId(idUsuario); //Capturo el id de un usuario el cual se supone que devolvera un lbro
        if (usuario != null) { //valida que el usuario exista
            List<Libro> librosAlquilados = usuario.getListaLibrosAlquilados(); //Copia la lista de libros del usuario elegido a una nueva lista
            Libro libroADevolver = null; //Creo un objeto libro con valores nulos para luego pasarle los valores del libro capturado por id de la lista de libros alquilados
            for (Libro libro : librosAlquilados) {
                if (libro.getIdLibro() == idLibro) {
                    libroADevolver = libro; //Asignacion de null a valores que tenga libro
                    break;
                }
            }
            if (libroADevolver != null) { //Si ya no es null, se remueve de la lista de alquilados el  objeto que tenga los vlores de libroADevolver
                librosAlquilados.remove(libroADevolver);
                libroADevolver.setDisponibilidad(true);//Cambio nuevamente la disponibilidad del libro
                listaLibros.add(libroADevolver); //Incorporo nuevamente el libro a su lista original
                actualizarListalibrosDisponibles(); //Actualizo la lista de libros disponibles
                operacionesUsuario.actualizarListaUsuariosConLibrosPrestados(); //actualizo la lista de usuarios que tengan libros
                System.out.println("Libro devuelto con exito !");
            } else {
                System.out.println("El usuario no tiene alquilado un libro con el ID proporcionado.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

}
