package com.biblioteca;

import java.util.*;

public class Main {

    static ArrayList<Libro> libros = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Prestamo> prestamos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1. Registrar libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Buscar libro");
            System.out.println("4. Modificar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("6. Registrar usuario");
            System.out.println("7. Listar usuarios");
            System.out.println("8. Registrar préstamo");
            System.out.println("9. Devolver libro");
            System.out.println("10. Ver archivo");
            System.out.println("11. Salir");

            op = sc.nextInt();

            switch (op) {

                case 1 -> registrarLibro();
                case 2 -> listar(libros);
                case 3 -> buscarLibro();
                case 4 -> modificarLibro();
                case 5 -> eliminarLibro();
                case 6 -> registrarUsuario();
                case 7 -> listarUsuarios();
                case 8 -> registrarPrestamo();
                case 9 -> devolverLibro();
                case 10 -> ArchivoLibro.leerLibros();

            }

        } while (op != 11);
    }

    static void registrarLibro() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Libro l : libros)
            if (l.getId() == id) {
                System.out.println("ID duplicado");
                return;
            }

        System.out.print("Título: ");
        String t = sc.nextLine();

        System.out.print("Autor: ");
        String a = sc.nextLine();

        if (t.isEmpty() || a.isEmpty()) {
            System.out.println("Campos obligatorios");
            return;
        }

        Libro libro = new Libro(id, t, a);
        libros.add(libro);
        ArchivoLibro.guardarLibro(libro);

        System.out.println("Libro registrado");
    }

    static void listar(List<Libro> lista) {
        lista.forEach(Libro::mostrarInfo);
    }

    static void buscarLibro() {
        System.out.print("ID: ");
        int id = sc.nextInt();

        libros.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        Libro::mostrarInfo,
                        () -> System.out.println("No encontrado")
                );
    }

    static void modificarLibro() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Libro l : libros) {
            if (l.getId() == id) {
                System.out.print("Nuevo título: ");
                String t = sc.nextLine();
                System.out.print("Nuevo autor: ");
                String a = sc.nextLine();
                l.modificar(t, a);
                System.out.println("Modificado");
                return;
            }
        }
        System.out.println("No encontrado");
    }

    static void eliminarLibro() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Confirmar (s/n): ");
        String r = sc.nextLine();

        if (r.equalsIgnoreCase("s")) {
            libros.removeIf(l -> l.getId() == id);
            System.out.println("Eliminado");
        }
    }

    static void registrarUsuario() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String n = sc.nextLine();
        System.out.print("Tipo: ");
        String t = sc.nextLine();

        usuarios.add(new Usuario(id, n, t));
        System.out.println("Usuario registrado");
    }

    static void listarUsuarios() {
        usuarios.forEach(Usuario::mostrarInfo);
    }

    static void registrarPrestamo() {
        System.out.print("ID: ");
        int id = sc.nextInt();

        System.out.print("Libro ID: ");
        int idL = sc.nextInt();

        System.out.print("Usuario ID: ");
        int idU = sc.nextInt();

        Libro libro = libros.stream().filter(l -> l.getId() == idL).findFirst().orElse(null);
        Usuario usuario = usuarios.stream().filter(u -> u.getId() == idU).findFirst().orElse(null);

        if (libro == null || usuario == null || !libro.isDisponible()) {
            System.out.println("Error en préstamo");
            return;
        }

        prestamos.add(new Prestamo(id, libro, usuario));
        System.out.println("Préstamo realizado");
    }

    static void devolverLibro() {
        System.out.print("ID préstamo: ");
        int id = sc.nextInt();

        for (Prestamo p : prestamos) {
            if (p.getId() == id) {
                p.registrarDevolucion();
                System.out.println("Devolución registrada");
            }
        }
    }
}