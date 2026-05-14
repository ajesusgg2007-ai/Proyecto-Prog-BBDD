package VentanaTitulo;

import VentanaGuardarCliente.VentanaGuardarCliente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTitulo extends JFrame {
    private JPanel panelPrincipal;
    private JButton btnabrirGuardarCliente;

    public VentanaTitulo() {
        setTitle("Menú Principal");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
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
    }
}