
package icc.pap.pl1.patrones.vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PanelTablero extends JPanel {

    private static final int COLUMNAS = 14;
    private static final int FILAS = 11;
    private static final int TAM_CELDA = 40;

    private int[] serpienteX = {4, 3, 2};
    private int[] serpienteY = {5, 5, 5};

    private int comidaX = 9;
    private int comidaY = 3;

    public PanelTablero() {

        setBackground(new Color(10, 35, 32));

        setPreferredSize(
                new Dimension(
                        COLUMNAS * TAM_CELDA,
                        FILAS * TAM_CELDA
                )
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        dibujarCuadricula(g2);
        dibujarComida(g2);
        dibujarSerpiente(g2);

        g2.dispose();
    }

    private void dibujarCuadricula(Graphics2D g2) {

        for (int fila = 0; fila < FILAS; fila++) {

            for (int columna = 0; columna < COLUMNAS; columna++) {

                int x = columna * TAM_CELDA;
                int y = fila * TAM_CELDA;

                g2.setColor(new Color(15, 75, 58));

                g2.fillRoundRect(
                        x + 2,
                        y + 2,
                        TAM_CELDA - 4,
                        TAM_CELDA - 4,
                        8,
                        8
                );
            }
        }
    }

    private void dibujarSerpiente(Graphics2D g2) {

        for (int i = 0; i < serpienteX.length; i++) {

            int x = serpienteX[i] * TAM_CELDA;
            int y = serpienteY[i] * TAM_CELDA;

            if (i == 0) {
                g2.setColor(new Color(190, 255, 90));
            } else {
                g2.setColor(new Color(65, 220, 75));
            }

            g2.fillRoundRect(
                    x + 3,
                    y + 3,
                    TAM_CELDA - 6,
                    TAM_CELDA - 6,
                    10,
                    10
            );
        }
    }

    private void dibujarComida(Graphics2D g2) {

        int x = comidaX * TAM_CELDA;
        int y = comidaY * TAM_CELDA;

        g2.setColor(new Color(255, 65, 65));

        g2.fillRoundRect(
                x + 3,
                y + 3,
                TAM_CELDA - 6,
                TAM_CELDA - 6,
                10,
                10
        );
    }
}
