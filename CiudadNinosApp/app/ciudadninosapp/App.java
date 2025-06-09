package app.ciudadninosapp;

// Java program to implement a simple JDBC application
import java.sql.*;
import java.util.Scanner;

public class App {

    private static Connection connection;

    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Insertar padrino");
            System.out.println("2. Eliminar un donante");
            System.out.println("3. Listar padrinos con programas");
            System.out.println("4. Total de aportes mensuales por programa");
            System.out.println("5. Donantes que aportan a más de dos programas");
            System.out.println("6. Donantes con aportes mensuales y medios de pago");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    insertarPadrino(connection);
                    break;
                case 2:
                    eliminarDonante(connection);
                    break;
                case 3:
                    listarPadrinosConProgramas(connection);
                    break;
                case 4:
                    mostrarTotalAportesPorPrograma(connection);
                    break;
                case 5:
                    mostrarDonantesConMasDeDosProgramas(connection);
                    break;
                case 6:
                    mostrarDonantesConMediosDePago(connection);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

            System.out.println();

        } while (opcion != 0);

        DBConnection.close(); // Cerrar conexion al final
        scanner.close();
        
    }


    public static void insertarPadrino(Connection conn) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("===== Insertar nuevo padrino =====");

            System.out.print("Dni: ");
            String dni = scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
            String fechaNacimiento = scanner.nextLine();

            System.out.print("Telefono movil: ");
            String telMovil = scanner.nextLine();

            System.out.print("Telefono fijo: ");
            String telFijo = scanner.nextLine();

            System.out.print("Codigo postal: ");
            String codPostal = scanner.nextLine();

            System.out.print("Direccion: ");
            String direccion = scanner.nextLine();

            System.out.print("Facebook: ");
            String facebook = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Validaciones para prevenir errores antes de llegar a la base de datos
            if(dni.length() < 7){
                System.out.println("Error: el DNI debe tener al menos 7 caracteres.");
                return;
            }

            if(telMovil == telFijo){
                System.out.println("Error: El telefono fijo no puede ser igual que el movil.");
                return;
            }

            /**
             * Los ? son placeholders. Se usan en PreparedStatement para
             * insertar valores de forma segura y manejar conversiones de tipos
             * automaticamente, como string a varchar. 
             */
            String sql = """
            INSERT INTO Padrino 
            (dni, nombre, apellido, fecha_nacimiento, tel_movil, tel_fijo, cod_postal, direccion, facebook, email) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1,dni); // reemplazar el primer ? de sql por dni
                ps.setString(2,nombre); // reemplazar el segundo ? de sql por nombre
                ps.setString(3,apellido); // reemplazar el tercer ? de sql por apellido
                ps.setString(4,fechaNacimiento); 
                ps.setString(5,telMovil); 
                ps.setString(6,telFijo); 
                ps.setString(7,codPostal);
                ps.setString(8,direccion);
                ps.setString(9,facebook); 
                ps.setString(10,email); 

                // Ejecuta la consulta SQL que se preparo con PreparedStatement. Es una sentencia insert.
                int count = ps.executeUpdate();
                System.out.println("Se inserto correctamente el padrino. Filas afectadas: " + count);
            }
 
        } catch (SQLException e) { // para errores que violan restricciones, como dni duplicado
            System.err.println("Error SQL al insertar padrino: " + e.getMessage());
        } catch (IllegalArgumentException e) { // para atrapar errores al convertir fechas
            System.err.println("Formato de fecha incorrecto.");
        }
    }

    public static void eliminarDonante(Connection conn) {
        return;
    }

    public static void listarPadrinosConProgramas(Connection conn) {
        return;
    }

    public static void mostrarTotalAportesPorPrograma(Connection conn) {
        return;
    }

    public static void mostrarDonantesConMasDeDosProgramas(Connection conn) {
        return;
    }

    public static void mostrarDonantesConMediosDePago(Connection conn) {
        return;
    }

}