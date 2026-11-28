
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;

public class PanelEfectos extends JPanel implements ObservadorJuego {

    private JLabel etiquetaDoble;
    private JLabel etiquetaVelocidad;

    private final Color FONDO = new Color(30, 35, 85);

    public PanelEfectos() {

        setLayout(new GridLayout(3, 1, 5, 10));
        setBackground(FONDO);

        JLabel titulo = new JLabel("Efectos activos");

        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 19));

        etiquetaDoble = new EtiquetaEfecto(
                "Puntuación ×2  |  Inactivo",
                new Color(190, 110, 240)
        );

        etiquetaVelocidad = new EtiquetaEfecto(
                "Velocidad modificada  |  Inactivo",
                new Color(70, 170, 255)
        );

        add(titulo);
        add(etiquetaDoble);
        add(etiquetaVelocidad);
    }

    // ETIQUETA CON FONDO Y BORDES REDONDEADOS
    private class EtiquetaEfecto extends JLabel {

        private final Color colorBorde;

        public EtiquetaEfecto(String texto, Color colorBorde) {

            super(texto);

            this.colorBorde = colorBorde;

            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 13));

            setOpaque(false);

            setBorder(
                    BorderFactory.createEmptyBorder(
                            10, 10, 10, 10
                    )
            );
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // FONDO DE LA TARJETA
            g2.setColor(new Color(55, 40, 100));

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    18,
                    18
            );

            // BORDE
            g2.setColor(colorBorde);

            g2.setStroke(new BasicStroke(2));

            g2.drawRoundRect(
                    1,
                    1,
                    getWidth() - 3,
                    getHeight() - 3,
                    18,
                    18
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    @Override
    public void actualizar(EstadoJuegoVista estado) {

        Runnable actualizacion = () -> {

            // PUNTUACIÓN DOBLE
            if (estado.isPuntuacionDoble()) {

                etiquetaDoble.setText(
                        "Puntuación ×2  |  "
                        + formatearTiempo(estado.getTiempoDoble())
                );

            } else {

                etiquetaDoble.setText(
                        "Puntuación ×2  |  Inactivo"
                );
            }

            // VELOCIDAD
            if (estado.isVelocidadModificada()) {

                etiquetaVelocidad.setText(
                        "Velocidad modificada  |  "
                        + formatearTiempo(estado.getTiempoVelocidad())
                );

            } else {

                etiquetaVelocidad.setText(
                        "Velocidad modificada  |  Inactivo"
                );
            }

            repaint();
        };

        if (SwingUtilities.isEventDispatchThread()) {
            actualizacion.run();
        } else {
            SwingUtilities.invokeLater(actualizacion);
        }
    }

    // FORMATO DEL TEMPORIZADOR
    private String formatearTiempo(long milisegundos) {

        long segundos = milisegundos / 1000;

        return String.format(
                "%02d:%02d",
                segundos / 60,
                segundos % 60
        );
    }
}
