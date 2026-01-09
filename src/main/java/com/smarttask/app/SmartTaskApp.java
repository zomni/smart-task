package com.smarttask.app;

import com.smarttask.model.Tarea;
import com.smarttask.service.GestorTareas;

import java.util.List;
import java.util.Scanner;

/**
 * Aplicación de consola SmartTask.
 */
public class SmartTaskApp {

    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        Scanner sc = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            imprimirMenu();
            int opcion = leerEntero(sc, "Elige una opción: ");

            switch (opcion) {
                case 1 -> agregarTarea(sc, gestor);
                case 2 -> listar("ACTIVAS", gestor.listarActivas());
                case 3 -> listar("COMPLETADAS", gestor.listarCompletadas());
                case 4 -> marcarCompletada(sc, gestor);
                case 5 -> eliminar(sc, gestor);
                case 0 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
        System.out.println("¡Gracias por usar SmartTask!");
        sc.close();
    }

    private static void imprimirMenu() {
        System.out.println("\n=== SMARTTASK ===");
        System.out.println("1) Agregar tarea");
        System.out.println("2) Listar tareas activas");
        System.out.println("3) Listar tareas completadas");
        System.out.println("4) Marcar tarea como completada");
        System.out.println("5) Eliminar tarea por ID");
        System.out.println("0) Salir");
    }

    private static void agregarTarea(Scanner sc, GestorTareas gestor) {
        String nombre = leerNoVacio(sc, "Nombre de la tarea: ");
        int prioridad = leerEnteroEnRango(sc, "Prioridad (1-5): ", 1, 5);

        System.out.print("¿Es urgente? (s/n): ");
        String urg = sc.nextLine().trim().toLowerCase();
        while (!urg.equals("s") && !urg.equals("n")) {
            System.out.print("Responde 's' o 'n': ");
            urg = sc.nextLine().trim().toLowerCase();
        }

        boolean urgente = urg.equals("s");
        boolean ok = gestor.agregar(nombre, prioridad, urgente);

        if (ok) System.out.println("Tarea agregada ✅");
        else System.out.println("No se pudo agregar la tarea (datos inválidos).");
    }

    private static void marcarCompletada(Scanner sc, GestorTareas gestor) {
        int id = leerEntero(sc, "ID a completar: ");
        if (gestor.marcarCompletada(id)) System.out.println("Marcada como completada ✅");
        else System.out.println("No existe una tarea con ese ID.");
    }

    private static void eliminar(Scanner sc, GestorTareas gestor) {
        int id = leerEntero(sc, "ID a eliminar: ");
        if (gestor.eliminar(id)) System.out.println("Eliminada ✅");
        else System.out.println("No existe una tarea con ese ID.");
    }

    private static void listar(String titulo, List<Tarea> tareas) {
        System.out.println("\n--- " + titulo + " ---");
        if (tareas.isEmpty()) {
            System.out.println("(sin tareas)");
            return;
        }
        for (Tarea t : tareas) {
            System.out.println(t);
        }
    }

    private static int leerEntero(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }

    private static String leerNoVacio(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("El nombre no puede estar vacío.");
        }
    }

    private static int leerEnteroEnRango(Scanner sc, String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val < min || val > max) {
                    System.out.printf("La prioridad debe estar entre %d y %d.%n", min, max);
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }
}
