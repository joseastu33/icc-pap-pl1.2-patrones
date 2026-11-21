package icc.pap.pl1.patrones.juego;

import java.util.ArrayList;
import java.util.List;

import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;
import icc.pap.pl1.patrones.observer.SujetoJuego;

public class PartidaSnake implements SujetoJuego {

    private int puntuacion;
    private String estadoPartida = "PREPARADO";

    private final List<ObservadorJuego> observadores =
            new ArrayList<>();

    // Registrar observadores
    @Override
    public void agregarObservador(ObservadorJuego observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    // Eliminar observadores
    @Override
    public void eliminarObservador(ObservadorJuego observador) {
        observadores.remove(observador);
    }

    // Notificar cambios
    @Override
    public void notificarObservadores(EstadoJuegoVista estado) {
        for (ObservadorJuego observador : observadores) {
            observador.actualizar(estado);
        }
    }

    // Modificar puntuación
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
        notificarObservadores(crearEstadoVista());
    }

    // Modificar estado de la partida
    public void setEstadoPartida(String estadoPartida) {
        this.estadoPartida = estadoPartida;
        notificarObservadores(crearEstadoVista());
    }

    // Crear una copia de los datos actuales
    private EstadoJuegoVista crearEstadoVista() {

        return new EstadoJuegoVista(
                puntuacion,
                estadoPartida,
                false,
                false,
                0,
                0
        );
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getEstadoPartida() {
        return estadoPartida;
    }
}