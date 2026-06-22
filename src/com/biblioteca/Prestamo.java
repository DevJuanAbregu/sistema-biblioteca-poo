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

    public int getId() { return id; }

    public void registrarDevolucion() {
        fechaDevolucion = LocalDate.now();
        libro.setDisponible(true);
    }

    public LocalDate calcularFechaDevolucion() {
        return fechaPrestamo.plusDays(7);
    }

    public void mostrarInfo() {
        String estado = (fechaDevolucion == null) ? "Pendiente" : fechaDevolucion.toString();

        System.out.println("ID: " + id +
                " | Libro: " + libro.getTitulo() +
                " | Usuario: " + usuario.getNombre() +
                " | Fecha Préstamo: " + fechaPrestamo +
                " | Fecha Devolución: " + estado);
    }
}