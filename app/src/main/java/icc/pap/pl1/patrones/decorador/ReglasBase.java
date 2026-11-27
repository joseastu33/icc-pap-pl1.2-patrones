package icc.pap.pl1.patrones.decorador;

public class ReglasBase implements ReglasJuego {
// implementamos los metodos de la clase reglas juego
// los dos metodos de esa clase son calcular punto y obtener intervalo de moviemiento
// declaramos dos constantes que no pueden cambiar su valor puntos vase e intervalo base
    private static final int PUNTOS_BASE = 10;
    private static final int INTERVALO_BASE = 180;
// los override debuelven el valor de los puntos o el itnervalo
    @Override
    public int calcularPuntos() {
        return PUNTOS_BASE;
    }

    @Override
    public int obtenerIntervaloMovimiento() {
        return INTERVALO_BASE;
    }
}

//en conclusion esta clase representa el comportamiento normal de nuestra serpiente