package icc.pap.pl1.patrones.decorador;

import icc.pap.pl1.patrones.modelo.TipoComidaSnake;

public class GestorEfectos {
    private static final long DURACION = 10_000;
    private long tiempoDoble = 0;
    private long tiempoVelocidad = 0;
    private final ReglasJuego reglasBase = new ReglasBase();
    public void activar(TipoComidaSnake tipo) {

        switch (tipo) {
            case PUNTUACION_DOBLE ->
                tiempoDoble = DURACION;

            case VELOCIDAD ->
                tiempoVelocidad = DURACION;

            case NORMAL -> {
                // La comida normal no activa efectos.
            }
            case CRECIMIENTO -> {
                //el CRECIMIENTO modifica EL tamaño de la SERPIENTE
            }
        }
    }

    public void actualizar(long milisegundos, boolean jugando) {

        if (!jugando) {
            return;
        }

        tiempoDoble = Math.max(0, tiempoDoble - milisegundos);

        tiempoVelocidad = Math.max(
            0, tiempoVelocidad - milisegundos
        );
    }

    public ReglasJuego obtenerReglas() {

        ReglasJuego reglas = reglasBase;

        if (tiempoDoble > 0) {
            reglas = new PuntuacionDoble(reglas);
        }

        if (tiempoVelocidad > 0) {
            reglas = new VelocidadModificada(reglas);
        }

        return reglas;
    }

    public boolean tienePuntuacionDoble() {
        return tiempoDoble > 0;
    }

    public boolean tieneVelocidadModificada() {
        return tiempoVelocidad > 0;
    }

    public long getTiempoDoble() {
        return tiempoDoble;
    }

    public long getTiempoVelocidad() {
        return tiempoVelocidad;
    }

    public void reiniciar() {
        tiempoDoble = 0;
        tiempoVelocidad = 0;
    }
}