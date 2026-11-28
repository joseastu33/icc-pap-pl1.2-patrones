
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;

public class PanelMarcador extends JPanel implements ObservadorJuego {

    private JLabel etiquetaPuntuacion;
    private JLabel etiquetaEstado;

    private PanelEfectos panelEfectos;

    private final Color FONDO = new Color(30, 35, 85);
    private final Color MORADO = new Color(110, 55, 180);
    private final Color VERDE = new Color(170, 255, 120);

    public PanelMarcador() {

        setLayout(new BorderLayout(8, 15));
        setBackground(FONDO);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(135, 100, 230), 2
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        // PANEL DE PUNTUACIÓN
        JPanel panelPuntuacion = new JPanel(
                new GridLayout(3, 1, 5, 8)
        );

        panelPuntuacion.setBackground(FONDO);

        JLabel titulo = new JLabel("Puntuación");

        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 21));

        // PUNTOS
        etiquetaPuntuacion = new JLabel("0 puntos");

        etiquetaPuntuacion.setForeground(Color.WHITE);
        etiquetaPuntuacion.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        etiquetaPuntuacion.setOpaque(true);
        etiquetaPuntuacion.setBackground(
                new Color(65, 35, 120)
        );

        etiquetaPuntuacion.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );

        // ESTADO
        etiquetaEstado = new JLabel("Estado: PREPARADO");

        etiquetaEstado.setForeground(VERDE);
        etiquetaEstado.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        panelPuntuacion.add(titulo);
        panelPuntuacion.add(etiquetaPuntuacion);
        panelPuntuacion.add(etiquetaEstado);

        // SEPARADOR VISUAL
        JPanel separador = new JPanel();

        separador.setBackground(
                new Color(75, 65, 140)
        );

        separador.setPreferredSize(
                new Dimension(1, 2)
        );

        // PANEL DE EFECTOS
        panelEfectos = new PanelEfectos();

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 10));
        panelSuperior.setBackground(FONDO);

        panelSuperior.add(panelPuntuacion, BorderLayout.NORTH);
        panelSuperior.add(separador, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelEfectos, BorderLayout.CENTER);
    }

    @Override
    public void actualizar(EstadoJuegoVista estado) {

        Runnable actualizacion = () -> {

            etiquetaPuntuacion.setText(
                    estado.getPuntuacion() + " puntos"
            );

            String estadoTexto = convertirEstado(
                    estado.getEstado()
            );

            etiquetaEstado.setText(
                    "Estado: " + estadoTexto
            );

            // COLOR DEL ESTADO
            switch (estadoTexto) {

                case "JUGANDO":
                    etiquetaEstado.setForeground(VERDE);
                    break;

                case "PAUSADO":
                    etiquetaEstado.setForeground(Color.YELLOW);
                    break;

                case "TERMINADO":
                    etiquetaEstado.setForeground(Color.RED);
                    break;

                default:
                    etiquetaEstado.setForeground(VERDE);
                    break;
            }

            // ACTUALIZAR EFECTOS
            panelEfectos.actualizar(estado);
        };

        if (SwingUtilities.isEventDispatchThread()) {
            actualizacion.run();
        } else {
            SwingUtilities.invokeLater(actualizacion);
        }
    }

    private String convertirEstado(String estado) {

        switch (estado) {

            case "EstadoPreparado":
                return "PREPARADO";

            case "EstadoJugando":
                return "JUGANDO";

            case "EstadoPausado":
                return "PAUSADO";

            case "EstadoTerminado":
                return "TERMINADO";

            default:
                return estado;
        }
    }
}
