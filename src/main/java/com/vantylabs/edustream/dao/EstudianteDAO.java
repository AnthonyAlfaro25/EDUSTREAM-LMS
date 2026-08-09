package com.vantylabs.edustream.dao;

import com.vantylabs.edustream.Conexion;
import com.vantylabs.edustream.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabricio
 */
public class EstudianteDAO implements ICrudDAO<Estudiante> {

    private final Conexion conexion = new Conexion();

    @Override
    public boolean insertar(Estudiante estudiante) throws SQLException {

        String sql = """
                     INSERT INTO usuarios
                     (nombre,email,password,rol)
                     VALUES(?,?,?,?)
                     """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getEmail());
            ps.setString(3, estudiante.getPassword());
            ps.setString(4, "ESTUDIANTE");

            return ps.executeUpdate() > 0;
        }

    }

    @Override
    public List<Estudiante> obtenerTodos() throws SQLException {

        List<Estudiante> lista = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM usuarios
                     WHERE rol='ESTUDIANTE'
                     ORDER BY id_usuario
                     """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Estudiante estudiante = new Estudiante();

                estudiante.setId(rs.getInt("id_usuario"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setEmail(rs.getString("email"));
                estudiante.setPassword(rs.getString("password"));

                lista.add(estudiante);

            }

        }

        return lista;

    }

    @Override
    public boolean eliminarPorId(int id) throws SQLException {

        String sql = """
                     DELETE FROM usuarios
                     WHERE id_usuario=?
                     AND rol='ESTUDIANTE'
                     """;

        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        }

    }
    
    public Estudiante autenticar(String usuario, String password) throws SQLException {
    String sql = """
                 SELECT id_usuario, nombre, email, password
                 FROM usuarios
                 WHERE email = ? AND password = ? AND rol = 'ESTUDIANTE'
                 """;

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setId(rs.getInt("id_usuario"));
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setEmail(rs.getString("email"));
                    estudiante.setPassword(rs.getString("password"));
                    return estudiante;
                }
            }
        }
    return null; // Si las credenciales son incorrectas
    }
}