public class Main {
    public static void main(String[] args) {
        // Sustituye con tus datos reales de MySQL
        String usuario = "root";
        String password = "";
        String nombreBD = "mecanico";

        ConexionMySQL conexion = new ConexionMySQL("root", "", "mecanico");

        VentanaGuardarCliente ventana = new VentanaGuardarCliente();
        ventana.setVisible(true);

    }

}