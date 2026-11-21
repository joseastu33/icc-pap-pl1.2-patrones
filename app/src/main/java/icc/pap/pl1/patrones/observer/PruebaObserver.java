package icc.pap.pl1.patrones.observer;

import icc.pap.pl1.patrones.juego.PartidaSnake;

public class PruebaObserver {

    public static void main(String[] args) {
// clase solo para verificar el funcionamiento 
        PartidaSnake partida = new PartidaSnake();

        // Creamos un observador de prueba
        ObservadorJuego observador = estado -> {

            System.out.println("¡Cambio detectado!");

            System.out.println(
                "Puntuación: " + estado.getPuntuacion()
            );

            System.out.println(
                "Estado: " + estado.getEstado()
            );
        };

        // Registramos el observador
        partida.agregarObservador(observador);

        // Cambiamos la puntuación
        partida.setPuntuacion(20);

        // Cambiamos el estado
        partida.setEstadoPartida("JUGANDO");
    }
}