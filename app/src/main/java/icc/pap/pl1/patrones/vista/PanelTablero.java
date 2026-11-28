
package icc.pap.pl1.patrones.vista;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.List;

import icc.pap.pl1.patrones.juego.PartidaSnake;
import icc.pap.pl1.patrones.modelo.CoordenadasTablero;
import icc.pap.pl1.patrones.modelo.SegmentoSnake;
import icc.pap.pl1.patrones.modelo.SentidoMovimiento;
import icc.pap.pl1.patrones.modelo.ComidaSnake;
import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;
import icc.pap.pl1.patrones.state.EstadoTerminado;

public class PanelTablero extends JPanel implements ObservadorJuego {

    private static final int TAM_CELDA = 32;

    private final PartidaSnake partidaSnake;

    private final Color COLOR_FONDO = new Color(8, 35, 30);
    private final Color COLOR_CELDA = new Color(15, 75, 58);
    private final Color COLOR_CABEZA = new Color(100, 255, 100);
    private final Color COLOR_CUERPO = new Color(30, 180, 90);
    private final Color COLOR_COMIDA = new Color(255, 65, 65);

    public PanelTablero(PartidaSnake partidaSnake) {

        this.partidaSnake = partidaSnake;

        setBackground(COLOR_FONDO);

        // El tamaño del tablero se mantiene fijo.
        setPreferredSize(new Dimension(
                obtenerColumnas() * TAM_CELDA,
                obtenerFilas() * TAM_CELDA
        ));

        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());

        configurarControles();
    }

    private int obtenerColumnas() {
        return partidaSnake.getTableroJuego().getColumnas();
    }

    private int obtenerFilas() {
        return partidaSnake.getTableroJuego().getFilas();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Calculamos el espacio sobrante para centrar la cuadrícula.
        int anchoTablero = obtenerColumnas() * TAM_CELDA;
        int altoTablero = obtenerFilas() * TAM_CELDA;

        int desplazamientoX = (getWidth() - anchoTablero) / 2;
        int desplazamientoY = (getHeight() - altoTablero) / 2;

        dibujarCuadricula(g2, desplazamientoX, desplazamientoY);
        dibujarComida(g2, desplazamientoX, desplazamientoY);
        dibujarSerpiente(g2, desplazamientoX, desplazamientoY);

        g2.dispose();
    }

    private void dibujarCuadricula(
            Graphics2D g2,
            int desplazamientoX,
            int desplazamientoY) {

        g2.setColor(COLOR_FONDO);

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        for (int fila = 0; fila < obtenerFilas(); fila++) {

            for (int columna = 0; columna < obtenerColumnas(); columna++) {

                int x = desplazamientoX + columna * TAM_CELDA;
                int y = desplazamientoY + fila * TAM_CELDA;

                g2.setColor(COLOR_CELDA);

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

    private void dibujarSerpiente(
            Graphics2D g2,
            int desplazamientoX,
            int desplazamientoY) {

        List<SegmentoSnake> cuerpoS =
                partidaSnake.getSerpienteSnake().getCuerpoSerpiente();

        for (int i = 0; i < cuerpoS.size(); i++) {

            CoordenadasTablero posicionS =
                    cuerpoS.get(i).getCoordenadasTablero();

            int x = desplazamientoX
                    + posicionS.getCoorX() * TAM_CELDA;

            int y = desplazamientoY
                    + posicionS.getCoorY() * TAM_CELDA;

            if (i == 0) {

                if (partidaSnake.getEstadoActual() instanceof EstadoTerminado) {
                    g2.setColor(Color.RED);
                } else {
                    g2.setColor(COLOR_CABEZA);
                }

            } else {
                g2.setColor(COLOR_CUERPO);
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

    private void dibujarComida(
            Graphics2D g2,
            int desplazamientoX,
            int desplazamientoY) {

        ComidaSnake comidaSn = partidaSnake.getComidaSnake();

        if (comidaSn == null) {
            return;
        }

        CoordenadasTablero pos =
                comidaSn.getCoordenadasTablero();

        int x = desplazamientoX
                + pos.getCoorX() * TAM_CELDA;

        int y = desplazamientoY
                + pos.getCoorY() * TAM_CELDA;

        g2.setColor(COLOR_COMIDA);

        g2.fillRoundRect(
                x + 3,
                y + 3,
                TAM_CELDA - 6,
                TAM_CELDA - 6,
                10,
                10
        );
    }

    @Override
    public void actualizar(EstadoJuegoVista estado) {
        repaint();
    }

    private void configurarControles() {

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("UP"),
                "arriba"
        );

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("DOWN"),
                "abajo"
        );

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("LEFT"),
                "izquierda"
        );

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("RIGHT"),
                "derecha"
        );

        getActionMap().put("arriba", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                partidaSnake.getSerpienteSnake()
                        .cambiarSentidoMovimiento(SentidoMovimiento.ARRIBA);
            }
        });

        getActionMap().put("abajo", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                partidaSnake.getSerpienteSnake()
                        .cambiarSentidoMovimiento(SentidoMovimiento.ABAJO);
            }
        });

        getActionMap().put("izquierda", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                partidaSnake.getSerpienteSnake()
                        .cambiarSentidoMovimiento(SentidoMovimiento.IZQUIERDA);
            }
        });

        getActionMap().put("derecha", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                partidaSnake.getSerpienteSnake()
                        .cambiarSentidoMovimiento(SentidoMovimiento.DERECHA);
            }
        });
    }
}
