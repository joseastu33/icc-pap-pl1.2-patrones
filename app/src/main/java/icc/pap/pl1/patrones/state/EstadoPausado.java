package icc.pap.pl1.patrones.state;

import icc.pap.pl1.patrones.juego.PartidaSnake;

public class EstadoPausado implements EstadoJuego {
    private PartidaSnake partidaSnake;

    public EstadoPausado(PartidaSnake partidaSnake){
        this.partidaSnake = partidaSnake;
    }
    //no hace nada porque YA ESTÁ JUGANDO
    @Override
    public void iniciarJuego() {
       System.out.println("Se encuentra jugando");
    }

    @Override
    public void pausarJuego() {
        System.out.println("Ya se encuentra pausado");
    }
    //tampoco hace nada porque YA ESTÁ PAUSADA
    @Override
    public void reanudarJuego() {
       partidaSnake.cambiarEstado(new EstadoJugando(partidaSnake));
    }

    @Override
    public void terminarJuego() {
       partidaSnake.cambiarEstado(new EstadoTerminado(partidaSnake));
    }
}
