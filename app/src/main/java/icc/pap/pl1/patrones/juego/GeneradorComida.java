package icc.pap.pl1.patrones.juego;

import java.util.Random;

import icc.pap.pl1.patrones.modelo.ComidaSnake;
import icc.pap.pl1.patrones.modelo.CoordenadasTablero;
import icc.pap.pl1.patrones.modelo.SegmentoSnake;
import icc.pap.pl1.patrones.modelo.SerpienteSnake;
import icc.pap.pl1.patrones.modelo.TableroJuego;
import icc.pap.pl1.patrones.modelo.TipoComidaSnake;

public class GeneradorComida {
    public ComidaSnake generarComidaSnake(TableroJuego tableroJuego, SerpienteSnake serpienteSnake){
        int casillasTotalesDelTablero = tableroJuego.getFilas() * tableroJuego.getColumnas();
        int segmentosOcupados = serpienteSnake.getCuerpoSerpiente().size();
        if(segmentosOcupados >= casillasTotalesDelTablero){ //los SEGMENTOS OCUPADOS NO deben ser MAYORES
            //NO deben ser mayores que las CASILLAS TOTALES del TABLERO
            return null;
        }
        Random posRandom = new Random();
        int x = 0;
        int y = 0;
        boolean estaOcupado = true;
        while(estaOcupado){
            x = posRandom.nextInt(tableroJuego.getColumnas());
            y = posRandom.nextInt(tableroJuego.getFilas());
            estaOcupado = false;
            //iterador FOR que recorre todos los SEGMENTOS de la SERPIENTE para
            //para COMPROBAR si alguno de dichos segmentos OCUPA las coordenas aleatorias
            //esto sirve para saber si se puede colocar comida aquí o no (ya que está el cuerpo de la serpiente)
            for(SegmentoSnake segmentoSerpiente : serpienteSnake.getCuerpoSerpiente()){
                if(segmentoSerpiente.getCoordenadasTablero().getCoorX() == x 
                && segmentoSerpiente.getCoordenadasTablero().getCoorY() == y){
                    estaOcupado = true;
                    break;
                }
            }
        }
        CoordenadasTablero coordenadas = new CoordenadasTablero(x, y);
        int numero = posRandom.nextInt(100);
        //dependiendo del VALOR RANDOMICO:
        //1) la comida otorgará más PUNTOS
        //2) se activarán efectos visuales
        TipoComidaSnake tipo;
        if(numero <= 19){
            tipo = TipoComidaSnake.NORMAL;
        } else if (numero <= 49){
            tipo = TipoComidaSnake.PUNTUACION_DOBLE;
        } else if (numero <= 79){
            tipo = TipoComidaSnake.VELOCIDAD;
        } else {
            tipo = TipoComidaSnake.CRECIMIENTO; //Se añade un efecto para HACER CRECER EL SEGMENTO
        }
    return new ComidaSnake(coordenadas, tipo);
    }
}
