package com.smarttask.model;

public class TareaNormal extends Tarea {
    public TareaNormal(int id, String nombre, int prioridad) {
        super(id, nombre, prioridad);
    }

    @Override
    public String getTipo() {
        return "NORMAL";
    }
}
