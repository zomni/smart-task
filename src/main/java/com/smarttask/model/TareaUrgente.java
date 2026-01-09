package com.smarttask.model;

public class TareaUrgente extends Tarea {
    public TareaUrgente(int id, String nombre, int prioridad) {
        super(id, nombre, prioridad);
    }

    @Override
    public String getTipo() {
        return "URGENTE";
    }
}
