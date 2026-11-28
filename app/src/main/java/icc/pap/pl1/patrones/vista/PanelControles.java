
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelControles extends JPanel {

    private JButton botonIniciar;
    private JButton botonPausar;
    private JButton botonReanudar;
    private JButton botonReiniciar;

    private final Color FONDO = new Color(30, 35, 85);
    private final Color BORDE = new Color(135, 100, 230);

    public PanelControles() {

        setLayout(new GridLayout(5, 1, 10, 12));
        setBackground(FONDO);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDE, 2),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                )
        );

        JLabel titulo = new JLabel("Controles de partida");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        botonIniciar = crearBoton(
                "Iniciar",
                new Color(20, 190, 75),
                new Color(10, 145, 55)
        );

        botonPausar = crearBoton(
                "Pausar",
                new Color(255, 190, 0),
                new Color(220, 145, 0)
        );

        botonReanudar = crearBoton(
                "Reanudar",
                new Color(40, 125, 255),
                new Color(20, 80, 220)
        );

        botonReiniciar = crearBoton(
                "Reiniciar",
                new Color(235, 45, 100),
                new Color(185, 20, 70)
        );

        add(titulo);
        add(botonIniciar);
        add(botonPausar);
        add(botonReanudar);
        add(botonReiniciar);
    }

    private JButton crearBoton(
            String texto,
            Color colorSuperior,
            Color colorInferior) {

        JButton boton = new JButton(texto) {

            private boolean mouseEncima = false;

            {
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setOpaque(false);

                setForeground(Color.WHITE);
                setFont(new Font("Arial", Font.BOLD, 19));
                setCursor(new Cursor(Cursor.HAND_CURSOR));

                addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mouseEncima = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        mouseEncima = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int ancho = getWidth();
                int alto = getHeight();

                Color superior = colorSuperior;
                Color inferior = colorInferior;

                if (mouseEncima) {
                    superior = superior.brighter();
                    inferior = inferior.brighter();
                }

                // Degradado del botón
                GradientPaint degradado = new GradientPaint(
                        0, 0,
                        superior,
                        0, alto,
                        inferior
                );

                g2.setPaint(degradado);

                g2.fillRoundRect(
                        0,
                        0,
                        ancho - 1,
                        alto - 1,
                        22,
                        22
                );

                // Borde iluminado
                g2.setColor(superior.brighter());

                g2.setStroke(new BasicStroke(2));

                g2.drawRoundRect(
                        1,
                        1,
                        ancho - 3,
                        alto - 3,
                        22,
                        22
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        boton.setPreferredSize(new Dimension(220, 55));

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
