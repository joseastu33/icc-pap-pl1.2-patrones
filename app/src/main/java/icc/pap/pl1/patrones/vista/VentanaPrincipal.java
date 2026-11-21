
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final Color FONDO = new Color(15, 20, 55);
    private final Color PANEL = new Color(30, 35, 85);

    public VentanaPrincipal() {

        setTitle("SnakeLab - Minijuego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 950);
        setMinimumSize(new Dimension(1100, 750));
        setLocationRelativeTo(null);

        // Panel principal
        JPanel principal = new JPanel(new BorderLayout(15, 15));
        principal.setBackground(FONDO);
        principal.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Encabezado
        principal.add(crearEncabezado(), BorderLayout.NORTH);

        // Contenedor de las tres columnas
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setBackground(FONDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.gridy = 0;
        gbc.weighty = 1;

        // COLUMNA IZQUIERDA
        gbc.gridx = 0;
        gbc.weightx = 0.25;

        contenido.add(crearPanelIzquierdo(), gbc);

        // COLUMNA CENTRAL
        gbc.gridx = 1;
        gbc.weightx = 0.50;

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(PANEL);

        panelCentral.setBorder(
                BorderFactory.createLineBorder(
                        new Color(135, 100, 230), 3
                )
        );

        PanelTablero tablero = new PanelTablero();

        // El tablero se agrega al centro automáticamente
        panelCentral.add(tablero);

        contenido.add(panelCentral, gbc);

        // COLUMNA DERECHA
        gbc.gridx = 2;
        gbc.weightx = 0.25;

        contenido.add(crearPanelDerecho(), gbc);

        // Agregar contenido a la ventana
        principal.add(contenido, BorderLayout.CENTER);

        add(principal);
    }

    // ENCABEZADO
    private JPanel crearEncabezado() {

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(FONDO);

        encabezado.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 20, 10)
        );

        JLabel titulo = new JLabel("SnakeLab  |  Minijuego");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel estado = new JLabel("Estado: PREPARADO");
        estado.setForeground(new Color(170, 255, 120));
        estado.setFont(new Font("Arial", Font.BOLD, 20));

        encabezado.add(titulo, BorderLayout.WEST);
        encabezado.add(estado, BorderLayout.EAST);

        return encabezado;
    }

    // PANEL IZQUIERDO
    private JPanel crearPanelIzquierdo() {

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(FONDO);

        JPanel controles = crearPanelBase(
                "Controles de partida"
        );

        JPanel marcador = crearPanelBase(
                "Puntuación y efectos activos"
        );

        panel.add(controles);
        panel.add(marcador);

        return panel;
    }

    // PANEL DERECHO
    private JPanel crearPanelDerecho() {

        return crearPanelBase("Generar comida");
    }

    // MÉTODO PARA CREAR PANELES
    private JPanel crearPanelBase(String titulo) {

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(panel, BoxLayout.Y_AXIS)
        );

        panel.setBackground(PANEL);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(120, 110, 220), 2
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        panel.add(etiqueta);

        return panel;
    }
}
