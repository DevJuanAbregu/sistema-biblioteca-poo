package com.biblioteca;

import java.time.LocalDate;

public class Prestamo {

    private int id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(int id, Libro libro, Usuario usuario) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;

        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;

        libro.setDisponible(false);
    }

    public int getId() {
        return id;
    }

    public void registrarDevolucion() {
        fechaDevolucion = LocalDate.now();
        libro.setDisponible(true);
    }

    public void mostrarInfo() {

        String devolucion;

        if (fechaDevolucion == null) {
            devolucion = "Pendiente";
        } else {
            devolucion = fechaDevolucion.toString();
        }

        System.out.println(
                "ID Prestamo: " + id +
                        " | Libro: " + libro.getTitulo() +
                        " | Usuario: " + usuario.getNombre() +
                        " | Fecha Prestamo: " + fechaPrestamo +
                        " | Fecha Devolucion: " + devolucion
        );
    }
}
