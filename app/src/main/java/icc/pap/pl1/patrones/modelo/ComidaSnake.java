package icc.pap.pl1.patrones.modelo;
/**
 * 
 * ComidaSnake: la comida de la serpiente con sus TIPOS
 */
public class ComidaSnake {
    private CoordenadasTablero coordenadasTablero; //la comida debe aparecer en un lugar del tablero
    private TipoComidaSnake tipoComidaSnake; //esa comida DEBE SER de algún TIPO

    //la COMIDA deberá inicializarse CONSIDERANDO su TIPO y sus COORDENADAS
    public ComidaSnake(CoordenadasTablero coordenadasTablero, TipoComidaSnake tipoComidaSnake) {
        this.coordenadasTablero = coordenadasTablero;
        this.tipoComidaSnake = tipoComidaSnake;
    }

    public CoordenadasTablero getCoordenadasTablero() {
        return coordenadasTablero;
    }

    public TipoComidaSnake getTipoComidaSnake() {
        return tipoComidaSnake;
    }
    
    
}
