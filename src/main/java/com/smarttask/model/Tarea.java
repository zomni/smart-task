package com.smarttask.model;

/**
 * Tarea genérica del sistema.
 */
public abstract class Tarea {
    private final int id;
    private String nombre;
    private int prioridad; // 1-5 por ejemplo
    private boolean completada;

    protected Tarea(int id, String nombre, int prioridad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }
        this.id = id;
        this.nombre = nombre.trim();
        this.prioridad = prioridad;
        this.completada = false;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPrioridad() { return prioridad; }
    public boolean isCompletada() { return completada; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }
        this.prioridad = prioridad;
    }

    public void marcarComoCompletada() { this.completada = true; }

    /** Tipo textual */
    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("[%s] ID:%d | %s | Prioridad:%d | %s",
                getTipo(), id, nombre, prioridad, completada ? "COMPLETADA" : "ACTIVA");
    }
}
