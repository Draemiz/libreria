package com.krakedev.libros.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.libros.entity.Libro;

@Service
public class LibroService {

    // ArrayList
    private List<Libro> libros = new ArrayList<>();

    // Crear libro
    public boolean agregarLibro(Libro libro) {

        for (Libro libroExistente : libros) {

            // Validar codigo repetido
            if (libroExistente.getCodigo().equals(libro.getCodigo())) {

                return false;
            }

            // Validar titulo + autor repetidos
            if (libroExistente.getTitulo().equalsIgnoreCase(libro.getTitulo())
                    && libroExistente.getAutor().equalsIgnoreCase(libro.getAutor())) {

                return false;
            }
        }

        libros.add(libro);

        return true;
    }

    // Listar libros
    public List<Libro> obtenerLibros() {
        return libros;
    }

    // Buscar libro por codigo
    public Libro buscarPorCodigo(String codigo) {

        for (Libro libro : libros) {

            if (libro.getCodigo().equals(codigo)) {
                return libro;
            }
        }

        return null;
    }

    // Actualizar libro
    public boolean actualizarLibro(String codigo, Libro libroActualizado) {

        Libro libroEncontrado = buscarPorCodigo(codigo);

        if (libroEncontrado != null) {

            libroEncontrado.setTitulo(libroActualizado.getTitulo());
            libroEncontrado.setPrecio(libroActualizado.getPrecio());

            return true;
        }

        return false;
    }

    // Eliminar libro
    public boolean eliminarLibro(String codigo) {

        Libro libroEncontrado = buscarPorCodigo(codigo);

        if (libroEncontrado != null) {

            libros.remove(libroEncontrado);

            return true;
        }

        return false;
    }
}