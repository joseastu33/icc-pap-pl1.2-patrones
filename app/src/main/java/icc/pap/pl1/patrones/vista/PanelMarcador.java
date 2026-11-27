
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;

public class PanelMarcador extends JPanel implements ObservadorJuego {

    private JLabel etiquetaPuntuacion;
    private JLabel etiquetaEstado;

    private PanelEfectos panelEfectos;

    public PanelMarcador() {

        setLayout(new BorderLayout(5, 10));
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

        // Parte superior: puntuación
        JPanel panelPuntuacion = new JPanel(
                new GridLayout(3, 1, 5, 5)
        );

        panelPuntuacion.setBackground(new Color(30, 35, 85));

        JLabel titulo = new JLabel("🏆 Puntuación");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 21));

        etiquetaPuntuacion = new JLabel("0 puntos");
        etiquetaPuntuacion.setForeground(Color.WHITE);
        etiquetaPuntuacion.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        etiquetaEstado = new JLabel("Estado: PREPARADO");
        etiquetaEstado.setForeground(new Color(170, 255, 120));
        etiquetaEstado.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        panelPuntuacion.add(titulo);
        panelPuntuacion.add(etiquetaPuntuacion);
        panelPuntuacion.add(etiquetaEstado);

        // Parte inferior: efectos
        panelEfectos = new PanelEfectos();

        add(panelPuntuacion, BorderLayout.NORTH);
        add(panelEfectos, BorderLayout.CENTER);
    }

    @Override
    public void actualizar(EstadoJuegoVista estado) {

        Runnable actualizacion = () -> {

            etiquetaPuntuacion.setText(
                    estado.getPuntuacion() + " puntos"
            );

            etiquetaEstado.setText(
                    "Estado: " + estado.getEstado()
            );

            // Actualizar también los efectos
            panelEfectos.actualizar(estado);
        };

        if (SwingUtilities.isEventDispatchThread()) {
            actualizacion.run();
        } else {
            SwingUtilities.invokeLater(actualizacion);
        }
    }
}
