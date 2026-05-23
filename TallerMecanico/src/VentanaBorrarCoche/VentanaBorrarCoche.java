package VentanaBorrarCoche;

import main.ConexionMySQL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaBorrarCoche extends JFrame {
    private JPanel panel1;
    private JTextField Matricula;
    private JButton buscarButton;
    private JButton borrarCocheButton;
    private JButton Volver;
    private JTextArea infoArea; // Para mostrar los datos encontrados

    public VentanaBorrarCoche() {
        panel1 = new JPanel(new GridLayout(6, 1, 10, 10));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Matricula = new JTextField();
        buscarButton = new JButton("Buscar Vehículo");
        borrarCocheButton = new JButton("Borrar Vehículo");
        Volver = new JButton("Volver");
        infoArea = new JTextArea(3, 20);
        infoArea.setEditable(false);

        panel1.add(new JLabel("Introduce la Matrícula:"));
        panel1.add(Matricula);
        panel1.add(buscarButton);
        panel1.add(new JScrollPane(infoArea));
        panel1.add(borrarCocheButton);
        panel1.add(Volver);

        setContentPane(panel1);
        setTitle("Gestionar Borrado de Coche");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Lógica de búsqueda
        buscarButton.addActionListener(e -> buscarCoche());

        // Lógica de borrado
        borrarCocheButton.addActionListener(e -> borrarCoche());

        Volver.addActionListener(e -> {
            VentanaTitulo.VentanaTitulo v = new VentanaTitulo.VentanaTitulo();
            v.setVisible(true);
            dispose();
        });
    }

    private void buscarCoche() {
        String mat = Matricula.getText().trim();
        if (mat.isEmpty()) return;

        ConexionMySQL conn = new ConexionMySQL("root", "", "mecanico");
        try {
            conn.conectar();
            // Hacemos un JOIN para traer el nombre del cliente asociado
            String sql = "SELECT v.Marca, v.Modelo, c.Nombre FROM vehículos v " +
                    "JOIN clientes c ON v.ID_Cliente = c.ID_Cliente " +
                    "WHERE v.Matrícula = '" + mat + "'";
            ResultSet rs = conn.ejecutarSelect(sql);

            if (rs.next()) {
                infoArea.setText("Marca: " + rs.getString("Marca") +
                        "\nModelo: " + rs.getString("Modelo") +
                        "\nPropietario: " + rs.getString("Nombre"));
            } else {
                infoArea.setText("No se encontró ningún vehículo con esa matrícula.");
            }
            conn.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void borrarCoche() {
        String mat = Matricula.getText().trim();
        if (mat.isEmpty()) return;

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de borrar este vehículo?");
        if (confirm != JOptionPane.YES_OPTION) return;

        ConexionMySQL conn = new ConexionMySQL("root", "", "mecanico");
        try {
            conn.conectar();
            int filas = conn.ejecutarInsertDeleteUpdate("DELETE FROM `vehículos` WHERE `Matrícula` = '" + mat + "'");
            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Vehículo eliminado con éxito.");
                Matricula.setText("");
                infoArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al borrar.");
            }
            conn.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}