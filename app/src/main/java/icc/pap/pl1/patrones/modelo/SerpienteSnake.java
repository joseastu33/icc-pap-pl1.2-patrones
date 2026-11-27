package icc.pap.pl1.patrones.modelo;

import java.util.List;
/**
 * 
 * SerpienteSnake: cuerpo completo de la SERPIENTE incluyendo su dirección 
 */

public class SerpienteSnake {
    private List<SegmentoSnake> cuerpoSerpiente; //se usa UNA SOLA LISTA para al querer obtener la cabeza y COLA se consulte
    //es consultado al indice 0 y -1
    private SentidoMovimiento sentidoMovimiento;

    public SerpienteSnake(List<SegmentoSnake> cuerpoSerpiente, SentidoMovimiento sentidoMovimiento) {
        this.cuerpoSerpiente = cuerpoSerpiente;
        this.sentidoMovimiento = sentidoMovimiento;
    }

    public List<SegmentoSnake> getCuerpoSerpiente() {
        return List.copyOf(cuerpoSerpiente); //lista que permite CONSULTAR elementos SIN MODIFICAR su estructura
    }

    public SentidoMovimiento getSentidoMovimiento() {
        return sentidoMovimiento;
    }
    //cuando la cabeza esté EN UNA POSICION:
    //calcular la siguiente
    public CoordenadasTablero calcularSiguientePosicion(){
        SegmentoSnake cabezaSnake = cuerpoSerpiente.getFirst();
        CoordenadasTablero coordenadasCabezaSnake = cabezaSnake.getCoordenadasTablero();
        int nuevaCoorX = coordenadasCabezaSnake.getCoorX();
        int nuevaCoorY = coordenadasCabezaSnake.getCoorY();
        switch(sentidoMovimiento){
            case ARRIBA:
                nuevaCoorY--;
                break;
            case ABAJO:
                nuevaCoorY++;
                break;
            case IZQUIERDA:
                nuevaCoorX--;
                break;
            case DERECHA:
                nuevaCoorX++;
                break;
            }
        return new CoordenadasTablero(nuevaCoorX, nuevaCoorY);
    }

    public void desplazarSegmentos(int cantidadCrecimiento ){
        //el cuerpo de la serpiente es desplazado de la cola a la cabeza
        //lo cual permite NO sobreescribir posiciones que se necesiten

        //nueva posicion de la CABEZA
        CoordenadasTablero nuevaPosicionCabeza = calcularSiguientePosicion();

        //la posicion ANTERIOR de la COLA se guarda
        SegmentoSnake colaSnake = cuerpoSerpiente.getLast();
        CoordenadasTablero posicionAnteriorCola = colaSnake.getCoordenadasTablero();

        for(int i = cuerpoSerpiente.size() -1; i > 0; i--){
            SegmentoSnake segmentoActual = cuerpoSerpiente.get(i);
            SegmentoSnake segmentoAnterior = cuerpoSerpiente.get(i-1); //un indice atrás ESTA el segmento ANTERIOR
            segmentoActual.setCoordenadasTablero(segmentoAnterior.getCoordenadasTablero());
        }
        cuerpoSerpiente.getFirst().setCoordenadasTablero(nuevaPosicionCabeza);
        
        for(int i = 0; i < cantidadCrecimiento; i++){
            cuerpoSerpiente.add(new SegmentoSnake(posicionAnteriorCola));
        }

    }
    

    
    
}
