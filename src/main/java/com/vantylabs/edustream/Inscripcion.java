package com.vantylabs.edustream;

/*@author BEN_10*/
import java.time.LocalDate;

import java.time.LocalDate;

public class Inscripcion {

    private Estudiante estudiante;
    private Curso curso;
    private LocalDate fecha;

    public Inscripcion(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fecha = LocalDate.now();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

}