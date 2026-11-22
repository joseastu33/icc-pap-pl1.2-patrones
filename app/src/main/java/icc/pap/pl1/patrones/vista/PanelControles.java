
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

public class PanelControles extends JPanel {

    private JButton botonIniciar;
    private JButton botonPausar;
    private JButton botonReanudar;
    private JButton botonReiniciar;

    public PanelControles() {

        setLayout(new GridLayout(5, 1, 10, 10));
        setBackground(new Color(30, 35, 85));

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(120, 110, 220), 2
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        JLabel titulo = new JLabel("🎮 Controles de partida");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        botonIniciar = crearBoton("▶  Iniciar",
                new Color(20, 190, 75));

        botonPausar = crearBoton("Ⅱ  Pausar",
                new Color(245, 175, 0));

        botonReanudar = crearBoton("▶  Reanudar",
                new Color(25, 105, 245));

        botonReiniciar = crearBoton("⟳  Reiniciar",
                new Color(220, 35, 90));

        add(titulo);
        add(botonIniciar);
        add(botonPausar);
        add(botonReanudar);
        add(botonReiniciar);
    }

    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Arial", Font.BOLD, 19));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);

        boton.setFocusPainted(false);
        boton.setBorder(
                BorderFactory.createLineBorder(
                        color.brighter(), 2
                )
        );

        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return boton;
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonPausar() {
        return botonPausar;
    }

    public JButton getBotonReanudar() {
        return botonReanudar;
    }

    public JButton getBotonReiniciar() {
        return botonReiniciar;
    }
}
