package icc.pap.pl1.patrones.state;

import icc.pap.pl1.patrones.juego.PartidaSnake;

public class EstadoPreparado implements EstadoJuego{
    private final PartidaSnake partidaSnake;

    public EstadoPreparado(PartidaSnake partidaSnake){
        this.partidaSnake = partidaSnake;
    }

    //Al INICIAR el juego se CAMBIA el estado
    @Override 
    public void iniciarJuego(){
        partidaSnake.cambiarEstado(new EstadoJugando(partidaSnake));
    }

    @Override 
    public void pausarJuego(){

    }

    @Override 
    public void reanudarJuego(){

    }

    @Override 
    public void terminarJuego(){
        partidaSnake.cambiarEstado(new EstadoTerminado(partidaSnake));
    }
}
