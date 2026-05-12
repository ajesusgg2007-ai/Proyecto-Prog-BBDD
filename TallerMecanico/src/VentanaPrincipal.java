import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal {
    private JPanel mainPanel; // El panel que contiene tus botones
    private JButton añadirButton;
    private JButton eliminarButton;

    // Constructor de la clase
    public VentanaPrincipal() {
        // Aquí es donde Swing suele conectar los componentes del .form
        // Si usas IntelliJ IDEA, esto se genera automáticamente.
    }

    public static void main(String[] args) {
        // Creamos el marco (la ventana)
        JFrame frame = new JFrame("Mi Aplicación");

        // Instanciamos nuestra clase
        VentanaPrincipal gui = new VentanaPrincipal();

        // Configuramos la ventana
        frame.setContentPane(gui.mainPanel); // Asignamos el panel principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Ajusta el tamaño al contenido
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.setVisible(true); // ¡La hace aparecer!
    }
}
