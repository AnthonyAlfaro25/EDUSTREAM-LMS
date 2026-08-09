package com.vantylabs.edustream;

// @author Fabricio

/**
 * Interfaz ICursoOperaciones.
 * Define las operaciones básicas que pueden realizarse
 * sobre los estudiantes de un curso.
 */
public interface ICursoOperaciones {

    /**
     * Inscribe un estudiante en el curso.
     *
     * @param e Estudiante que será inscrito.
     */
    void inscribir(Estudiante e);

    /**
     * Elimina un estudiante del curso.
     *
     * @param e Estudiante que será eliminado.
     */
    void eliminar(Estudiante e);

    /**
     * Muestra la lista de estudiantes inscritos en el curso.
     */
    void listarEstudiantes();
}