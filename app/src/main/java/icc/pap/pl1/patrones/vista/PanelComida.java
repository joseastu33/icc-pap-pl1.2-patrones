
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

public class PanelComida extends JPanel {

    private final JComboBox<String> comboTipoComida = new JComboBox<>(
    new String[] {
        "Normal",
        "Puntuación doble",
        "Velocidad",
        "Crecimiento"
    }
);
    private final JSpinner spinnerX;
    private final JSpinner spinnerY;

    private final JButton botonUbicar;
    private final JButton botonAleatorio;

    private final Color PANEL = new Color(30, 35, 85);
    private final Color TEXTO = Color.WHITE;

    public PanelComida() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(PANEL);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(120, 110, 220), 2
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 15, 20, 15
                        )
                )
        );

        // TÍTULO
        JLabel titulo = new JLabel("Generar comida");
        titulo.setForeground(TEXTO);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(titulo);
        add(Box.createVerticalStrut(30));

        // TIPO DE COMIDA
        JLabel etiquetaTipo = new JLabel("Tipo de comida");
        etiquetaTipo.setForeground(TEXTO);
        etiquetaTipo.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaTipo.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(etiquetaTipo);
        add(Box.createVerticalStrut(12));

        comboTipoComida = new JComboBox<>(
                new String[]{
                        "Normal",
                        "Puntuación doble",
                        "Velocidad"
                }
        );

        comboTipoComida.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        comboTipoComida.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 40)
        );

        comboTipoComida.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(comboTipoComida);
        add(Box.createVerticalStrut(30));

        // COORDENADAS MANUALES
        JLabel etiquetaCoordenadas =
                new JLabel("Coordenadas manuales");

        etiquetaCoordenadas.setForeground(TEXTO);
        etiquetaCoordenadas.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        etiquetaCoordenadas.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(etiquetaCoordenadas);
        add(Box.createVerticalStrut(12));

        // PANEL HORIZONTAL PARA X E Y
        JPanel panelCoordenadas = new JPanel(
                new GridLayout(1, 4, 8, 0)
        );

        panelCoordenadas.setBackground(PANEL);

        JLabel etiquetaX = new JLabel("X:");
        JLabel etiquetaY = new JLabel("Y:");

        etiquetaX.setForeground(TEXTO);
        etiquetaY.setForeground(TEXTO);

        etiquetaX.setFont(new Font("Arial", Font.BOLD, 15));
        etiquetaY.setFont(new Font("Arial", Font.BOLD, 15));

        // El tablero tiene 14 columnas y 11 filas
        spinnerX = new JSpinner(
                new SpinnerNumberModel(5, 0, 13, 1)
        );

        spinnerY = new JSpinner(
                new SpinnerNumberModel(5, 0, 10, 1)
        );

        Dimension tamanoSpinner = new Dimension(75, 35);

        spinnerX.setPreferredSize(tamanoSpinner);
        spinnerY.setPreferredSize(tamanoSpinner);

        spinnerX.setFont(new Font("Arial", Font.BOLD, 15));
        spinnerY.setFont(new Font("Arial", Font.BOLD, 15));

        panelCoordenadas.add(etiquetaX);
        panelCoordenadas.add(spinnerX);
        panelCoordenadas.add(etiquetaY);
        panelCoordenadas.add(spinnerY);

        panelCoordenadas.setPreferredSize(
                new Dimension(280, 45)
        );

        panelCoordenadas.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 45)
        );

        panelCoordenadas.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(panelCoordenadas);
        add(Box.createVerticalStrut(30));

        // BOTÓN UBICAR COMIDA
        botonUbicar = new JButton("Ubicar comida");

        botonUbicar.setBackground(new Color(30, 110, 240));
        botonUbicar.setForeground(Color.WHITE);

        botonUbicar.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        botonUbicar.setFocusPainted(false);

        botonUbicar.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 50)
        );

        botonUbicar.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(botonUbicar);
        add(Box.createVerticalStrut(15));

        // BOTÓN GENERAR ALEATORIA
        botonAleatorio = new JButton("Generar aleatoria");

        botonAleatorio.setBackground(
                new Color(130, 50, 220)
        );

        botonAleatorio.setForeground(Color.WHITE);

        botonAleatorio.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        botonAleatorio.setFocusPainted(false);

        botonAleatorio.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 50)
        );

        botonAleatorio.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(botonAleatorio);
        add(Box.createVerticalStrut(30));

        // INFORMACIÓN
        JLabel informacion = new JLabel(
                "<html>Ubica la comida en una casilla libre "
                + "del tablero o genera una posición aleatoria.</html>"
        );

        informacion.setForeground(
                new Color(200, 210, 230)
        );

        informacion.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        informacion.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(informacion);
    }

    // GETTERS

    public JButton getBotonUbicar() {
        return botonUbicar;
    }

    public JButton getBotonAleatorio() {
        return botonAleatorio;
    }

    public int getCoordenadaX() {
        return (int) spinnerX.getValue();
    }

    public int getCoordenadaY() {
        return (int) spinnerY.getValue();
    }

    public String getTipoComidaSeleccionado() {
        return (String) comboTipoComida.getSelectedItem();
    }
    
}
