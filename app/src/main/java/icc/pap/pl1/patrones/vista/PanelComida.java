
package icc.pap.pl1.patrones.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelComida extends JPanel {

    private final JComboBox<String> comboTipoComida;
    private final JSpinner spinnerX;
    private final JSpinner spinnerY;

    private final JButton botonUbicar;
    private final JButton botonAleatorio;

    private final Color PANEL = new Color(30, 35, 85);
    private final Color TEXTO = Color.WHITE;
    private final Color MORADO = new Color(135, 100, 230);

    public PanelComida() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(PANEL);

        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(MORADO, 2),
                new EmptyBorder(20, 15, 20, 15)
        ));

        // TITULO
        JLabel titulo = new JLabel("GENERAR COMIDA");

        titulo.setForeground(TEXTO);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulo);
        add(Box.createVerticalStrut(30));

        // TIPO DE COMIDA
        JLabel etiquetaTipo = crearEtiqueta("Tipo de comida:");
        etiquetaTipo.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(etiquetaTipo);
        add(Box.createVerticalStrut(10));

        comboTipoComida = new JComboBox<>(new String[]{
                "Normal",
                "Puntuación doble",
                "Velocidad",
                "Crecimiento"
        });

        comboTipoComida.setFont(new Font("Arial", Font.PLAIN, 14));
        comboTipoComida.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 38)
        );

        comboTipoComida.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(comboTipoComida);
        add(Box.createVerticalStrut(30));

        // COORDENADAS
        JLabel etiquetaCoordenadas = crearEtiqueta("Coordenadas:");

        etiquetaCoordenadas.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(etiquetaCoordenadas);
        add(Box.createVerticalStrut(12));

        spinnerX = new JSpinner(
                new SpinnerNumberModel(5, 0, 13, 1)
        );

        spinnerY = new JSpinner(
                new SpinnerNumberModel(5, 0, 10, 1)
        );

        configurarSpinner(spinnerX);
        configurarSpinner(spinnerY);

        JPanel panelCoordenadas = new JPanel(
                new GridLayout(1, 4, 8, 0)
        );

        panelCoordenadas.setBackground(PANEL);

        JLabel etiquetaX = crearEtiqueta("X:");
        JLabel etiquetaY = crearEtiqueta("Y:");

        panelCoordenadas.add(etiquetaX);
        panelCoordenadas.add(spinnerX);
        panelCoordenadas.add(etiquetaY);
        panelCoordenadas.add(spinnerY);

        panelCoordenadas.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 40)
        );

        panelCoordenadas.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panelCoordenadas);
        add(Box.createVerticalStrut(25));

        // BOTON UBICAR
        botonUbicar = crearBoton(
                "Ubicar comida",
                new Color(45, 130, 255),
                new Color(20, 80, 220)
        );

        add(botonUbicar);
        add(Box.createVerticalStrut(15));

        // BOTON ALEATORIO
        botonAleatorio = crearBoton(
                "Generar aleatoria",
                new Color(155, 65, 245),
                new Color(105, 35, 200)
        );

        add(botonAleatorio);
        add(Box.createVerticalStrut(25));

        // INFORMACION
        JLabel informacion = new JLabel(
                "<html><center>"
                + "Ubica comida en una casilla libre<br>"
                + "del tablero o genera una posición aleatoria."
                + "</center></html>"
        );

        informacion.setForeground(TEXTO);
        informacion.setFont(new Font("Arial", Font.PLAIN, 12));
        informacion.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(informacion);
    }

    // CREAR ETIQUETAS
    private JLabel crearEtiqueta(String texto) {

        JLabel etiqueta = new JLabel(texto);

        etiqueta.setForeground(TEXTO);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));

        return etiqueta;
    }

    // ESTILO DE LOS SPINNERS
    private void configurarSpinner(JSpinner spinner) {

        spinner.setFont(new Font("Arial", Font.BOLD, 14));

        spinner.setPreferredSize(
                new Dimension(65, 38)
        );

        spinner.setMaximumSize(
                new Dimension(65, 38)
        );
    }

    // CREAR BOTONES CON DEGRADADO
    private JButton crearBoton(
            String texto,
            Color colorSuperior,
            Color colorInferior) {

        JButton boton = new JButton(texto) {

            private boolean mouseEncima = false;

            {
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setOpaque(false);

                setForeground(Color.WHITE);
                setFont(new Font("Arial", Font.BOLD, 15));

                setCursor(
                        new Cursor(Cursor.HAND_CURSOR)
                );

                addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mouseEncima = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        mouseEncima = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                Color superior = colorSuperior;
                Color inferior = colorInferior;

                if (mouseEncima) {
                    superior = superior.brighter();
                    inferior = inferior.brighter();
                }

                GradientPaint degradado = new GradientPaint(
                        0, 0,
                        superior,
                        0, getHeight(),
                        inferior
                );

                g2.setPaint(degradado);

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth() - 1,
                        getHeight() - 1,
                        20,
                        20
                );

                // BORDE
                g2.setColor(superior.brighter());
                g2.setStroke(new BasicStroke(2));

                g2.drawRoundRect(
                        1,
                        1,
                        getWidth() - 3,
                        getHeight() - 3,
                        20,
                        20
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 48)
        );

        boton.setPreferredSize(
                new Dimension(220, 48)
        );

        return boton;
    }

    // GETTERS DE LOS BOTONES
    public JButton getBotonUbicar() {
        return botonUbicar;
    }

    public JButton getBotonAleatorio() {
        return botonAleatorio;
    }

    // GETTERS DE COORDENADAS
    public int getCoordenadaX() {
        return (int) spinnerX.getValue();
    }

    public int getCoordenadaY() {
        return (int) spinnerY.getValue();
    }

    // GETTER DEL TIPO DE COMIDA
    public String getTipoComidaSeleccionado() {
        return (String) comboTipoComida.getSelectedItem();
    }
}
