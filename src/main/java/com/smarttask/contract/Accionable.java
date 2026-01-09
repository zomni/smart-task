package com.smarttask.contract;

public interface Accionable {
    boolean agregar(String nombre, int prioridad, boolean urgente);
    boolean marcarCompletada(int id);
    boolean eliminar(int id);
}
