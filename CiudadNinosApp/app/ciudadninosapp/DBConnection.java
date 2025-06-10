package app.ciudadninosapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que maneja la conexión a la base de datos usando el patrón Singleton.
 * Garantiza que haya una única conexión compartida durante toda la ejecución
 * del programa.
 */
public class DBConnection {

    // Variable estática que guarda la conexión actual.
    private static Connection connection;

    // Se definen los parametros de conexion:
    private static final String url = "jdbc:mysql://localhost:3306/ciudadninosdb";// URL de la Base de Datos y servidor
    private static final String driver = "com.mysql.cj.jdbc.Driver";// URL del Driver
    private static final String username = "root"; // Nombre del usuario que hace la consulta en esta instancia
    private static final String password = "160913aa"; // Contraseña del usuario que hace la consulta en esta instancia

    /**
     * Constructor privado para evitar que se creen instancias desde afuera.
     * Esto es parte de la implementación del patrón Singleton.
     */
    private DBConnection() {
        // Constructor privado: evita instanciación externa
    }

    /**
     * Obtiene una instancia única de la conexión a la base de datos. Si ya
     * existe una conexión activa, la devuelve. Si no, la crea.
     *
     * @return conexión a la base de datos
     */
    public static Connection getInstance() {
        if (connection == null) {
            // Intentamos establecer la conexion con la base de datos
            try {
                Class.forName(driver); // Establecemos el driver de la base de datos si no se cargo 
                connection = DriverManager.getConnection(url, username, password); // Establecemos la conexion de red a la base de datos
            } catch (ClassNotFoundException e) { // Error si no se encuentra el driver JDBC
                System.err.println("No se encontró el JDBC Driver: " + e.getMessage());
            } catch (SQLException e) { // Error si hay problemas al conectarse a la base de datos
                System.err.println("SQL Error: " + e.getMessage());
            }
        }

        // Devuelve la conexion, ya sea nueva o existente
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos si está abierta. Es importante para
     * liberar recursos del sistema.
     */
    public static void close() {
        try {
            // Verifica que la conexión exista y esté abierta
            if (connection != null && !connection.isClosed()) {
                connection.close(); // Cierra la conexion

                    }} catch (SQLException e) {
            // Error al intentar cerrar la conexión
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
