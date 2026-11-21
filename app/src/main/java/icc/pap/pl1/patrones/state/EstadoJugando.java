package icc.pap.pl1.patrones.state;

import icc.pap.pl1.patrones.juego.PartidaSnake;

public class EstadoJugando implements EstadoJuego {
    private PartidaSnake partidaSnake;
    public EstadoJugando(PartidaSnake partidaSnake){
        this.partidaSnake = partidaSnake;
    }
    //no hace nada porque YA ESTÁ JUGANDO
    @Override
    public void iniciarJuego() {
       System.out.println("Se encuentra jugando");
    }

    @Override
    public void pausarJuego() {
        partidaSnake.cambiarEstado(new EstadoPausado(partidaSnake));
    }
    //tampoco hace nada porque YA ESTÁ JUGANDO
    @Override
    public void reanudarJuego() {
       System.out.println("Se encuentra jugando");
    }

    @Override
    public void terminarJuego() {
       partidaSnake.cambiarEstado(new EstadoTerminado(partidaSnake));
    }
    
}
