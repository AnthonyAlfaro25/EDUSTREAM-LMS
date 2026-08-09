package com.vantylabs.edustream;

// @author anthony
/* Clase para el usuario Estudiante derivada de la clase abstracta Usuario */

public class Estudiante extends Usuario {
    private String carnet;
    
    // Constructor vacío
    public Estudiante() {
        super();
    }
    
    // Constructor completo
    public Estudiante(int id, String nombre, String email, String password, String carnet) {
        super(id, nombre, email, password);
        this.carnet = carnet;
    }
    
    // Constructor sin id
    public Estudiante(String nombre, String email, String password, String carnet) {
        super(nombre, email, password);
        this.carnet = carnet;
    }

    public String getCarnet() { 
       return carnet; 
    }
    
    public void setCarnet(String carnet) { 
       this.carnet = carnet;
    }

    @Override
    public String getRol() { 
       return "Estudiante";
    }
}