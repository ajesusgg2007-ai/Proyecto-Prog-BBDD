package VentanaRegistrarCoche;

import main.ConexionMySQL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistrarCoche extends JFrame {
    private JPanel panel1;
    private JButton registrarCocheButton;
    private JTextField Matricula;
    private JTextField Marca;
    private JTextField Modelo;
    private JTextField Arreglo;
    private JRadioButton siRadioButton;
    private JRadioButton noRadioButton;
    private JTextField Telefono;
    private JButton Volver;

    // Constructor de la ventana
    public VentanaRegistrarCoche() {
        // Asignamos el panel principal (obligatorio en el diseñador de IntelliJ)
        setContentPane(panel1);
        setTitle("Registrar Coche");
        setSize(400, 470);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Agrupar los RadioButtons para que sean mutuamente excluyentes
        ButtonGroup grupoLimpiado = new ButtonGroup();
        grupoLimpiado.add(siRadioButton);
        grupoLimpiado.add(noRadioButton);
        noRadioButton.setSelected(true); // Selección por defecto

        // 2. Asignar el evento al botón de registrar
        registrarCocheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCocheEnBaseDeDatos();
            }
        });

        Volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Creamos la ventana de título de nuevo
                VentanaTitulo.VentanaTitulo ventanaPrincipal = new VentanaTitulo.VentanaTitulo();

                // 2. La hacemos visible
                ventanaPrincipal.setVisible(true);

                // 3. Destruimos la ventana actual de registrar coche para liberar memoria
                dispose();
            }
        });
    }

    private void registrarCocheEnBaseDeDatos() {
        // 1. Capturar y validar los datos de la interfaz gráfica
        String matriculaTexto = Matricula.getText().trim();
        String marcaTexto = Marca.getText().trim();
        String modeloTexto = Modelo.getText().trim();
        String arregloTexto = Arreglo.getText().trim();
        String telefonoTexto = Telefono.getText().trim(); // Capturamos el teléfono

        // Validación de campos vacíos
        if (matriculaTexto.isEmpty() || marcaTexto.isEmpty() || modeloTexto.isEmpty() || arregloTexto.isEmpty() || telefonoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, rellene todos los campos del formulario, incluido el Teléfono.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación extra: comprobar que el teléfono sea un número válido
        int telefonoInt;
        try {
            telefonoInt = Integer.parseInt(telefonoTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduzca un número de teléfono válido (solo dígitos).", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean esLimpiado = siRadioButton.isSelected();
        int limpiadoInt = esLimpiado ? 1 : 0;
        String fechaEntrada = java.time.LocalDate.now().toString();

        // 2. Conexión con XAMPP utilizando tu clase ConexionMySQL
        ConexionMySQL conexion = new ConexionMySQL("root", "", "mecanico");

        try {
            conexion.conectar();

            // PASO A: Buscar el ID_Cliente que corresponde a ese Teléfono
            String sqlBuscarCliente = "SELECT ID_Cliente FROM clientes WHERE Telefono = " + telefonoInt;
            java.sql.ResultSet rs = conexion.ejecutarSelect(sqlBuscarCliente);

            int idClienteEncontrado = -1;

            if (rs.next()) {
                // Si el teléfono existe en la tabla de clientes, recuperamos su ID
                idClienteEncontrado = rs.getInt("ID_Cliente");
            } else {
                // Si no existe, lanzamos el aviso para frenar la inserción del coche
                JOptionPane.showMessageDialog(this, "No existe ningún cliente registrado con el teléfono: " + telefonoTexto + ".\nPor favor, registre primero al cliente.", "Cliente no encontrado", JOptionPane.ERROR_MESSAGE);
                conexion.desconectar();
                return;
            }

            // PASO B: Insertar el vehículo usando el ID numérico que acabamos de descubrir
            String sqlInsertarCoche = "INSERT INTO `vehículos` (Matrícula, ID_Cliente, Modelo, Marca, Arreglo, Limpiado, Fecha_Entrada) " +
                    "VALUES ('" + matriculaTexto + "', " + idClienteEncontrado + ", '" + modeloTexto + "', '" + marcaTexto +
                    "', '" + arregloTexto + "', " + limpiadoInt + ", '" + fechaEntrada + "')";

            int filasInsertadas = conexion.ejecutarInsertDeleteUpdate(sqlInsertarCoche);

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "¡Vehículo registrado y asignado con éxito al cliente asociado al teléfono: " + telefonoTexto + "!", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            }

            conexion.desconectar();

        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Método opcional para vaciar el formulario tras un registro exitoso
    private void limpiarCampos() {
        Matricula.setText("");
        Marca.setText("");
        Modelo.setText("");
        Arreglo.setText("");
        noRadioButton.setSelected(true);
    }
}
