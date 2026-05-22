package VentanaTitulo;

import VentanaGuardarCliente.VentanaGuardarCliente;
import VentanaRegistrarCoche.VentanaRegistrarCoche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VentanaTitulo extends JFrame {
    private JPanel panelPrincipal;
    private JButton btnabrirGuardarCliente;
    private JButton registrarCocheButton;

    public VentanaTitulo() {
        setTitle("Mecánicos Salazar");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Acción del botón
        btnabrirGuardarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Creamos la instancia de la ventana de guardado
                // Asegúrate de que el nombre de la clase sea exacto al de tu archivo
                VentanaGuardarCliente GuardarCliente = new VentanaGuardarCliente();

                // 2. La hacemos visible
                GuardarCliente.setVisible(true);

                // 3. (Opcional) Cerramos o minimizamos la ventana actual
                 dispose();
                 setLocationRelativeTo(null);
            }
        });

        registrarCocheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instanciamos la ventana de registro de coche
                VentanaRegistrarCoche registrarCoche = new VentanaRegistrarCoche();

                // La hacemos visible en la pantalla
                registrarCoche.setVisible(true);

                // Opcional: Cerramos la ventana principal actual para que no se queden amontonadas
                dispose();
            }
        });
    }

    private void createUIComponents() {
        panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/title.png")));
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