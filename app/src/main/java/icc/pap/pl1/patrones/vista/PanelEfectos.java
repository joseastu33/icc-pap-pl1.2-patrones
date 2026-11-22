
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;

public class PanelEfectos extends JPanel implements ObservadorJuego {

    private JLabel etiquetaDoble;
    private JLabel etiquetaVelocidad;

    public PanelEfectos() {

        setLayout(new GridLayout(3, 1, 5, 8));
        setBackground(new Color(30, 35, 85));

        JLabel titulo = new JLabel("✨ Efectos activos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 19));

        etiquetaDoble = new JLabel("⭐ Puntuación ×2  |  Inactivo");
        etiquetaVelocidad = new JLabel("⚡ Velocidad modificada  |  Inactivo");

        configurarEtiqueta(etiquetaDoble, new Color(190, 110, 240));
        configurarEtiqueta(etiquetaVelocidad, new Color(70, 170, 255));

        add(titulo);
        add(etiquetaDoble);
        add(etiquetaVelocidad);
    }

    private void configurarEtiqueta(JLabel etiqueta, Color color) {

        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 13));

        etiqueta.setOpaque(true);
        etiqueta.setBackground(new Color(55, 40, 100));

        etiqueta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(color, 2),
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)
                )
        );
    }

    @Override
    public void actualizar(EstadoJuegoVista estado) {

        Runnable actualizacion = () -> {

            if (estado.isPuntuacionDoble()) {

                etiquetaDoble.setText(
                        "⭐ Puntuación ×2  |  "
                        + formatearTiempo(estado.getTiempoDoble())
                );

            } else {

                etiquetaDoble.setText(
                        "⭐ Puntuación ×2  |  Inactivo"
                );
            }

            if (estado.isVelocidadModificada()) {

                etiquetaVelocidad.setText(
                        "⚡ Velocidad modificada  |  "
                        + formatearTiempo(estado.getTiempoVelocidad())
                );

            } else {

                etiquetaVelocidad.setText(
                        "⚡ Velocidad modificada  |  Inactivo"
                );
            }
        };

        if (SwingUtilities.isEventDispatchThread()) {
            actualizacion.run();
        } else {
            SwingUtilities.invokeLater(actualizacion);
        }
    }

    private String formatearTiempo(long milisegundos) {

        long segundos = milisegundos / 1000;

        return String.format(
                "%02d:%02d",
                segundos / 60,
                segundos % 60
        );
    }
}
