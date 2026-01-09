package com.smarttask.service;

import com.smarttask.contract.Accionable;
import com.smarttask.model.Tarea;
import com.smarttask.model.TareaNormal;
import com.smarttask.model.TareaUrgente;

import java.util.ArrayList;
import java.util.List;

public class GestorTareas implements Accionable {

    private final List<Tarea> tareas = new ArrayList<>();
    private int nextId = 1;

    @Override
    public boolean agregar(String nombre, int prioridad, boolean urgente) {
        try {
            int id = nextId; // aún NO aumentamos
            Tarea nueva = urgente
                    ? new TareaUrgente(id, nombre, prioridad)
                    : new TareaNormal(id, nombre, prioridad);

            tareas.add(nueva);
            nextId++; // recién aquí, porque fue exitosa
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<Tarea> listarTodas() {
        return new ArrayList<>(tareas);
    }

    public List<Tarea> listarActivas() {
        List<Tarea> out = new ArrayList<>();
        for (Tarea t : tareas) {
            if (!t.isCompletada()) out.add(t);
        }
        return out;
    }

    public List<Tarea> listarCompletadas() {
        List<Tarea> out = new ArrayList<>();
        for (Tarea t : tareas) {
            if (t.isCompletada()) out.add(t);
        }
        return out;
    }

    @Override
    public boolean marcarCompletada(int id) {
        Tarea t = buscarPorId(id);
        if (t == null) return false;
        t.marcarComoCompletada();
        return true;
    }

    @Override
    public boolean eliminar(int id) {
        Tarea t = buscarPorId(id);
        if (t == null) return false;
        return tareas.remove(t);
    }

    private Tarea buscarPorId(int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) return t;
        }
        return null;
    }
}
