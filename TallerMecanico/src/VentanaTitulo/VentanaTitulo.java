package VentanaTitulo;

import VentanaGuardarCliente.VentanaGuardarCliente;
import VentanaRegistrarCoche.VentanaRegistrarCoche;
import VentanaBorrarCoche.VentanaBorrarCoche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VentanaTitulo extends JFrame {
    private JPanel panelPrincipal;
    private JButton btnabrirGuardarCliente;
    private JButton registrarCocheButton;
    private JButton verCochesButton;
    private JButton borrarCocheButton; // Declaración del botón

    public VentanaTitulo() {
        setTitle("Mecánicos Salazar");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // --- INICIALIZACIÓN SEGURA ---
        // Si el botón no se creó en el .form, lo creamos aquí
        if (borrarCocheButton == null) {
            borrarCocheButton = new JButton("Borrar Coche");
            // En lugar de añadirlo al panel (que da error de Layout),
            // asegúrate de que el botón exista y simplemente le ponemos la acción.
        }
        // -----------------------------

        // Acción Borrar Coche
        borrarCocheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBorrarCoche ventanaBorrado = new VentanaBorrarCoche();
                ventanaBorrado.setVisible(true);
                dispose();
            }
        });

        // ... (resto de tus otros botones)

        // Acción Registrar Coche
        registrarCocheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistrarCoche registrarCoche = new VentanaRegistrarCoche();
                registrarCoche.setVisible(true);
                dispose();
            }
        });

        // Acción Ver Coches
        verCochesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaVerCoche.VentanaVerCoche ventanaLista = new VentanaVerCoche.VentanaVerCoche();
                ventanaLista.setVisible(true);
                dispose();
            }
        });

        // Acción Borrar Coche (Lógica segura)
        borrarCocheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBorrarCoche ventanaBorrado = new VentanaBorrarCoche();
                ventanaBorrado.setVisible(true);
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