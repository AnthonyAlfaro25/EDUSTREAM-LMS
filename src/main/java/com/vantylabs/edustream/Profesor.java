package com.vantylabs.edustream;

// @author anthony
/* Clase para el usuario Profesor derivada de la clase abstracta Usuario */

public class Profesor extends Usuario {
    private String materia;
    
    // Constructor vacío
    public Profesor() {
        super();
    }
    
    // Constructor completo
    public Profesor(int id, String nombre, String email, String password, String materia) {
        super(id, nombre, email, password);
        this.materia = materia;
    }
    
    // Constructor sin ID
    public Profesor(String nombre, String email, String password, String materia) {
        super(nombre, email, password);
        this.materia = materia;
    }

    public String getMateria() { 
       return materia; 
    }
    
    public void setMateria(String materia) { 
       this.materia = materia;
    }

    @Override
    public String getRol() { 
       return "Profesor";
    }
}