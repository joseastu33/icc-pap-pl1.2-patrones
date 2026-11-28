package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelComida extends JPanel {

    private final JSpinner spinnerX;
    private final JSpinner spinnerY;

    private final JButton botonUbicar;
    private final JButton botonAleatorio;

    private String tipoComidaSeleccionado = "Normal";

    private final Color PANEL = new Color(30, 35, 85);
    private final Color TEXTO = Color.WHITE;
    private final Color MORADO = new Color(135, 100, 230);

    private final Color VERDE = new Color(20, 190, 75);
    private final Color MORADO_DOBLE = new Color(170, 70, 230);
    private final Color AZUL = new Color(25, 105, 245);
    private final Color AMARILLO = new Color(245, 175, 0);

    private BotonRedondeado botonNormal;
    private BotonRedondeado botonDoble;
    private BotonRedondeado botonVelocidad;
    private BotonRedondeado botonCrecimiento;

    public PanelComida() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(PANEL);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MORADO, 2),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // TÍTULO
        JLabel titulo = new JLabel("GENERAR COMIDA");
        titulo.setForeground(TEXTO);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulo);
        add(Box.createVerticalStrut(20));

        // TIPO DE COMIDA
        JLabel etiquetaTipo = new JLabel("Tipo de comida:");
        etiquetaTipo.setForeground(TEXTO);
        etiquetaTipo.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaTipo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(etiquetaTipo);
        add(Box.createVerticalStrut(10));

        // PANEL DE BOTONES DE COMIDA
        JPanel panelTipos = new JPanel(new GridLayout(4, 1, 0, 10));
        panelTipos.setBackground(PANEL);

        botonNormal = new BotonRedondeado(
                "●  Normal",
                VERDE
        );

        botonDoble = new BotonRedondeado(
                "●  Puntuación doble",
                MORADO_DOBLE
        );

        botonVelocidad = new BotonRedondeado(
                "●  Velocidad",
                AZUL
        );

        botonCrecimiento = new BotonRedondeado(
                "●  Crecimiento",
                AMARILLO
        );

        panelTipos.add(botonNormal);
        panelTipos.add(botonDoble);
        panelTipos.add(botonVelocidad);
        panelTipos.add(botonCrecimiento);

        panelTipos.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 210)
        );

        add(panelTipos);
        add(Box.createVerticalStrut(20));

        // COORDENADAS
        JLabel etiquetaCoordenadas = new JLabel("Coordenadas:");
        etiquetaCoordenadas.setForeground(TEXTO);
        etiquetaCoordenadas.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaCoordenadas.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(etiquetaCoordenadas);
        add(Box.createVerticalStrut(10));

        // SPINNER X
        spinnerX = new JSpinner(
                new SpinnerNumberModel(5, 0, 13, 1)
        );

        // SPINNER Y
        spinnerY = new JSpinner(
                new SpinnerNumberModel(5, 0, 10, 1)
        );

        JPanel panelCoordenadas = new JPanel(
                new GridLayout(1, 4, 8, 0)
        );

        panelCoordenadas.setBackground(PANEL);

        JLabel etiquetaX = new JLabel("X:");
        etiquetaX.setForeground(TEXTO);
        etiquetaX.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel etiquetaY = new JLabel("Y:");
        etiquetaY.setForeground(TEXTO);
        etiquetaY.setFont(new Font("Arial", Font.BOLD, 14));

        panelCoordenadas.add(etiquetaX);
        panelCoordenadas.add(spinnerX);
        panelCoordenadas.add(etiquetaY);
        panelCoordenadas.add(spinnerY);

        panelCoordenadas.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 40)
        );

        add(panelCoordenadas);
        add(Box.createVerticalStrut(20));

        // BOTÓN UBICAR COMIDA
        botonUbicar = crearBotonAccion(
                " Ubicar comida",
                new Color(30, 120, 255)
        );

        add(botonUbicar);
        add(Box.createVerticalStrut(12));

        // BOTÓN GENERAR ALEATORIA
        botonAleatorio = crearBotonAccion(
                " Generar aleatoria",
                new Color(145, 45, 230)
        );

        add(botonAleatorio);
        add(Box.createVerticalStrut(20));

        // INFORMACIÓN
        JLabel informacion = new JLabel(
                "<html><center>"
                        + "Ubica la comida en una casilla libre<br>"
                        + "del tablero o genera una posición aleatoria."
                        + "</center></html>"
        );

        informacion.setForeground(TEXTO);
        informacion.setFont(new Font("Arial", Font.PLAIN, 12));
        informacion.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(informacion);

        // CONFIGURAR SELECCIÓN
        configurarSeleccion();
        actualizarBotonesTipo();
    }

    // SELECCIÓN DE TIPO DE COMIDA

    private void configurarSeleccion() {

        botonNormal.addActionListener(e -> {
            tipoComidaSeleccionado = "Normal";
            actualizarBotonesTipo();
        });

        botonDoble.addActionListener(e -> {
            tipoComidaSeleccionado = "Puntuación doble";
            actualizarBotonesTipo();
        });

        botonVelocidad.addActionListener(e -> {
            tipoComidaSeleccionado = "Velocidad";
            actualizarBotonesTipo();
        });

        botonCrecimiento.addActionListener(e -> {
            tipoComidaSeleccionado = "Crecimiento";
            actualizarBotonesTipo();
        });
    }

    private void actualizarBotonesTipo() {

        botonNormal.setSeleccionado(
                tipoComidaSeleccionado.equals("Normal")
        );

        botonDoble.setSeleccionado(
                tipoComidaSeleccionado.equals("Puntuación doble")
        );

        botonVelocidad.setSeleccionado(
                tipoComidaSeleccionado.equals("Velocidad")
        );

        botonCrecimiento.setSeleccionado(
                tipoComidaSeleccionado.equals("Crecimiento")
        );
    }

    // CREAR BOTONES DE ACCIÓN

    private JButton crearBotonAccion(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);

        boton.setFocusPainted(false);

        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.brighter(), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        boton.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 50)
        );

        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

    // GETTERS

    public JButton getBotonUbicar() {
        return botonUbicar;
    }

    public JButton getBotonAleatorio() {
        return botonAleatorio;
    }

    public int getCoordenadaX() {
        return (int) spinnerX.getValue();
    }

    public int getCoordenadaY() {
        return (int) spinnerY.getValue();
    }

    public String getTipoComidaSeleccionado() {
        return tipoComidaSeleccionado;
    }

    // BOTÓN REDONDEADO PERSONALIZADO

    private class BotonRedondeado extends JButton {

        private final Color color;
        private boolean seleccionado;

        public BotonRedondeado(String texto, Color color) {

            super(texto);

            this.color = color;

            setFont(new Font("Arial", Font.BOLD, 16));
            setForeground(Color.WHITE);

            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);

            setCursor(new Cursor(Cursor.HAND_CURSOR));

            setPreferredSize(new Dimension(200, 45));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        }

        public void setSeleccionado(boolean seleccionado) {
            this.seleccionado = seleccionado;
            repaint();
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

            // FONDO
            if (seleccionado) {
                g2.setColor(color);
            } else {
                g2.setColor(new Color(
                        color.getRed() / 3,
                        color.getGreen() / 3,
                        color.getBlue() / 3
                ));
            }

            g2.fillRoundRect(
                    2, 2,
                    ancho - 4, alto - 4,
                    25, 25
            );

            // BORDE
            g2.setStroke(new BasicStroke(
                    seleccionado ? 3 : 2
            ));

            g2.setColor(
                    seleccionado
                            ? color.brighter()
                            : color
            );

            g2.drawRoundRect(
                    2, 2,
                    ancho - 4, alto - 4,
                    25, 25
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }
}