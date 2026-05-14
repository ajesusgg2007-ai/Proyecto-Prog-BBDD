import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGuardarCliente extends JFrame {
    private JPanel panel1;
    // Campos de texto para que el usuario escriba
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JButton btnGuardar;

    public VentanaGuardarCliente() {
        // Configuración básica de la ventana
        setContentPane(panel1);
        setTitle("Añadir Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String tel = txtTelefono.getText();
        String email = txtEmail.getText();

        // 2. Creamos la conexión (usando tu clase ConexionMySQL)
        ConexionMySQL conexion = new ConexionMySQL("root", "", "mecanico");

        try {
            conexion.conectar();

            // 3. Montamos la consulta SQL con los valores recogidos
            // IMPORTANTE: Los textos van entre comillas simples ''
            String sql = "INSERT INTO clientes (ID_Cliente, Nombre, Telefono, Email) VALUES ("
                    + id + ", '" + nombre + "', " + tel + ", '" + email + "')";

            int resultado = conexion.ejecutarInsertDeleteUpdate(sql);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "¡Cliente " + nombre + " guardado con éxito!");
                limpiarCampos();
            }

            conexion.desconectar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
}