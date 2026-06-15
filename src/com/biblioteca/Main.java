package com.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Prestamo> prestamos = new ArrayList<>();

        int opcion = 0;

        do {

            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1. Registrar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Buscar Libro");
            System.out.println("4. Registrar Usuario");
            System.out.println("5. Listar Usuarios");
            System.out.println("6. Registrar Prestamo");
            System.out.println("7. Listar Prestamos");
            System.out.println("8. Registrar Devolucion");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            try {

                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {

                    case 1:

                        System.out.print("ID Libro: ");
                        int idLibro = Integer.parseInt(sc.nextLine());

                        boolean existeLibro = false;

                        for (Libro l : libros) {
                            if (l.getId() == idLibro) {
                                existeLibro = true;
                                break;
                            }
                        }

                        if (existeLibro) {
                            System.out.println("El ID ya existe.");
                            break;
                        }

                        System.out.print("Titulo: ");
                        String titulo = sc.nextLine();

                        System.out.print("Autor: ");
                        String autor = sc.nextLine();

                        libros.add(new Libro(idLibro, titulo, autor));

                        System.out.println("Libro registrado correctamente.");
                        break;

                    case 2:

                        if (libros.isEmpty()) {
                            System.out.println("No existen libros.");
                        } else {
                            for (Libro l : libros) {
                                l.mostrarInfo();
                            }
                        }

                        break;

                    case 3:

                        System.out.print("Ingrese ID del libro: ");
                        int buscarLibro = Integer.parseInt(sc.nextLine());

                        boolean encontrado = false;

                        for (Libro l : libros) {

                            if (l.getId() == buscarLibro) {

                                l.mostrarInfo();
                                encontrado = true;
                            }
                        }

                        if (!encontrado) {
                            System.out.println("Libro no encontrado.");
                        }

                        break;

                    case 4:

                        System.out.print("ID Usuario: ");
                        int idUsuario = Integer.parseInt(sc.nextLine());

                        boolean existeUsuario = false;

                        for (Usuario u : usuarios) {
                            if (u.getId() == idUsuario) {
                                existeUsuario = true;
                                break;
                            }
                        }

                        if (existeUsuario) {
                            System.out.println("El usuario ya existe.");
                            break;
                        }

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Tipo (Administrador/Usuario): ");
                        String tipo = sc.nextLine();

                        usuarios.add(new Usuario(idUsuario, nombre, tipo));

                        System.out.println("Usuario registrado correctamente.");
                        break;

                    case 5:

                        if (usuarios.isEmpty()) {
                            System.out.println("No existen usuarios.");
                        } else {
                            for (Usuario u : usuarios) {
                                u.mostrarInfo();
                            }
                        }

                        break;

                    case 6:

                        System.out.print("ID Prestamo: ");
                        int idPrestamo = Integer.parseInt(sc.nextLine());

                        System.out.print("ID Libro: ");
                        int libroId = Integer.parseInt(sc.nextLine());

                        System.out.print("ID Usuario: ");
                        int usuarioId = Integer.parseInt(sc.nextLine());

                        Libro libroSeleccionado = null;
                        Usuario usuarioSeleccionado = null;

                        for (Libro l : libros) {
                            if (l.getId() == libroId && l.isDisponible()) {
                                libroSeleccionado = l;
                            }
                        }

                        for (Usuario u : usuarios) {
                            if (u.getId() == usuarioId) {
                                usuarioSeleccionado = u;
                            }
                        }

                        if (libroSeleccionado != null && usuarioSeleccionado != null) {

                            prestamos.add(
                                    new Prestamo(
                                            idPrestamo,
                                            libroSeleccionado,
                                            usuarioSeleccionado
                                    )
                            );

                            System.out.println("Prestamo registrado.");
                        } else {
                            System.out.println("Datos incorrectos.");
                        }

                        break;

                    case 7:

                        if (prestamos.isEmpty()) {
                            System.out.println("No existen prestamos.");
                        } else {

                            for (Prestamo p : prestamos) {
                                p.mostrarInfo();
                            }
                        }

                        break;

                    case 8:

                        System.out.print("ID Prestamo: ");
                        int devolver = Integer.parseInt(sc.nextLine());

                        boolean prestamoEncontrado = false;

                        for (Prestamo p : prestamos) {

                            if (p.getId() == devolver) {

                                p.registrarDevolucion();
                                prestamoEncontrado = true;

                                System.out.println("Devolucion registrada.");
                            }
                        }

                        if (!prestamoEncontrado) {
                            System.out.println("Prestamo no encontrado.");
                        }

                        break;

                    case 9:

                        System.out.println("Sistema finalizado.");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }

            } catch (Exception e) {

                System.out.println("Error: dato ingresado no valido.");
            }

        } while (opcion != 9);

        sc.close();
    }
}
