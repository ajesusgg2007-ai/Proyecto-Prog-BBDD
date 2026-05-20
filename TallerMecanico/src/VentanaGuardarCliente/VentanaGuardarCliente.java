package VentanaGuardarCliente;

import main.ConexionMySQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGuardarCliente extends JFrame {
    private JPanel panel1;
    // Campos de texto para que el usuario escriba
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JButton btnGuardar;

    public VentanaGuardarCliente() {
        // Configuración básica de la ventana
        setContentPane(panel1);
        setTitle("Añadir Cliente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Acción del botón
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });
    }

    private void guardarCliente() {
        // 1. Recogemos los valores de los cuadros de texto

        String nombre = txtNombre.getText();
        String tel = txtTelefono.getText();
        String email = txtEmail.getText();

        ConexionMySQL conexion = new ConexionMySQL("root", "", "mecanico");

        try {
            conexion.conectar();

            // 3. Montamos la consulta SQL con los valores recogidos
            // IMPORTANTE: Los textos van entre comillas simples ''
            String sql = "INSERT INTO clientes (Nombre, Telefono, Email) VALUES (' "+
                    nombre + "', " + tel + ", '" + email + "')";

            int resultado = conexion.ejecutarInsertDeleteUpdate(sql);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente " + nombre + " guardado con éxito");
                limpiarCampos();
            }

            conexion.desconectar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }

    private void createUIComponents() {
       panel1 = new JPanel() {
           @Override
           protected void paintComponent(Graphics g) {
               super.paintComponent(g);
               try {
                   ImageIcon image = new ImageIcon(getClass().getResource("//imagenes/tu_imagen.png"));
                   if (image.getImage() != null) {
                       g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
                   }
               } catch (Exception e) {
                   System.err.println("No se pudo cargar la imagen de fondo");
               }
           }
       };
    }
}