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

        JLabel titulo = new JLabel("Controles de partida");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        // BOTONES
        botonIniciar = crearBoton(
                "Iniciar",
                new Color(20, 190, 75),
                new IconoBoton("play")
        );

        botonPausar = crearBoton(
                "Pausar",
                new Color(245, 175, 0),
                new IconoBoton("pause")
        );

        botonReanudar = crearBoton(
                "Reanudar",
                new Color(25, 105, 245),
                new IconoBoton("play")
        );

        botonReiniciar = crearBoton(
                "Reiniciar",
                new Color(220, 35, 90),
                new IconoBoton("restart")
        );

        add(titulo);
        add(botonIniciar);
        add(botonPausar);
        add(botonReanudar);
        add(botonReiniciar);
    }

    // CREAR BOTONES REDONDEADOS
    private JButton crearBoton(
            String texto,
            Color color,
            Icon icono) {

        JButton boton = new JButton(texto, icono);

        boton.setFont(new Font("Arial", Font.BOLD, 19));
        boton.setForeground(Color.WHITE);

        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);

        boton.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setIconTextGap(12);

        // PERSONALIZAR APARIENCIA DEL BOTÓN
        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {

            @Override
            public void paint(Graphics g, JComponent c) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                JButton b = (JButton) c;

                // FONDO DEL BOTÓN
                g2.setColor(color);

                g2.fillRoundRect(
                        0,
                        0,
                        b.getWidth(),
                        b.getHeight(),
                        22,
                        22
                );

                // BORDE BRILLANTE
                g2.setColor(color.brighter());
                g2.setStroke(new BasicStroke(2));

                g2.drawRoundRect(
                        1,
                        1,
                        b.getWidth() - 3,
                        b.getHeight() - 3,
                        22,
                        22
                );

                g2.dispose();

                // DIBUJAR TEXTO E ICONO
                super.paint(g, c);
            }
        });

        return boton;
    }

    // ICONOS DIBUJADOS EN JAVA
    private static class IconoBoton implements Icon {

        private final String tipo;

        public IconoBoton(String tipo) {
            this.tipo = tipo;
        }

        @Override
        public int getIconWidth() {
            return 24;
        }

        @Override
        public int getIconHeight() {
            return 24;
        }

        @Override
        public void paintIcon(
                Component componente,
                Graphics g,
                int x,
                int y) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(Color.WHITE);

            g2.setStroke(
                    new BasicStroke(
                            2.5f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            // ICONO PLAY
            if (tipo.equals("play")) {

                int[] puntosX = {
                    x + 5,
                    x + 5,
                    x + 20
                };

                int[] puntosY = {
                    y + 3,
                    y + 21,
                    y + 12
                };

                g2.fillPolygon(
                        puntosX,
                        puntosY,
                        3
                );

            // ICONO PAUSA
            } else if (tipo.equals("pause")) {

                g2.fillRoundRect(
                        x + 5,
                        y + 3,
                        5,
                        18,
                        2,
                        2
                );

                g2.fillRoundRect(
                        x + 14,
                        y + 3,
                        5,
                        18,
                        2,
                        2
                );

            // ICONO REINICIAR
            } else if (tipo.equals("restart")) {

                g2.drawArc(
                        x + 3,
                        y + 4,
                        17,
                        17,
                        45,
                        290
                );

                int[] puntosX = {
                    x + 17,
                    x + 22,
                    x + 20
                };

                int[] puntosY = {
                    y + 3,
                    y + 5,
                    y + 10
                };

                g2.fillPolygon(
                        puntosX,
                        puntosY,
                        3
                );
            }

            g2.dispose();
        }
    }

    // GETTERS
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