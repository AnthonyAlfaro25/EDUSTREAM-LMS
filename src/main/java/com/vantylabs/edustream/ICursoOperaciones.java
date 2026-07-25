package com.vantylabs.edustream;

// @author Fabricio
/* Interfaz entre usuario estudiante y operaciones para el curso */

public interface ICursoOperaciones {
    void inscribir(Estudiante e);
    void eliminar(Estudiante e);
    void listarEstudiantes();
}
