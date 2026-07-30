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
    public Estudiante(int id, String nombre, String email, String contrasenia, String carnet) {
        super(id, nombre, email, contrasenia);
        this.carnet = carnet;
    }
    
    // Constructor sin id
    public Estudiante(String nombre, String email, String contrasenia, String carnet) {
        super(nombre, email, contrasenia);
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

    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPassword(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}