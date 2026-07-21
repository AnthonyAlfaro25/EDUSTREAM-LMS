package com.vantylabs.edustream;

import java.util.ArrayList;
import java.util.Scanner;

public class Edustream {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        Profesor profesor = null;
        Curso curso = null;

        int opcion;

        do {

            System.out.println("\n----------------------------------");
            System.out.println("       SISTEMA EDUSTREAM");
            System.out.println("----------------------------------");
            System.out.println("1. Registrar Profesor");
            System.out.println("2. Registrar Estudiante");
            System.out.println("3. Crear Curso");
            System.out.println("4. Inscribir Estudiante");
            System.out.println("5. Eliminar Estudiante");
            System.out.println("6. Listar Estudiantes");
            System.out.println("7. Ver Inscripciones");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opciones: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("ID: ");
                    int idProfesor = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombreProfesor = sc.nextLine();

                    System.out.print("Correo: ");
                    String correoProfesor = sc.nextLine();

                    System.out.print("Contraseña: ");
                    String passProfesor = sc.nextLine();

                    System.out.print("Materia: ");
                    String materia = sc.nextLine();

                    profesor = new Profesor(idProfesor, nombreProfesor,
                            correoProfesor, passProfesor, materia);

                    System.out.println("Profesor registrado correctamente.");

                    break;

                case 2:

                    System.out.print("ID: ");
                    int idEst = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombreEst = sc.nextLine();

                    System.out.print("Correo: ");
                    String correoEst = sc.nextLine();

                    System.out.print("Contraseña: ");
                    String passEst = sc.nextLine();

                    System.out.print("Carnet: ");
                    String carnet = sc.nextLine();

                    Estudiante estudiante = new Estudiante(idEst,
                            nombreEst,
                            correoEst,
                            passEst,
                            carnet);

                    estudiantes.add(estudiante);

                    System.out.println("Estudiante registrado correctamente.");

                    break;

                case 3:

                    if (profesor == null) {
                        System.out.println("Primero registre un profesor.");
                        break;
                    }

                    System.out.print("ID del Curso: ");
                    int idCurso = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre del Curso: ");
                    String nombreCurso = sc.nextLine();

                    curso = new Curso(idCurso, nombreCurso, profesor);

                    System.out.println("Curso creado correctamente.");

                    break;

                case 4:

                    if (curso == null) {
                        System.out.println("Debe crear un curso primero.");
                        break;
                    }

                    if (estudiantes.isEmpty()) {
                        System.out.println("No existen estudiantes.");
                        break;
                    }

                    System.out.println("\nEstudiantes:");

                    for (int i = 0; i < estudiantes.size(); i++) {

                        System.out.println((i + 1) + ". "
                                + estudiantes.get(i).getNombre());

                    }

                    System.out.print("Seleccione un estudiante: ");

                    int seleccion = sc.nextInt();

                    if (seleccion >= 1 && seleccion <= estudiantes.size()) {

                        curso.inscribir(estudiantes.get(seleccion - 1));

                    } else {

                        System.out.println("Opción incorrecta.");

                    }

                    break;

                case 5:

                    if (curso == null) {
                        System.out.println("Debe crear un curso primero.");
                        break;
                    }

                    if (estudiantes.isEmpty()) {
                        System.out.println("No existen estudiantes.");
                        break;
                    }

                    System.out.println("\nEstudiantes:");

                    for (int i = 0; i < estudiantes.size(); i++) {

                        System.out.println((i + 1) + ". "
                                + estudiantes.get(i).getNombre());

                    }

                    System.out.print("Seleccione un estudiante: ");

                    seleccion = sc.nextInt();

                    if (seleccion >= 1 && seleccion <= estudiantes.size()) {

                        curso.eliminar(estudiantes.get(seleccion - 1));

                        System.out.println("Estudiante eliminado.");

                    }

                    break;

                case 6:

                    if (curso == null) {

                        System.out.println("No existe un curso.");

                    } else {

                        System.out.println("\n===== ESTUDIANTES =====");

                        curso.listarEstudiantes();

                    }

                    break;

                case 7:

                    if (curso == null) {

                        System.out.println("No existe un curso.");

                    } else {

                        System.out.println("\n===== INSCRIPCIONES =====");

                        for (Inscripcion i : curso.getInscripciones()) {

                            System.out.println("-----------------------------");
                            System.out.println("Estudiante: "
                                    + i.getEstudiante().getNombre());
                            System.out.println("Fecha: "
                                    + i.getFecha());

                        }

                    }

                    break;

                case 8:

                    System.out.println("Gracias por usar EduStream.");

                    break;

                default:

                    System.out.println("Opción incorrecta.");

            }

        } while (opcion != 8);

        sc.close();

    }

}