
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import java.awt.*;

import icc.pap.pl1.patrones.modelo.TipoComidaSnake;
import icc.pap.pl1.patrones.juego.MotorJuego;
import icc.pap.pl1.patrones.juego.PartidaSnake;
import icc.pap.pl1.patrones.observer.ObservadorJuego;
import icc.pap.pl1.patrones.observer.EstadoJuegoVista;

public class VentanaPrincipal extends JFrame implements ObservadorJuego {

    private final PartidaSnake partidaSnake;
    private final MotorJuego motorJuego;

    private final PanelControles panelControles;
    private final PanelMarcador panelMarcador;
    private final PanelComida panelComida;

    private JLabel etiquetaEstado;

    private final Color FONDO = new Color(15, 20, 55);
    private final Color PANEL = new Color(30, 35, 85);

    public VentanaPrincipal(
            PartidaSnake partidaSnake,
            MotorJuego motorJuego) {

        this.partidaSnake = partidaSnake;
        this.motorJuego = motorJuego;

        this.panelControles = new PanelControles();
        this.panelMarcador = new PanelMarcador();
        this.panelComida = new PanelComida();

        configurarControles();
        configurarPanelComida();

        setTitle("SnakeLab - Minijuego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 950);
        setMinimumSize(new Dimension(1100, 750));
        setLocationRelativeTo(null);

        // PANEL PRINCIPAL
        JPanel principal = new JPanel(new BorderLayout(15, 15));
        principal.setBackground(FONDO);

        principal.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // ENCABEZADO
        principal.add(crearEncabezado(), BorderLayout.NORTH);

        // Registrar VentanaPrincipal como observador
        partidaSnake.agregarObservador(this);

        // CONTENEDOR DE LAS TRES COLUMNAS
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setBackground(FONDO);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.gridy = 0;
        gbc.weighty = 1;

        // COLUMNA IZQUIERDA
        gbc.gridx = 0;
        gbc.weightx = 0.20;

        contenido.add(crearPanelIzquierdo(), gbc);

        // COLUMNA CENTRAL: TABLERO
        gbc.gridx = 1;
        gbc.weightx = 0.60;

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(PANEL);

        panelCentral.setBorder(
                BorderFactory.createLineBorder(
                        new Color(135, 100, 230), 3
                )
        );

        PanelTablero tablero = new PanelTablero(partidaSnake);

        // Registrar tablero como observador
        partidaSnake.agregarObservador(tablero);

        JScrollPane scrollTablero = new JScrollPane(tablero);
        scrollTablero.setBorder(null);

        panelCentral.add(
                scrollTablero,
                BorderLayout.CENTER
        );

        contenido.add(panelCentral, gbc);

        // COLUMNA DERECHA: GENERAR COMIDA
        gbc.gridx = 2;
        gbc.weightx = 0.20;

        contenido.add(crearPanelDerecho(), gbc);

        // AGREGAR CONTENIDO A LA VENTANA
        principal.add(contenido, BorderLayout.CENTER);

        add(principal);
    }

    // ENCABEZADO
    private JPanel crearEncabezado() {

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(FONDO);

        encabezado.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 20, 10
                )
        );

        JLabel titulo = new JLabel("SnakeLab  |  Minijuego");

        titulo.setForeground(Color.WHITE);
        titulo.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        // Etiqueta dinámica del estado
        etiquetaEstado = new JLabel("Estado: PREPARADO");

        etiquetaEstado.setForeground(
                new Color(170, 255, 120)
        );

        etiquetaEstado.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        encabezado.add(titulo, BorderLayout.WEST);
        encabezado.add(etiquetaEstado, BorderLayout.EAST);

        return encabezado;
    }

    // PANEL IZQUIERDO
    private JPanel crearPanelIzquierdo() {

        JPanel panel = new JPanel(
                new GridLayout(2, 1, 10, 10)
        );

        panel.setBackground(FONDO);

        panel.add(panelControles);
        panel.add(panelMarcador);

        // Registrar marcador como observador
        partidaSnake.agregarObservador(panelMarcador);

        return panel;
    }

    // PANEL DERECHO
    private JPanel crearPanelDerecho() {

        return panelComida;
    }

    // CONFIGURACIÓN DE LOS CONTROLES
    private void configurarControles() {

        // INICIAR
        panelControles.getBotonIniciar().addActionListener(e -> {

            partidaSnake.iniciarJuego();
            motorJuego.iniciarMotorJuego();

        });

        // PAUSAR
        panelControles.getBotonPausar().addActionListener(e -> {

            partidaSnake.pausarJuego();
            motorJuego.detenerMotorJuego();

        });

        // REANUDAR
        panelControles.getBotonReanudar().addActionListener(e -> {

            partidaSnake.reanudarJuego();
            motorJuego.iniciarMotorJuego();

        });

        // REINICIAR
        panelControles.getBotonReiniciar().addActionListener(e -> {

            motorJuego.detenerMotorJuego();

            partidaSnake.reiniciarPartida();

            repaint();

        });
    }

    // CONVERTIR EL TIPO SELECCIONADO AL ENUM
    private TipoComidaSnake convertirTipo(String tipo) {

        switch (tipo) {

            case "Puntuación doble":
                return TipoComidaSnake.PUNTUACION_DOBLE;

            case "Velocidad":
                return TipoComidaSnake.VELOCIDAD;

            case "Crecimiento":
                return TipoComidaSnake.CRECIMIENTO;

            default:
                return TipoComidaSnake.NORMAL;
        }
    }

    // CONFIGURACIÓN DEL PANEL DE COMIDA
    private void configurarPanelComida() {

        // UBICAR COMIDA MANUALMENTE
        panelComida.getBotonUbicar().addActionListener(e -> {

            int x = panelComida.getCoordenadaX();
            int y = panelComida.getCoordenadaY();

            TipoComidaSnake tipo = convertirTipo(
                    panelComida.getTipoComidaSeleccionado()
            );

            boolean correcto = partidaSnake.ubicarComida(x, y, tipo);

            if (!correcto) {

                JOptionPane.showMessageDialog(
                        this,
                        "La posición está ocupada o fuera del tablero."
                );
            }
        });

        // GENERAR COMIDA ALEATORIA
        panelComida.getBotonAleatorio().addActionListener(e -> {

            boolean generado = partidaSnake.generarComidaAleatoria();

            if (!generado) {

                JOptionPane.showMessageDialog(
                        this,
                        "No hay espacio disponible para generar comida."
                );
            }
        });
    }

    // OBSERVER: ACTUALIZAR EL ESTADO DEL ENCABEZADO
    @Override
    public void actualizar(EstadoJuegoVista estado) {

        SwingUtilities.invokeLater(() -> {

            String estadoActual = estado.getEstado();

            if (estadoActual.equals("EstadoPreparado")) {

                etiquetaEstado.setText("Estado: PREPARADO");
                etiquetaEstado.setForeground(
                        new Color(170, 255, 120)
                );

            } else if (estadoActual.equals("EstadoJugando")) {

                etiquetaEstado.setText("Estado: JUGANDO");
                etiquetaEstado.setForeground(
                        new Color(170, 255, 120)
                );

            } else if (estadoActual.equals("EstadoPausado")) {

                etiquetaEstado.setText("Estado: PAUSADO");
                etiquetaEstado.setForeground(Color.YELLOW);

            } else if (estadoActual.equals("EstadoTerminado")) {

                etiquetaEstado.setText("Estado: TERMINADO");
                etiquetaEstado.setForeground(Color.RED);
            }
        });
    }
}
