/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vantylabs.edustream.dao;

import com.vantylabs.edustream.Conexion;
import com.vantylabs.edustream.Curso;
import com.vantylabs.edustream.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la gestión de cursos.
 *
 * @author Fabricio
 */
public class CursoDAO implements ICrudDAO<Curso> {

    private final Conexion conexion = new Conexion();

    @Override
    public boolean insertar(Curso curso) throws SQLException {

        String sql = """
                INSERT INTO cursos
                (nombre_curso, descripcion, id_profesor)
                VALUES (?, ?, ?)
                """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, curso.getNombre());
            ps.setString(2, curso.getDescripcion());
            ps.setInt(3, curso.getProfesor().getId());

            return ps.executeUpdate() > 0;
        }

    }

    @Override
    public List<Curso> obtenerTodos() throws SQLException {

        List<Curso> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM cursos
                ORDER BY id_curso
                """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Curso curso = new Curso();

                curso.setId(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre_curso"));
                curso.setDescripcion(rs.getString("descripcion"));

                Profesor profesor = new Profesor();
                profesor.setId(rs.getInt("id_profesor"));

                curso.setProfesor(profesor);

                lista.add(curso);

            }

        }

        return lista;

    }

    @Override
    public boolean eliminarPorId(int id) throws SQLException {

        String sql = """
                DELETE FROM cursos
                WHERE id_curso = ?
                """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        }

    }

}
