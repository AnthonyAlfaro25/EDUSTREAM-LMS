package com.vantylabs.edustream;

// @author anthony
/* Clase para el usuario Administrativo derivada de la clase abstracta Usuario */

public class Administrativo extends Usuario {

    // constructor extendido
    public Administrativo (int id, String nombre, String email, String contrasenia) {
        super(id, nombre, email, contrasenia);
    }

    // getter del rol
    @Override
    public String getRol() { 
        return "Administrativo";
    }
}

