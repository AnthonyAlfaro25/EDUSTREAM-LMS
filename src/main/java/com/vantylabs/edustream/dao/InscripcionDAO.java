package com.vantylabs.edustream.dao;

import com.vantylabs.edustream.Conexion;
import com.vantylabs.edustream.Curso;
import com.vantylabs.edustream.Estudiante;
import com.vantylabs.edustream.Inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la gestión de inscripciones.
 *
 * @author Fabricio
 */

public class InscripcionDAO implements ICrudDAO<Inscripcion> {

    private final Conexion conexion = new Conexion();

   @Override
public boolean insertar(Inscripcion inscripcion) throws SQLException {

    String verificarSql = """
            SELECT COUNT(*)
            FROM inscripciones
            WHERE id_estudiante = ?
            AND id_curso = ?
            """;

    String insertarSql = """
            INSERT INTO inscripciones
            (id_estudiante, id_curso, fecha_inscripcion)
            VALUES (?, ?, ?)
            """;

    try (Connection con = conexion.getConexion()) {

        // Verificar si el estudiante ya está inscrito
        try (PreparedStatement psVerificar =
                     con.prepareStatement(verificarSql)) {

            psVerificar.setInt(1, inscripcion.getEstudiante().getId());
            psVerificar.setInt(2, inscripcion.getCurso().getId());

            try (ResultSet rs = psVerificar.executeQuery()) {

                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }
        }

        // Insertar la inscripción
        try (PreparedStatement psInsertar =
                     con.prepareStatement(insertarSql)) {

            psInsertar.setInt(
                    1,
                    inscripcion.getEstudiante().getId()
            );

            psInsertar.setInt(
                    2,
                    inscripcion.getCurso().getId()
            );

            psInsertar.setDate(
                    3,
                    java.sql.Date.valueOf(inscripcion.getFecha())
            );

            return psInsertar.executeUpdate() > 0;
        }
    }
}

    @Override
public List<Inscripcion> obtenerTodos() throws SQLException {

    List<Inscripcion> lista = new ArrayList<>();

    String sql = """
            SELECT i.id_inscripcion,
                   i.fecha_inscripcion,

                   e.id_usuario AS id_estudiante,
                   e.nombre AS nombre_estudiante,
                   e.email AS email_estudiante,

                   c.id_curso,
                   c.nombre_curso,
                   c.descripcion

            FROM inscripciones i

            INNER JOIN usuarios e
                    ON i.id_estudiante = e.id_usuario

            INNER JOIN cursos c
                    ON i.id_curso = c.id_curso

            ORDER BY i.id_inscripcion
            """;

    try (Connection con = conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            Inscripcion inscripcion = new Inscripcion();

            inscripcion.setId(
                    rs.getInt("id_inscripcion")
            );

            Estudiante estudiante = new Estudiante();

            estudiante.setId(
                    rs.getInt("id_estudiante")
            );

            estudiante.setNombre(
                    rs.getString("nombre_estudiante")
            );

            estudiante.setEmail(
                    rs.getString("email_estudiante")
            );

            Curso curso = new Curso();

            curso.setId(
                    rs.getInt("id_curso")
            );

            curso.setNombre(
                    rs.getString("nombre_curso")
            );

            curso.setDescripcion(
                    rs.getString("descripcion")
            );

            inscripcion.setEstudiante(estudiante);
            inscripcion.setCurso(curso);

            inscripcion.setFecha(
                    rs.getDate("fecha_inscripcion").toLocalDate()
            );

            lista.add(inscripcion);
        }
    }

    return lista;
}

    @Override
    public boolean eliminarPorId(int id) throws SQLException {

        String sql = """
                DELETE FROM inscripciones
                WHERE id_inscripcion = ?
                """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        }

    }
    public List<Estudiante> obtenerEstudiantesPorCurso(int idCurso) throws SQLException {
    List<Estudiante> lista = new ArrayList<>();

    // Consulta SQL que une la tabla de inscripciones con la de usuarios
    String sql = """
                 SELECT u.id_usuario, u.nombre, u.email
                 FROM usuarios u
                 INNER JOIN inscripciones i ON u.id_usuario = i.id_estudiante
                 WHERE i.id_curso = ? AND u.rol = 'ESTUDIANTE'
                 ORDER BY u.nombre
                 """;

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCurso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Estudiante estudiante = new Estudiante();

                    estudiante.setId(rs.getInt("id_usuario"));
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setEmail(rs.getString("email"));

                    lista.add(estudiante);
                }
            }
        }
        return lista;
    }
}