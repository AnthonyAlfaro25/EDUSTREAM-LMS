package com.vantylabs.edustream;

// @author Fabricio y Bairon
/* Clase para para el curso que implementa la interfaz de operaciones */

import java.util.ArrayList;

public class Curso implements ICursoOperaciones {
    private int id;
    private String nombre;
    private String descripcion;
    private Profesor profesor;
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Inscripcion> inscripciones = new ArrayList<>();
    
    public Curso () {
    }
    
    public Curso(int id, String nombre, String descripcion, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
    }

    public Curso(String nombre, String descripcion, Profesor profesor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
    }
    
    @Override
    public void inscribir(Estudiante e) {
        if (e == null) return;

        for (Inscripcion i : inscripciones) {
            if (i.getEstudiante() != null && i.getEstudiante().getId() == e.getId()) {
                System.out.println("El estudiante ya está inscrito.");
                return;
            }
        }

        estudiantes.add(e);
        Inscripcion nueva = new Inscripcion(e, this);
        inscripciones.add(nueva);
    }

    @Override
    public void eliminar(Estudiante e) {
        if (e == null) return;
        estudiantes.removeIf(est -> est.getId() == e.getId());
        inscripciones.removeIf(ins -> ins.getEstudiante() != null && ins.getEstudiante().getId() == e.getId());
    }

    @Override
    public void listarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes inscritos en " + nombre);
            return;
        }
        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre() + " (Carnet: " + e.getCarnet() + ")");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public void setDescripcion(String string) {
        this.descripcion = string;
    }

    public String getDescripcion() {
        return descripcion;
    }
        
}