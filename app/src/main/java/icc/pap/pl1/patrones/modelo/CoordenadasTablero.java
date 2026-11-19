package icc.pap.pl1.patrones.modelo;
/**
 * 
 * CoordenadasTablero: objeto que REPRESENTA EL ESPACIO donde la SERPIENTE puede MOVERSE
 */

public class CoordenadasTablero {
    //coordenadas inmutables tras CREAR EL OBJETO
    private final int coorX; //inmutable luego de CREAR
    private final int coorY; //inmutable luego de CREAR

    public CoordenadasTablero(int coorX, int coorY){
        this.coorX = coorX;
        this.coorY = coorY;
    }

    public int getCoorX(){
        return coorX;
    }

    public int getCoorY(){
        return coorY;
    }
}
