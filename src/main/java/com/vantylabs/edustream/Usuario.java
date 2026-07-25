package com.vantylabs.edustream;

// @author anthony
/* Clase abstracta usuario, con los atributos y metodos necesarios
   para todos los tipos de usuario que utilizen la plataforma */

public abstract class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contrasenia;
    
    // Constructor vacio
    public Usuario() {
    }

    // Constructor completo (Para leer usuarios desde la DB)
    public Usuario(int id, String nombre, String email, String contrasenia){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }
    
    // Constructor sin ID (Para crear usuarios compatibles con la DB)
    public Usuario(String nombre, String email, String contrasenia) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    // getters y setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    //metodo getter abstracto para obtener el rol especifico de cada usuario
    public abstract String getRol();
    
    //metodo para mostrar el usuario
    @Override
    public String toString() {
        return "[" + getRol() + "] " + nombre + " - " + email;
    }
}