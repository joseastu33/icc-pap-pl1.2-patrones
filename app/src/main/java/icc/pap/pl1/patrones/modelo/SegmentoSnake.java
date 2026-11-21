package icc.pap.pl1.patrones.modelo;
/**
 * 
 * SegmentoSnake: CUERPO de la serpiente, incluido Cabeza y Cola
 */
public class SegmentoSnake {
    //el segmento DEBE tener coordenadas que indiquen DONDE SE ENCUENTRA
    //por eso debe tener un objeto que represente esas coordenadas, ese objeto es un CoordenadasTablero
    private CoordenadasTablero coordenadasTablero;

    public SegmentoSnake(CoordenadasTablero coordenadasTablero){
        this.coordenadasTablero = coordenadasTablero;
    }
    public void setCoordenadasTablero(CoordenadasTablero coordenadasTablero){
        this.coordenadasTablero = coordenadasTablero;
    }
    public CoordenadasTablero getCoordenadasTablero(){
        return coordenadasTablero;
    }
}
