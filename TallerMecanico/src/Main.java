import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Sustituye con tus datos reales de MySQL
        String usuario = "root";
        String password = "";
        String nombreBD = "mecanico";

        ConexionMySQL conexion = new ConexionMySQL("root", "", "mecanico");

        try {
            conexion.conectar();
            System.out.println("¡Conectado con éxito!");
            // Aquí podrías usar conexion.ejecutarSelect("SELECT...");
            conexion.desconectar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}