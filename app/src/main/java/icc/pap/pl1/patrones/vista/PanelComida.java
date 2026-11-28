
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelComida extends JPanel {

    private final JComboBox<String> comboTipoComida;
    private final JSpinner spinnerX;
    private final JSpinner spinnerY;

    private final JButton botonUbicar;
    private final JButton botonAleatorio;

    private final Color PANEL = new Color(30, 35, 85);
    private final Color TEXTO = Color.WHITE;
    private final Color MORADO = new Color(135, 100, 230);

    public PanelComida() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(PANEL);

        setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(MORADO, 2),
            new EmptyBorder(20, 15, 20, 15)
        ));

        // Título
        JLabel titulo = new JLabel("GENERAR COMIDA");
        titulo.setForeground(TEXTO);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulo);
        add(Box.createVerticalStrut(25));

        // Selector del tipo de comida
        JLabel etiquetaTipo = new JLabel("Tipo de comida:");
        etiquetaTipo.setForeground(TEXTO);
        etiquetaTipo.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(etiquetaTipo);
        add(Box.createVerticalStrut(8));

        comboTipoComida = new JComboBox<>(new String[] {
            "Normal",
            "Puntuación doble",
            "Velocidad",
            "Crecimiento"
        });

        comboTipoComida.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 35)
        );

        add(comboTipoComida);
        add(Box.createVerticalStrut(25));

        // Coordenadas
        JLabel etiquetaCoordenadas = new JLabel("Coordenadas:");
        etiquetaCoordenadas.setForeground(TEXTO);
        etiquetaCoordenadas.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(etiquetaCoordenadas);
        add(Box.createVerticalStrut(10));

        // Valores X y Y
        spinnerX = new JSpinner(
            new SpinnerNumberModel(5, 0, 13, 1)
        );

        spinnerY = new JSpinner(
            new SpinnerNumberModel(5, 0, 10, 1)
        );

        JPanel panelCoordenadas = new JPanel(new GridLayout(1, 4, 8, 0));
        panelCoordenadas.setBackground(PANEL);

        JLabel etiquetaX = new JLabel("X:");
        etiquetaX.setForeground(TEXTO);

        JLabel etiquetaY = new JLabel("Y:");
        etiquetaY.setForeground(TEXTO);

        panelCoordenadas.add(etiquetaX);
        panelCoordenadas.add(spinnerX);
        panelCoordenadas.add(etiquetaY);
        panelCoordenadas.add(spinnerY);

        panelCoordenadas.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 40)
        );

        add(panelCoordenadas);
        add(Box.createVerticalStrut(25));

        // Botón para ubicar comida manualmente
        botonUbicar = new JButton("Ubicar comida");
        botonUbicar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonUbicar.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 40)
        );

        add(botonUbicar);
        add(Box.createVerticalStrut(15));

        // Botón para generar comida aleatoria
        botonAleatorio = new JButton("Generar aleatoria");
        botonAleatorio.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAleatorio.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 40)
        );

        add(botonAleatorio);
        add(Box.createVerticalStrut(25));

        // Información
        JLabel informacion = new JLabel(
            "<html><center>"
            + "Selecciona el tipo de comida<br>"
            + "y las coordenadas para ubicarla."
            + "</center></html>"
        );

        informacion.setForeground(TEXTO);
        informacion.setFont(new Font("Arial", Font.PLAIN, 12));
        informacion.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(informacion);
    }

    // Getters de los botones

    public JButton getBotonUbicar() {
        return botonUbicar;
    }

    public JButton getBotonAleatorio() {
        return botonAleatorio;
    }

    // Getters de las coordenadas

    public int getCoordenadaX() {
        return (int) spinnerX.getValue();
    }

    public int getCoordenadaY() {
        return (int) spinnerY.getValue();
    }

    // Getter del tipo seleccionado

    public String getTipoComidaSeleccionado() {
        return (String) comboTipoComida.getSelectedItem();
    }
}
