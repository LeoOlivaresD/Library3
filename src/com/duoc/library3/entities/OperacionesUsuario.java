package com.duoc.library3.entities;

import com.duoc.library3.interfaces.IOperacionesUsuario;
import com.duoc.library3.outputs.OutputsUsuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class OperacionesUsuario implements IOperacionesUsuario {
    private OutputsUsuario fw = new OutputsUsuario();
    //DECLARACION DE HASHMAP PARA ALMACENAR MIS USUARIOS CON SU RESPECTIVO ID
    private static HashMap<Integer, Usuario> listaUsuarios = new HashMap<>();
    //USO DE TREESET PARA LISTAR USUARIOS QUE TENGAN LIBROS PRESTADOS
    private static TreeSet<Usuario> listaUsuariosConLibrosPrestados = new TreeSet<>();

    //Constructor
    public OperacionesUsuario() {
    }

    //Getters & Setters
    public static HashMap<Integer, Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public static void setListaUsuarios(HashMap<Integer, Usuario> listaUsuarios) {
        OperacionesUsuario.listaUsuarios = listaUsuarios;
    }

    public static TreeSet<Usuario> getListaUsuariosConLibrosPrestados() {
        return listaUsuariosConLibrosPrestados;
    }

    public static void setListaUsuariosConLibrosPrestados(TreeSet<Usuario> listaUsuariosConLibrosPrestados) {
        OperacionesUsuario.listaUsuariosConLibrosPrestados = listaUsuariosConLibrosPrestados;
    }

    //Metodos implementdos por interface
    //CON CADA LLAMADA AL METODO CREARÉ NUEVOS USUARIOS QUE ALACENARÉ EN MI LISTA CON HASHMAP
    @Override
    public void registrarUsuario() {
        Usuario usuario = new Usuario();
        Scanner sc = new Scanner(System.in);
        Boolean cicloDoWhile = true;
        Random rand = new Random();
        int min = 1;
        int max = 10000;
        int idAleatorio = rand.nextInt(max - min + 1) + min;
        usuario.setIdUsuario(idAleatorio);
        System.out.println("Id usuario: " + usuario.getIdUsuario());
        //
        //Comienzo a validar los datos registrados de usuario
        do {
            System.out.println("Ingrese nombre de usuario a registrar");
            String nombre = sc.nextLine();
            if (nombre.isBlank() || nombre.isEmpty()) {
                System.out.println("Debe ingresar un nombre valido, por favor vuelva a intentarlo");
            } else {
                usuario.setNombre(nombre);
                System.out.println("Nombre registrado !");
                cicloDoWhile = false;
            }
        } while (cicloDoWhile);
        cicloDoWhile = true; //Reseteo mi variable para que continue de manera correcta el flujo del programa
        do {
            System.out.println("Ingrese residencia de usuario a registrado");
            String residencia = sc.nextLine();
            if (residencia.isBlank() || residencia.isEmpty()) {
                System.out.println("Debe ingresar una residencia valida, por favor vuelva a intentarlo");
            } else {
                usuario.setResidencia(residencia);
                System.out.println("Residencia registrada !");
                cicloDoWhile = false;
            }
        } while (cicloDoWhile);
        System.out.println("Usuario registrado correctamente");
        //Guardo el usuario en mi HashMap
        listaUsuarios.put(usuario.getIdUsuario(), usuario);
        //Almaceno el objeto de usuario junto a todos sus atributos en un archivo de texto
        fw.almacenarUsuariosEnTxt(usuario);
    }

    @Override
    //VER LISTA USUARIOS CON CLAVE(ID) Y VALORES
    public void mostrarListaUsuario() {
        for (Entry<Integer, Usuario> e : listaUsuarios.entrySet()) {
            System.out.println(e);
        }
    }

    @Override
    //Con este metodo buscare un usuario en particular dentro de mi lista hashmap, con su id para poder asignar libros o buscar informacion en particular de él
    public Usuario getUsuarioPorId(int idUsuario) {
        return listaUsuarios.get(idUsuario);
    }

    @Override
    // EN ESTE METODO POR MEDIO DEL ID BUSCO EN MI LISTA DE USUARIOS GUARDADOS PARA OBTENER SU LISTA DE LIBROS ALQUILADOS
    public void mostrarLibrosAlquilados(Integer id) {
        Usuario usuario = listaUsuarios.get(id);
        if (usuario != null) {
            if (usuario.getListaLibrosAlquilados().isEmpty()) {
                System.out.println("Este usuario no ha alquilado libros.");
            } else {
                System.out.println("Libros alquilados por " + usuario.getNombre() + ":");
                for (Libro libro : usuario.getListaLibrosAlquilados()) {
                    System.out.println("ID: " + libro.getIdLibro() + ", Título: " + libro.getTitulo());
                }
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    //METODO PARA ACTUALIZAR MI LISTA CON USUARIOS QUE TENGAN LIBROS PRESTADOS
    @Override
    public void actualizarListaUsuariosConLibrosPrestados() {
        listaUsuariosConLibrosPrestados.clear(); //LIMPIO MI LISTA EN PRIMERA INSTANCIA
        for (Usuario usuario : listaUsuarios.values()) {
            if (!usuario.getListaLibrosAlquilados().isEmpty()) { //RECORRO LA LISTA DE USUARIOS, BUSCANDO USUARIOS QUE TENGAN OBJETOS LIBROS EN SUS LISTA DE LIBROS ALQUILADOS
                listaUsuariosConLibrosPrestados.add(usuario);//AL ENCONTRAR UN USUARIO CON LISTA DE LIBROS ESTE SE GUARDA EN LA NUEVA LISTA
            }
        }
    }

    @Override
    public void mostrarListaUsuariosConLibrosPrestados() {
        if (listaUsuariosConLibrosPrestados.isEmpty()) { //Valido si la lista esta vacia lo cual significaria que ningun usuario a alquilado un libro
            System.out.println("Lo sentimos, en este momento no hay usuarios con libros alquilados");
        } else {
            System.out.println("Mostrando listado de usuarios con libros prestados");
            for (Usuario usuario : listaUsuariosConLibrosPrestados) { //Recorro la lista nuevamente para imprimir usuarios que contengan libros
                System.out.println(usuario.toString());
            }
        }
    }

    //Metodo para devolver un libro
    @Override
    public void devolverLibro(int idUsuario, int idLibro) {
        Usuario usuario = listaUsuarios.get(idUsuario); //Obtengo un usuario especifico por medio de su id
        if (usuario != null) { //Luego por medio del id de ese usuario verifico toda su lista de libros alquilados
            List<Libro> librosAlquilados = usuario.getListaLibrosAlquilados(); //Copio esa lista de libros alquilados a una nueva lista de tipo libros
            boolean libroEncotrado = false;
            for (Libro libro : librosAlquilados) { //Verifico ahora que los id de libros en la lista, sean igual al id que se paso por parametro para luego removerlo
                if (libro.getIdLibro() == idLibro) {
                    librosAlquilados.remove(libro);
                    libroEncotrado = true; //Como se logra identificar que el id que paso el usuario, coincide con un id de un libro puesto en la lista de alquilados, se comprueba que se encuentra y ah sido eliminado de la lista
                    break;
                }
            }
            if (libroEncotrado) {
                System.out.println("Libro devuelto con exito !");
                actualizarListaUsuariosConLibrosPrestados(); //Actualizo la lista de usuarios que poseen libros
            } else {
                System.out.println("El usuario no tiene alquilado un libro con el ID proporcionado.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
