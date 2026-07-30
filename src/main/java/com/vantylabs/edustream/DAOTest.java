package com.vantylabs.edustream;

import com.vantylabs.edustream.dao.CursoDAO;
import com.vantylabs.edustream.dao.EstudianteDAO;
import com.vantylabs.edustream.dao.InscripcionDAO;
import com.vantylabs.edustream.dao.ProfesorDAO;

import java.time.LocalDate;

public class DAOTest {

    public static void main(String[] args) {

        try {

            probarEstudianteDAO();

            probarProfesorDAO();

            probarCursoDAO();

            probarInscripcionDAO();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    //====================================================
    // ESTUDIANTE
    //====================================================

    public static void probarEstudianteDAO() throws Exception {

        System.out.println("\n========== ESTUDIANTE DAO ==========");

        EstudianteDAO dao = new EstudianteDAO();

        Estudiante estudiante = new Estudiante();

        estudiante.setNombre("David Ortiz");
        estudiante.setEmail("david@gmail.com");
        estudiante.setPassword("12345");

        dao.insertar(estudiante);

        System.out.println("Estudiantes registrados:");

        for (Estudiante e : dao.obtenerTodos()) {

            System.out.println(e.getId()
                    + " - "
                    + e.getNombre()
                    + " - "
                    + e.getEmail());

        }

        // dao.eliminarPorId(1);

    }

    //====================================================
    // PROFESOR
    //====================================================

    public static void probarProfesorDAO() throws Exception {

        System.out.println("\n========== PROFESOR DAO ==========");

        ProfesorDAO dao = new ProfesorDAO();

        Profesor profesor = new Profesor();

        profesor.setNombre("Carlos Vargas");
        profesor.setEmail("carlos@gmail.com");
        profesor.setPassword("12345");

        dao.insertar(profesor);

        System.out.println("Profesores registrados:");

        for (Profesor p : dao.obtenerTodos()) {

            System.out.println(p.getId()
                    + " - "
                    + p.getNombre()
                    + " - "
                    + p.getEmail());

        }

        // dao.eliminarPorId(2);

    }

    //====================================================
    // CURSO
    //====================================================

    public static void probarCursoDAO() throws Exception {

        System.out.println("\n========== CURSO DAO ==========");

        CursoDAO dao = new CursoDAO();

        Profesor profesor = new Profesor();

        profesor.setId(1);

        Curso curso = new Curso();

        curso.setNombre("Java Avanzado");
        curso.setDescripcion("Programación Orientada a Objetos");

        curso.setProfesor(profesor);

        dao.insertar(curso);

        System.out.println("Cursos registrados:");

        for (Curso c : dao.obtenerTodos()) {

            System.out.println(c.getId()
                    + " - "
                    + c.getNombre()
                    + " - Profesor: "
                    + c.getProfesor().getId());

        }

        // dao.eliminarPorId(1);

    }

    //====================================================
    // INSCRIPCION
    //====================================================

    public static void probarInscripcionDAO() throws Exception {

        System.out.println("\n========== INSCRIPCION DAO ==========");

        InscripcionDAO dao = new InscripcionDAO();

        Estudiante estudiante = new Estudiante();

        estudiante.setId(1);

        Curso curso = new Curso();

        curso.setId(1);

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setEstudiante(estudiante);

        inscripcion.setCurso(curso);

        inscripcion.setFecha(LocalDate.now());

        dao.insertar(inscripcion);

        System.out.println("Inscripciones registradas:");

        for (Inscripcion i : dao.obtenerTodos()) {

            System.out.println(
                    i.getId()
                    + " Estudiante: "
                    + i.getEstudiante().getId()
                    + " Curso: "
                    + i.getCurso().getId()
                    + " Fecha: "
                    + i.getFecha());

        }

        // dao.eliminarPorId(1);
    }
}