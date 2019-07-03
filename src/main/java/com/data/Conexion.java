
package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    private Connection jdbcConenection;
    private String jdbcUrl;
    private String jdbcUserName;
    private String jdbcPassword;
    private String jdbcServer;
    private String jdbcDataBase;

    public Conexion(Connection jdbcConenection, String jdbcUrl, String jdbcUserName) {
        this.jdbcConenection = jdbcConenection;
        this.jdbcUrl = jdbcUrl;
        this.jdbcUserName = jdbcUserName;
    }

    public Conexion() {
        this.jdbcServer="localhost";
        this.jdbcDataBase="estudiantes";
        this.jdbcUrl="jdbc:mysql://"+this.jdbcServer+":3306/"+this.jdbcDataBase;
        this.jdbcUserName="root";
        this.jdbcPassword="";
    }
    
    public void conectar() throws SQLException {
        if (jdbcConenection == null || jdbcConenection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                 System.out.println("No Conectado a la BD...");
                throw new SQLException(e);
            }
            jdbcConenection = DriverManager.getConnection(
                    jdbcUrl, jdbcUserName, jdbcPassword);
            System.out.println("Conectado a la BD...");
        }
      
    }

    public void desconectar() throws SQLException {
        if (jdbcConenection != null && !jdbcConenection.isClosed()) {
            jdbcConenection.close();
        }
    }

    public Connection getJdbcConnection() {
        return jdbcConenection;
    }
        
}
