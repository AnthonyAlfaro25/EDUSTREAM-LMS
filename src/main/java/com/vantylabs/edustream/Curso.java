package com.vantylabs.edustream;

// @author Fabricio
/* Clase para para el curso que implementa la interfaz de operaciones */

import java.util.ArrayList;

public class Curso implements ICursoOperaciones {
    private int id;
    private String nombre;
    private Profesor profesor;
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Inscripcion> inscripciones = new ArrayList<>();

    public Curso(int id, String nombre, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    @Override
    public void inscribir(Estudiante e){
        
    for(Inscripcion i : inscripciones){

    if(i.getEstudiante().equals(e)){

        System.out.println("El estudiante ya está inscrito.");

        return;

    }

    }    
    
    for(Inscripcion i : inscripciones){

    if(i.getEstudiante().equals(e)){

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
        estudiantes.remove(e);
    }

    @Override
    public void listarEstudiantes() {
        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre());
        }
    }
    
    public ArrayList<Inscripcion> getInscripciones() {

    return inscripciones;
    

    }
    }
