package com.smarttask.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorTareasTest {

    @Test
    void agregarTareaValida_deberiaAumentarLista() {
        GestorTareas gestor = new GestorTareas();
        assertEquals(0, gestor.listarTodas().size());

        boolean ok = gestor.agregar("Estudiar", 3, false);

        assertTrue(ok);
        assertEquals(1, gestor.listarTodas().size());
        assertEquals(1, gestor.listarActivas().size());
    }

    @Test
    void agregarTareaConNombreVacio_deberiaFallar() {
        GestorTareas gestor = new GestorTareas();

        boolean ok = gestor.agregar("", 3, false);

        assertFalse(ok);
        assertEquals(0, gestor.listarTodas().size());
    }

    @Test
    void agregarTareaConPrioridadInvalida_deberiaFallar() {
        GestorTareas gestor = new GestorTareas();

        boolean ok = gestor.agregar("Algo", 10, false);

        assertFalse(ok);
        assertEquals(0, gestor.listarTodas().size());
    }

    @Test
    void marcarCompletada_deberiaMoverACompletadas() {
        GestorTareas gestor = new GestorTareas();
        gestor.agregar("Proyecto", 4, true);

        boolean ok = gestor.marcarCompletada(1);

        assertTrue(ok);
        assertEquals(0, gestor.listarActivas().size());
        assertEquals(1, gestor.listarCompletadas().size());
    }

    @Test
    void marcarCompletadaConIdInexistente_deberiaFallar() {
        GestorTareas gestor = new GestorTareas();

        boolean ok = gestor.marcarCompletada(99);

        assertFalse(ok);
    }

    @Test
    void eliminar_deberiaQuitarTarea() {
        GestorTareas gestor = new GestorTareas();
        gestor.agregar("Borrar", 2, false);

        boolean ok = gestor.eliminar(1);

        assertTrue(ok);
        assertEquals(0, gestor.listarTodas().size());
    }

    @Test
    void eliminarConIdInexistente_deberiaFallar() {
        GestorTareas gestor = new GestorTareas();

        boolean ok = gestor.eliminar(5);

        assertFalse(ok);
    }
}
