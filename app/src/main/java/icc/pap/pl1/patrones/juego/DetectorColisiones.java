package icc.pap.pl1.patrones.juego;

import icc.pap.pl1.patrones.modelo.CoordenadasTablero;
import icc.pap.pl1.patrones.modelo.SegmentoSnake;
import icc.pap.pl1.patrones.modelo.SerpienteSnake;
import icc.pap.pl1.patrones.modelo.TableroJuego;

/**
 * 
 * DetectorColisiones
 */
public class DetectorColisiones {
    //metodo que detecta si hay colisión: a partir de cuatro casos
    public boolean hayColisionConPared(CoordenadasTablero siguientePosicion, TableroJuego tableroJuego){
        if(siguientePosicion.getCoorX() < 0 || siguientePosicion.getCoorY() < 0){
            return true;
        }
        if(siguientePosicion.getCoorX() >= tableroJuego.getColumnas()){ //caso: la CABEZA quiere moverse hacia un LUGAR que no existe en X
            return true;
        }
        else if(siguientePosicion.getCoorY() >= tableroJuego.getFilas()){ //caso: la CABEZA quiere moverse hacia un LUGAR que no existe en Y

            return true;
        } 
        return false;
    }
    public boolean hayColisionConCuerpo(CoordenadasTablero siguientePosicion, SerpienteSnake serpiente, boolean vaAComer){
        for(int i = 0; i < serpiente.getCuerpoSerpiente().size(); i++){
            SegmentoSnake segm = serpiente.getCuerpoSerpiente().get(i);
            if(segm.getCoordenadasTablero().getCoorX() == siguientePosicion.getCoorX() 
                && segm.getCoordenadasTablero().getCoorY() == siguientePosicion.getCoorY()){
                    if(i == serpiente.getCuerpoSerpiente().size()-1){
                        if(vaAComer){
                            return true;
                        }
                    } else {
                        return true;
                    }
            }
        }
        return false;
    }
}
