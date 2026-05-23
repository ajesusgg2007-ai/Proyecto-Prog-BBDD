package VentanaVerCoche;

import main.ConexionMySQL;
import VentanaTitulo.VentanaTitulo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaVerCoche extends JFrame {
    private JPanel panel1;
    private JTable tablaCoches; // Tu componente JTable del diseñador
    private JButton btnVolver;   // Tu botón de volver
    private DefaultTableModel modeloTabla; // El modelo para manejar los datos

    public VentanaVerCoche() {
        // Configuración obligatoria de IntelliJ Swing
        setContentPane(panel1);
        setTitle("Listado de Vehículos en Taller");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Inicializar el modelo de la tabla con tus columnas exactas
        String[] columnas = {"Matrícula", "ID Cliente", "Modelo", "Marca", "Arreglo", "Limpiado", "Fecha Entrada"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            // Truco opcional: Hacer que las celdas no sean editables al hacer doble clic
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCoches.setModel(modeloTabla);

        // 2. Cargar los datos desde XAMPP
        cargarDatosCoches();

        // 3. Acción del botón Volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaTitulo ventanaPrincipal = new VentanaTitulo();
                ventanaPrincipal.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
    }

    private void cargarDatosCoches() {
        // Limpiamos la tabla por si acaso ya tuviera filas
        modeloTabla.setRowCount(0);

        // Instanciamos tu clase de conexión
        ConexionMySQL conexion = new ConexionMySQL("root", "", "mecanico");

        // Consulta SQL respetando las tildes de tu base de datos
        String sql = "SELECT Matrícula, ID_Cliente, Modelo, Marca, Arreglo, Limpiado, Fecha_Entrada FROM `vehículos`";

        try {
            conexion.conectar();

            // Usamos el método ejecutarSelect de tu clase ConexionMySQL
            ResultSet rs = conexion.ejecutarSelect(sql);

            // Recorremos el ResultSet fila por fila
            while (rs.next()) {
                String matricula = rs.getString("Matrícula");
                int idCliente = rs.getInt("ID_Cliente");
                String modelo = rs.getString("Modelo");
                String marca = rs.getString("Marca");
                String arreglo = rs.getString("Arreglo");

                // Formateamos el TinyInt(1) / Boolean para que sea más visual
                int limpiadoInt = rs.getInt("Limpiado");
                String limpiadoTexto = (limpiadoInt == 1) ? "Sí" : "No";

                String fecha = rs.getString("Fecha_Entrada");

                // Creamos un array con los datos de esta fila
                Object[] fila = {matricula, idCliente, modelo, marca, arreglo, limpiadoTexto, fecha};

                // Agregamos la fila al modelo de la tabla
                modeloTabla.addRow(fila);
            }

            conexion.desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el listado de coches: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}