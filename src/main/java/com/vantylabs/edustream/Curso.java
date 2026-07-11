package com.vantylabs.edustream;

import java.util.ArrayList;

public class Curso implements ICursoOperaciones {
    private int id;
    private String nombre;
    private Profesor profesor;
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public Curso(int id, String nombre, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public void inscribir(Estudiante e) {
        estudiantes.add(e);
    }

    public void eliminar(Estudiante e) {
        estudiantes.remove(e);
    }

    public void listarEstudiantes() {
        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre());
        }
    }
}
