package app.ciudadninosapp;

// Java program to implement a simple JDBC application
import java.sql.*;
import java.util.Scanner;

public class App {

    private static Connection connection;

    public static void main(String[] args) {
        connection = DBConnection.getInstance();

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
            String sql = "INSERT INTO Padrino (dni, nombre, apellido, fecha_nacimiento, tel_movil, tel_fijo, cod_postal, direccion, facebook, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
                System.out.println("Se insertó correctamente el padrino. Filas afectadas: " + count);
            }
 
        } catch (SQLException e) { // para errores que violan restricciones, como dni duplicado
            System.err.println("Error SQL al insertar padrino: " + e.getMessage());
        } catch (IllegalArgumentException e) { // para atrapar errores al convertir fechas
            System.err.println("Formato de fecha incorrecto.");
        }

        
    }

    public static void eliminarDonante(Connection conn) {
        
    	Scanner scanner = new Scanner(System.in);
	
        System.out.print("Ingrese el DNI del donante a eleminar: ");
        String dni = scanner.nextLine();

        String query = "DELETE FROM Padrino WHERE dni = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
                ps.setString(1, dni);
                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Donante eliminado correctamente.");
                } else {
                    System.out.println("No se encontró donante con ese DNI.");
                }
            } catch (SQLException e) {
                System.err.println("Error al eliminar donante: " + e.getMessage());
            }	

            return;
            }

            public static void listarPadrinosConProgramas(Connection conn) {
                
            String sql ="SELECT p.dni, p.nombre, p.apellido, a.nro_programa FROM Padrino p JOIN Aporte a ON p.dni = a.dni ORDER BY p.apellido, p.nombre, a.nro_programa";
            
            System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.println("----------------------------------------- Consulta En MySQL ---------------------------------------");
                System.out.println(sql);
                System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.print("\n");


            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                    System.out.println("DNI | Nombre | Apellido | Programa");

                    while (rs.next()) {
                            String dni = rs.getString("dni");
                            String nombre = rs.getString("nombre");
                            String apellido = rs.getString("apellido");
                            String programa = rs.getString("nro_programa");

                            System.out.printf("%s | %s | %s | %s%n", dni, nombre, apellido, programa);
                    }

                } catch (SQLException e) {
                    System.err.println("Error al listar padrinos con programas: " + e.getMessage());
                }    
            return;
    }

    public static void mostrarTotalAportesPorPrograma(Connection conn) {
        try{
            Statement statement = conn.createStatement();
            String query = "SELECT nro_programa, SUM(monto) as total_aporte_mensual \n" + //
                                "FROM Aporte \n" + //
                                "GROUP BY nro_programa";

            //Envía en query a la base de datos y almacena el resulatdo.
            ResultSet resultSet = statement.executeQuery(query);
		
	        System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------- Consulta En MySQL ---------------------------------------");
            System.out.println(query);
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.print("\n");

            
            // Muestra los resultados.
            System.out.println("N° Programa | Total Aportes |");
            while(resultSet.next())
            {
                String nro_programa = resultSet.getString("nro_programa");
                String total_aportes = resultSet.getString("total_aporte_mensual");

                System.out.printf("  %s       | %s%n", nro_programa, total_aportes);
            } 
        } catch (SQLException e) { 
            System.err.println("Error SQL al consultar el programa: " + e.getMessage());
        }

        return;
    }

    public static void mostrarDonantesConMasDeDosProgramas(Connection conn) {
        try{
            Statement statement = conn.createStatement();
            String query = "SELECT a.dni, p.nombre, p.apellido \n" + //
                                "FROM Aporte a \n" + //
                                "NATURAL JOIN Padrino p \n" + //
                                "GROUP BY a.dni \n" + //
                                "HAVING COUNT(DISTINCT nro_programa) > 2";

            //Envía en query a la base de datos y almacena el resulatdo.
            ResultSet resultSet = statement.executeQuery(query);
            System.out.print("\n ");

	    System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------- Consulta En MySQL ---------------------------------------");
            System.out.println(query);
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.print("\n");

            // Muestra los resultados.
            System.out.println("DNI | Nombre | Apellido |");
            while(resultSet.next())
            {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("p.nombre");
                String apellido = resultSet.getString("p.apellido");
                
                System.out.printf("  %s       | %s  | %s%n", dni, nombre, apellido);
            } 
        } catch (SQLException e) { 
            System.err.println("Error SQL al consultar el programa: " + e.getMessage());
        }

        return;
    }

    public static void mostrarDonantesConMediosDePago(Connection conn) {
        try{
            Statement statement = conn.createStatement();
            String query = "SELECT \r\n" + //
                                "    a.dni,\r\n" + //
                                "    m.nombre_titular,\r\n" + //
                                "    m.tipo_tarjeta,\r\n" + //
                                "    \r\n" + //
                                "    -- Datos específicos de tarjeta de crédito\r\n" + //
                                "    tc.nro_tarjeta,\r\n" + //
                                "    tc.nombre_tarjeta,\r\n" + //
                                "    tc.fecha_vencimiento,\r\n" + //
                                "\r\n" + //
                                "    -- Datos específicos de débito/transferencia\r\n" + //
                                "    dt.cbu,\r\n" + //
                                "    dt.nro_cuenta,\r\n" + //
                                "    dt.nombre_banco,\r\n" + //
                                "    dt.sucursal_banco,\r\n" + //
                                "    dt.tipo_cuenta\r\n" + //
                                "\r\n" + //
                                "FROM Aporte a\r\n" + //
                                "JOIN Medio_De_Pago m ON a.id_medio_pago = m.id_medio_pago\r\n" + //
                                "LEFT JOIN Tarjeta_Credito tc ON m.id_medio_pago = tc.id_medio_pago\r\n" + //
                                "LEFT JOIN Debito_o_Transferencia dt ON m.id_medio_pago = dt.id_medio_pago\r\n" + //
                                "WHERE a.frecuencia = 'mensual';\r\n" + //
                                "";

            //Envía en query a la base de datos y almacena el resulatdo.
            ResultSet resultSet = statement.executeQuery(query);
            System.out.print("\n");
	    
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------- Consulta En MySQL ---------------------------------------");
            System.out.println(query);
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.print("\n");


            // Muestra los resultados.
            System.out.printf("%-10s | %-20s | %-10s | %-20s | %-20s | %-15s | %-20s\n","DNI", "Nombre Titular", "Tipo", "Tarjeta/CBU", "Cuenta/Tarjeta", "Banco", "Extra Info");


            while(resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre_titular");
                String tipo = resultSet.getString("tipo_tarjeta");

                // Variables comunes
                String tarjetaOCBU = "";
                String cuentaOTarjeta = "";
                String banco = "";
                String extra = "";

                if ("Credito".equalsIgnoreCase(tipo)) {
                    tarjetaOCBU = resultSet.getString("nro_tarjeta");
                    cuentaOTarjeta = resultSet.getString("nombre_tarjeta");
                    extra = resultSet.getString("fecha_vencimiento");
                } else if ("Debito".equalsIgnoreCase(tipo) || "Transferencia".equalsIgnoreCase(tipo)) {
                    tarjetaOCBU = resultSet.getString("cbu");
                    cuentaOTarjeta = resultSet.getString("nro_cuenta");
                    banco = resultSet.getString("nombre_banco");
                    extra = resultSet.getString("tipo_cuenta") + " / Sucursal: " + resultSet.getString("sucursal_banco");
                }

                // Reemplaza nulls con cadena vacía
                tarjetaOCBU = tarjetaOCBU != null ? tarjetaOCBU : "";
                cuentaOTarjeta = cuentaOTarjeta != null ? cuentaOTarjeta : "";
                banco = banco != null ? banco : "";
                extra = extra != null ? extra : "";

                System.out.printf("%-10s | %-20s | %-10s | %-20s | %-20s | %-15s | %-20s\n",
                    dni, nombre, tipo, tarjetaOCBU, cuentaOTarjeta, banco, extra);
            }


            return;
        } catch (SQLException e) { 
            System.err.println("Error SQL al consultar el programa: " + e.getMessage());
        }
    }
}
