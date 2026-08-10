package com.vantylabs.edustream;

// @author Anthony
/* Clase de conexion con MySQL */

//Importe de bibliotecas del JBDC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Se establecen los parametros de acceso a MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/edustream_db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "";
    
    // Se realiza la conexion y se controla el error mediante SQL Exception
    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
}
