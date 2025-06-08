package app.ciudadninosapp;

// Java program to implement a simple JDBC application
import java.sql.*;

public class App {

    private static Connection connection;

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/ciudadninosdb";// URL de la Base de Datos
        String driver = "com.mysql.cj.jdbc.Driver";// URL del Driver
        String username = "root"; // Nombre del usuario que hace la consulta en esta instancia
        String password = "1234"; // Contraseña del usuario que hace la consulta en esta instancia
        
        
        // Intentamos establecer la conección con la base de datos
        try {
            Class.forName(driver); // Establecemoss el driver 
            connection = DriverManager.getConnection( url, username, password);// Establecemos la conección
            
        }
        catch (ClassNotFoundException e) {
            System.err.println("No se encontró el JDBC Driver: "  + e.getMessage());
        }
        catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        /**
         * DATOS DE UNA CONSULTA GENERAL
         */
        String input = "INSERT INTO programa (nro_programa, descripcion, nombre) VALUES (2022, 'Centro de baile folklórico para la expresión infantil', 'Baile folklórico')";// Ejemplo Insert de alguna tabla
        programaInsert(input);

        connection.close(); // Tengo duda de si haría falta esto al final
    }

    public static void programaInsert(String input){
        // Intentamos un input
        try {
            Statement st = connection.createStatement(); // Crea instancia de un statement

            int count = st.executeUpdate(input); // Ejecutar la consulta

            System.out.println("Número de filas afectadas por esta consulta: " + count); //debug

            // Cerrar la conección
            st.close(); //cierra el statement
            
            System.out.println("Conección cerrada."); //debug
            
        }
        catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

}