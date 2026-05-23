package VentanaRegistrarCoche;

import javax.swing.*;
import java.awt.*;

public class panelCoche extends JPanel {

    private JLabel lblBase;
    private JLabel lblFaro;
    private JLabel lblLlanta;
    private JLabel lblMotor;
    private JLayeredPane layeredPane;

    public panelCoche() {
        setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 400)); // Ajusta al tamaño de tus imágenes

        ImageIcon iconBase = new ImageIcon(getClass().getResource("/imagenes/CocheDefault.png"));
        lblBase = new JLabel(iconBase);
        lblBase.setBounds(0, 0, 300, 400);

        // 2. Capa Faro Rojo (Oculta por defecto)
        ImageIcon iconFaro = new ImageIcon(getClass().getResource("/imagenes/Cochefaros.png"));
        lblFaro = new JLabel(iconFaro);
        lblFaro.setBounds(0, 0, 300, 400);
        lblFaro.setVisible(false);

        ImageIcon iconMotor = new ImageIcon(getClass().getResource("/imagenes/CocheMotor.png"));
        lblMotor = new JLabel(iconMotor);
        lblMotor.setBounds(0, 0, 300, 400);
        lblMotor.setVisible(false);

        ImageIcon iconRueda = new ImageIcon(getClass().getResource("/imagenes/CocheRueda.png"));
        lblLlanta = new JLabel(iconRueda);
        lblLlanta.setBounds(0, 0, 300, 400);
        lblLlanta.setVisible(false);

        //Capas
        layeredPane.add(lblBase, Integer.valueOf(1));
        layeredPane.add(lblFaro, Integer.valueOf(2));
        layeredPane.add(lblLlanta, Integer.valueOf(3));
        layeredPane.add(lblMotor, Integer.valueOf(4));

        add(layeredPane, BorderLayout.CENTER);


    }
        public void actualizar(String textoArreglo){
        String texto = textoArreglo.toLowerCase();

            lblFaro.setVisible(texto.contains("faro"));

            lblLlanta.setVisible(texto.contains("rueda"));

            lblMotor.setVisible(texto.contains("motor"));

            // Repintar para aplicar cambios
            repaint();
        }




    }

