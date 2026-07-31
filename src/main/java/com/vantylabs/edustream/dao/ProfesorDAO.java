/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vantylabs.edustream.dao;

import com.vantylabs.edustream.Conexion;
import com.vantylabs.edustream.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la gestion de profesores.
 *
 * @author Fabricio
 */
public class ProfesorDAO implements ICrudDAO<Profesor> {


    @Override
    public boolean insertar(Profesor profesor) throws SQLException {

        String sql = """
                INSERT INTO usuarios
                (nombre, email, password, rol)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getEmail());
            ps.setString(3, profesor.getPassword());
            ps.setString(4, "PROFESOR");

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Profesor> obtenerTodos() throws SQLException {

        List<Profesor> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM usuarios
                WHERE rol = 'PROFESOR'
                ORDER BY id_usuario
                """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Profesor profesor = new Profesor();

                profesor.setId(rs.getInt("id_usuario"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setEmail(rs.getString("email"));
                profesor.setPassword(rs.getString("password"));

                lista.add(profesor);
            }
        }

        return lista;
    }

    @Override
    public boolean eliminarPorId(int id) throws SQLException {

        String sql = """
                DELETE FROM usuarios
                WHERE id_usuario = ?
                AND rol = 'PROFESOR'
                """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
    
    public Profesor autenticar(String usuario, String password) throws SQLException {
    String sql = """
                 SELECT id_usuario, nombre, email, password
                 FROM usuarios
                 WHERE email = ? AND password = ? AND rol = 'PROFESOR'
                 """;

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Profesor profesor = new Profesor();
                    profesor.setId(rs.getInt("id_usuario"));
                    profesor.setNombre(rs.getString("nombre"));
                    profesor.setEmail(rs.getString("email"));
                    profesor.setPassword(rs.getString("password"));
                    return profesor;
                }
            }
        }
        return null; // Si las credenciales son incorrectas
    }

}