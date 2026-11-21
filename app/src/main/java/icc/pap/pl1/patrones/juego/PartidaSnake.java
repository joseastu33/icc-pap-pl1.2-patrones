package icc.pap.pl1.patrones.juego;
/**
 * PartidaSnake: representa al CONTEXTO de la partida y cada CONTEXTO permite hacer operaciones diferentes
 */

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.ComidaSnake;
import icc.pap.pl1.patrones.modelo.SerpienteSnake;
import icc.pap.pl1.patrones.modelo.TableroJuego;
import icc.pap.pl1.patrones.state.EstadoJuego;
import icc.pap.pl1.patrones.state.EstadoJugando;
import icc.pap.pl1.patrones.state.EstadoPartida;
import icc.pap.pl1.patrones.state.EstadoPreparado;
import icc.pap.pl1.patrones.state.EstadoTerminado;

public class PartidaSnake {

    private EstadoPartida estadoPartida; //administra una PARTIDA y determina EN QUE ESTADO se encuentra
    private SerpienteSnake serpienteSnake;
    private GeneradorComida generadorComida;
    private EstadoJuego estadoActual;
    private TableroJuego tableroJuego;
    private ComidaSnake comidaSnake;
    private BigDecimal puntuacion;

    public PartidaSnake (TableroJuego tableroJuego, SerpienteSnake serpienteSnake){
        this.tableroJuego = tableroJuego;
        this.serpienteSnake = serpienteSnake;
        this.estadoPartida = EstadoPartida.PREPARADO;
        this.puntuacion = null; //inicialmente NO EXISTE una puntuación ASIGNADA
        this.generadorComida = new GeneradorComida();
        this.comidaSnake = generadorComida.generarComidaSnake(this.tableroJuego, this.serpienteSnake);
        this.estadoActual = new EstadoPreparado(this);

        if(this.comidaSnake == null){
            this.estadoActual = new EstadoTerminado(this);
        }
    }
    public EstadoJuego getEstadoActual(){
        return estadoActual;
    }
    public void cambiarEstado(EstadoJuego nuevoEstadoJuego){
        this.estadoActual = nuevoEstadoJuego;
    }
    //conectar las OPERACIONES de los ESTADOS con **PartidaSnake**
    public void iniciarJuego(){
        estadoActual.iniciarJuego();
    }

    public void pausarJuego(){
        estadoActual.pausarJuego();
    }

    public void reanudarJuego(){
        estadoActual.reanudarJuego();
    }

    public void terminarJuego(){
        estadoActual.terminarJuego();
    }
    //la interfaz permite solicitar OPERACIOENS pero NO CONOCE EL ESTADO
    public boolean estaJugando(){
        return estadoActual instanceof EstadoJugando;
    }
    public void moverSerpiente(){
        serpienteSnake.mover(false); //al moverse no crece, solo cuando COME
        
    }
    
}
