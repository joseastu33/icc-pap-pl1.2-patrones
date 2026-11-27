
package icc.pap.pl1.patrones.vista;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import icc.pap.pl1.patrones.juego.PartidaSnake;
import icc.pap.pl1.patrones.modelo.CoordenadasTablero;
import icc.pap.pl1.patrones.modelo.SegmentoSnake;
import icc.pap.pl1.patrones.modelo.SentidoMovimiento;
import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;
import icc.pap.pl1.patrones.state.EstadoTerminado;
import icc.pap.pl1.patrones.modelo.ComidaSnake;
import icc.pap.pl1.patrones.observer.ObservadorJuego;
import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.state.EstadoTerminado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class PanelTablero extends JPanel implements ObservadorJuego {

    private int obtenerColumnas(){
        return partidaSnake.getTableroJuego().getColumnas();
    }

    private int obtenerFilas(){
        return partidaSnake.getTableroJuego().getFilas();
    }
    private static final int TAM_CELDA = 32;
    private final PartidaSnake partidaSnake;

    private int[] serpienteX = {4, 3, 2};
    private int[] serpienteY = {5, 5, 5};

    public PanelTablero(PartidaSnake partidaSnake) {
        this.partidaSnake = partidaSnake;
        setBackground(new Color(10, 35, 32));

        setPreferredSize(new Dimension(obtenerColumnas()* TAM_CELDA,obtenerFilas()* TAM_CELDA));
        configurarControles();
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
        System.out.println("Tamaño real del PanelTablero: "+ getWidth() + " x " + getHeight());

        g2.dispose();

    }

    private void dibujarCuadricula(Graphics2D g2) {

        for (int fila = 0; fila < obtenerFilas(); fila++) {

            for (int columna = 0; columna < obtenerColumnas(); columna++) {

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
        List<SegmentoSnake> cuerpoS = partidaSnake.getSerpienteSnake().getCuerpoSerpiente();
        
        for (int i = 0; i < cuerpoS.size(); i++) {
            CoordenadasTablero posicionS = cuerpoS.get(i).getCoordenadasTablero();
            int x = posicionS.getCoorX()*TAM_CELDA;
            int y = posicionS.getCoorY()*TAM_CELDA;
            System.out.println("DIBUJO | Segmento " + i + " | X=" + posicionS.getCoorX()
            + " | Y=" + posicionS.getCoorY()
            + " | Estado=" + partidaSnake.getEstadoActual().getClass().getSimpleName());
            if (i == 0) {
                if(partidaSnake.getEstadoActual() instanceof EstadoTerminado){
                    g2.setColor(Color.RED);
                } else {
                    g2.setColor(new Color(100, 255, 100));
                }
            } else {
                g2.setColor(new Color(30, 180, 90));
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

        ComidaSnake comidaSn = partidaSnake.getComidaSnake();
        if(comidaSn == null){
            return;
        }
        CoordenadasTablero pos = comidaSn.getCoordenadasTablero();
        int x = pos.getCoorX() * TAM_CELDA;
        int y = pos.getCoorY() * TAM_CELDA;

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

    @Override
    public void actualizar(EstadoJuegoVista estado) {
         System.out.println(
        "PANEL TABLERO | Recibió actualización: " + estado
    );
        repaint();
    }

    private void configurarControles() {
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke("UP"), "arriba"); //asocia el teclado con accion de ir ARRIBA
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke("DOWN"), "abajo"); //asocia el teclado con accion de ir ABAJO
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke("LEFT"), "izquierda");
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke("RIGHT"), "derecha");
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
