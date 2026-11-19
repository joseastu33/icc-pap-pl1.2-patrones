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
    

    
    
}
