/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

        String sql = """
                INSERT INTO inscripciones
                (id_estudiante, id_curso, fecha_inscripcion)
                VALUES (?, ?, ?)
                """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, inscripcion.getEstudiante().getId());
            ps.setInt(2, inscripcion.getCurso().getId());
            ps.setDate(3, java.sql.Date.valueOf(inscripcion.getFecha()));

            return ps.executeUpdate() > 0;
        }

    }

    @Override
    public List<Inscripcion> obtenerTodos() throws SQLException {

        List<Inscripcion> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM inscripciones
                ORDER BY id_inscripcion
                """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Inscripcion inscripcion = new Inscripcion();

                inscripcion.setId(rs.getInt("id_inscripcion"));

                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id_estudiante"));

                Curso curso = new Curso();
                curso.setId(rs.getInt("id_curso"));

                inscripcion.setEstudiante(estudiante);
                inscripcion.setCurso(curso);

                inscripcion.setFecha(
                        rs.getDate("fecha_inscripcion").toLocalDate());

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

}