package com.krakedev.libros.testJUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.krakedev.libros.controller.LibroController;
import com.krakedev.libros.entity.Libro;
import com.krakedev.libros.service.LibroService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LibroControllerTest {

    @Mock
    private LibroService libroService;

    @InjectMocks
    private LibroController libroController;

    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        libro1 = new Libro();
        libro1.setCodigo("L001");
        libro1.setTitulo("Clean Code");
        libro1.setAutor("Robert Martin");

        libro2 = new Libro();
        libro2.setCodigo("L002");
        libro2.setTitulo("Java Avanzado");
        libro2.setAutor("Oracle");
    }

    // =========================
    // GET TODOS LOS LIBROS
    // =========================
    @Test
    public void testObtenerLibros() {

        List<Libro> libros = new ArrayList<>();
        libros.add(libro1);
        libros.add(libro2);

        when(libroService.obtenerLibros()).thenReturn(libros);

        List<Libro> resultado = libroController.obtenerLibros();

        assertEquals(2, resultado.size());
        assertEquals("Clean Code", resultado.get(0).getTitulo());

        verify(libroService, times(1)).obtenerLibros();
    }

    // =========================
    // GET POR CODIGO EXISTENTE
    // =========================
    @Test
    public void testBuscarLibroExistente() {

        when(libroService.buscarPorCodigo("L001"))
                .thenReturn(libro1);

        Libro resultado = libroController.buscarLibro("L001");

        assertNotNull(resultado);
        assertEquals("L001", resultado.getCodigo());

        verify(libroService, times(1))
                .buscarPorCodigo("L001");
    }

    // =========================
    // GET POR CODIGO INEXISTENTE
    // =========================
    @Test
    public void testBuscarLibroNoExistente() {

        when(libroService.buscarPorCodigo("XXX"))
                .thenReturn(null);

        Libro resultado = libroController.buscarLibro("XXX");

        assertNull(resultado);

        verify(libroService, times(1))
                .buscarPorCodigo("XXX");
    }

    // =========================
    // CODIGO MAL ESCRITO
    // =========================
    @Test
    public void testBuscarLibroCodigoMalEscrito() {

        when(libroService.buscarPorCodigo("l001"))
                .thenReturn(null);

        Libro resultado = libroController.buscarLibro("l001");

        assertNull(resultado);

        verify(libroService, times(1))
                .buscarPorCodigo("l001");
    }

    // =========================
    // AGREGAR LIBRO
    // =========================
    @Test
    public void testAgregarLibro() {

        String respuesta =
                libroController.agregarLibro(libro1);

        assertEquals(
                "Libro agregado correctamente",
                respuesta);

        verify(libroService, times(1))
                .agregarLibro(libro1);
    }

    // =========================
    // AGREGAR LIBRO NULO
    // =========================
    @Test
    public void testAgregarLibroNulo() {

        String respuesta =
                libroController.agregarLibro(null);

        assertEquals(
                "Libro agregado correctamente",
                respuesta);

        verify(libroService, times(1))
                .agregarLibro(null);
    }

    // =========================
    // ACTUALIZAR LIBRO EXISTENTE
    // =========================
    @Test
    public void testActualizarLibroExistente() {

        when(libroService.actualizarLibro("L001", libro1))
                .thenReturn(true);

        String respuesta =
                libroController.actualizarLibro("L001", libro1);

        assertEquals("Libro actualizado", respuesta);

        verify(libroService, times(1))
                .actualizarLibro("L001", libro1);
    }

    // =========================
    // ACTUALIZAR LIBRO INEXISTENTE
    // =========================
    @Test
    public void testActualizarLibroNoExistente() {

        when(libroService.actualizarLibro("XXX", libro1))
                .thenReturn(false);

        String respuesta =
                libroController.actualizarLibro("XXX", libro1);

        assertEquals("Libro no encontrado", respuesta);

        verify(libroService, times(1))
                .actualizarLibro("XXX", libro1);
    }

    // =========================
    // ACTUALIZAR CON CODIGO VACIO
    // =========================
    @Test
    public void testActualizarLibroCodigoVacio() {

        when(libroService.actualizarLibro("", libro1))
                .thenReturn(false);

        String respuesta =
                libroController.actualizarLibro("", libro1);

        assertEquals("Libro no encontrado", respuesta);

        verify(libroService, times(1))
                .actualizarLibro("", libro1);
    }

    // =========================
    // ELIMINAR LIBRO EXISTENTE
    // =========================
    @Test
    public void testEliminarLibroExistente() {

        when(libroService.eliminarLibro("L001"))
                .thenReturn(true);

        String respuesta =
                libroController.eliminarLibro("L001");

        assertEquals("Libro eliminado", respuesta);

        verify(libroService, times(1))
                .eliminarLibro("L001");
    }

    // =========================
    // ELIMINAR LIBRO INEXISTENTE
    // =========================
    @Test
    public void testEliminarLibroNoExistente() {

        when(libroService.eliminarLibro("XXX"))
                .thenReturn(false);

        String respuesta =
                libroController.eliminarLibro("XXX");

        assertEquals("Libro no encontrado", respuesta);

        verify(libroService, times(1))
                .eliminarLibro("XXX");
    }

    // =========================
    // ELIMINAR CODIGO MAL ESCRITO
    // =========================
    @Test
    public void testEliminarLibroCodigoIncorrecto() {

        when(libroService.eliminarLibro("abc123"))
                .thenReturn(false);

        String respuesta =
                libroController.eliminarLibro("abc123");

        assertEquals("Libro no encontrado", respuesta);

        verify(libroService, times(1))
                .eliminarLibro("abc123");
    }
}