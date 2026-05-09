package com.krakedev.libros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.krakedev.libros.entity.Libro;
import com.krakedev.libros.service.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // GET
    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroService.obtenerLibros();
    }

    // GET por codigo
    @GetMapping("/{codigo}")
    public Libro buscarLibro(@PathVariable String codigo) {
        return libroService.buscarPorCodigo(codigo);
    }

    // POST
    @PostMapping
    public String agregarLibro(@RequestBody Libro libro) {

        libroService.agregarLibro(libro);

        return "Libro agregado correctamente";
    }

    // PUT
    @PutMapping("/{codigo}")
    public String actualizarLibro(@PathVariable String codigo,
            @RequestBody Libro libro) {

        boolean actualizado = libroService.actualizarLibro(codigo, libro);

        if (actualizado) {
            return "Libro actualizado";
        }

        return "Libro no encontrado";
    }

    // DELETE
    @DeleteMapping("/{codigo}")
    public String eliminarLibro(@PathVariable String codigo) {

        boolean eliminado = libroService.eliminarLibro(codigo);

        if (eliminado) {
            return "Libro eliminado";
        }

        return "Libro no encontrado";
    }
}