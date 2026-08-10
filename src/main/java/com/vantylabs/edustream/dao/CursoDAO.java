
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
            SELECT c.id_curso,
                   c.nombre_curso,
                   c.descripcion,
                   u.id_usuario,
                   u.nombre,
                   u.email
            FROM cursos c
            LEFT JOIN usuarios u
                   ON c.id_profesor = u.id_usuario
            ORDER BY c.id_curso
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

            profesor.setId(rs.getInt("id_usuario"));
            profesor.setNombre(rs.getString("nombre"));
            profesor.setEmail(rs.getString("email"));

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
    
   public List<Curso> obtenerPorProfesor(int idProfesor) throws SQLException {

    List<Curso> lista = new ArrayList<>();

    String sql = """
            SELECT c.id_curso,
                   c.nombre_curso,
                   c.descripcion,
                   u.id_usuario,
                   u.nombre,
                   u.email
            FROM cursos c
            LEFT JOIN usuarios u
                   ON c.id_profesor = u.id_usuario
            WHERE c.id_profesor = ?
            ORDER BY c.id_curso
            """;

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idProfesor);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Curso curso = new Curso();

                curso.setId(rs.getInt("id_curso"));
                curso.setNombre(rs.getString("nombre_curso"));
                curso.setDescripcion(rs.getString("descripcion"));

                Profesor profesor = new Profesor();

                profesor.setId(rs.getInt("id_usuario"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setEmail(rs.getString("email"));

                curso.setProfesor(profesor);

                lista.add(curso);
            }
        }
    }

    return lista;
}

}

