package icc.pap.pl1.patrones.state;

import icc.pap.pl1.patrones.juego.PartidaSnake;

public class EstadoTerminado implements EstadoJuego {
    private PartidaSnake partidaSnake;

    public EstadoTerminado(PartidaSnake partidaSnake){
        this.partidaSnake = partidaSnake;
    }
    //si la partida ha ACABADO, todos DEBERIAN MOSTRARA que YA acabó
    @Override
    public void iniciarJuego() {
       System.out.println("La partida ya terminó");
    }

    @Override
    public void pausarJuego() {
       System.out.println("La partida ya terminó");
    }

    @Override
    public void reanudarJuego() {
        System.out.println("La partida ya terminó");
    }

    @Override
    public void terminarJuego() {
        System.out.println("La partida ya terminó");
    }
    
}

