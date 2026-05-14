package main;

import VentanaGuardarCliente.VentanaGuardarCliente;
import VentanaTitulo.VentanaTitulo;

public class Main {
    public static void main(String[] args) {
        // Sustituye con tus datos reales de MySQL
        String usuario = "root";
        String password = "";
        String nombreBD = "mecanico";

        ConexionMySQL conexion = new ConexionMySQL("root", "", "mecanico");

        VentanaTitulo ventana = new VentanaTitulo();
        ventana.setVisible(true);


    }

}