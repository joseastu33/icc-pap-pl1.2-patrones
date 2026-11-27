package icc.pap.pl1.patrones.juego;

import javax.swing.Timer;

/**
 * 
 * MotorJuego: clase que controla CUANDO la serpiente se mueve
 * mediante un TEMPORIZADOR (swing.Timer)
 */
public class MotorJuego {
    private PartidaSnake partida;
    private Timer temporizador;
    private long ultimoTiempo;
    private int ultimoIntervalo = -1;

    public MotorJuego(PartidaSnake partidaSnake){
        this.partida = partidaSnake;
        //usa una lambda que define que hacer cada vez que el temporizador se ACTIVA
        this.temporizador = new Timer(180, e -> {
            long tiempoActual = System.currentTimeMillis();
            long milisegundos = tiempoActual-ultimoTiempo;
            ultimoTiempo = tiempoActual;

            partida.actualizarEfectos(milisegundos);
            if(partida.estaJugando()){
                partidaSnake.moverSerpiente();
            //a partir de AQUI el movimiento de la serpiente
            //es ejecutado
                int intervalo = partida.obtenerIntervaloMovimiento();
                if(intervalo != ultimoIntervalo){
                    System.out.println("Intervalo aplicado en MOTORJUEGO: " + intervalo + "ms");
                    ultimoIntervalo = intervalo;
                }
                temporizador.setDelay(intervalo);
            }

        });
    }
    //a partir de que se inicie, cada 180 ms se ejecutará la lambda que retrasa el MOVIMIENTO de la serpiente
    public void iniciarMotorJuego(){
        ultimoTiempo = System.currentTimeMillis();
        temporizador.setDelay(partida.obtenerIntervaloMovimiento());
        temporizador.start();
    }
    public void detenerMotorJuego(){
        temporizador.stop();
    }

}
