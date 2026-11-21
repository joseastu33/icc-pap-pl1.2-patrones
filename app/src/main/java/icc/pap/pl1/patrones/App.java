
package icc.pap.pl1.patrones;

import javax.swing.SwingUtilities;
import icc.pap.pl1.patrones.vista.VentanaPrincipal;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });

    }
}
