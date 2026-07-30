/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vantylabs.edustream.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD.
 *
 * @author Fabricio
 * @param <T>
 */
public interface ICrudDAO<T> {

    /**
     * Inserta un registro en la base de datos.
     *
     * @param objeto Objeto que se desea insertar.
     * @return true si la operación fue exitosa.
     * @throws SQLException
     */
    boolean insertar(T objeto) throws SQLException;

    /**
     * Obtiene todos los registros de la tabla.
     *
     * @return Lista de objetos.
     * @throws SQLException
     */
    List<T> obtenerTodos() throws SQLException;

    /**
     * Elimina un registro según su ID.
     *
     * @param id Identificador del registro.
     * @return true si fue eliminado.
     * @throws SQLException
     */
    boolean eliminarPorId(int id) throws SQLException;

}