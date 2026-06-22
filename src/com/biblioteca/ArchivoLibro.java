package com.biblioteca;

import java.io.*;

public class ArchivoLibro {

    public static void guardarLibro(Libro libro) {
        try (FileWriter fw = new FileWriter("libros.txt", true)) {
            fw.write(libro.getId() + ";" + libro.getTitulo() + ";" + libro.getAutor() + "\n");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar archivo");
        }
    }

    public static void leerLibros() {
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer archivo");
        }
    }
}