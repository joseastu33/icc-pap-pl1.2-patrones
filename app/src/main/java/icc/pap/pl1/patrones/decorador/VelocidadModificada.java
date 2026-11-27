package icc.pap.pl1.patrones.decorador;

public class VelocidadModificada extends DecoradorReglas {
//decorador concreto
//recibe las regglas que va envolver y multiplicamos por .70
    public VelocidadModificada(ReglasJuego reglas) {
        super(reglas);
    }
//math,max evita que el intervalo sea inferior a 60 milisegundos esto evita que la velocidad se aumente demasiado
    @Override
    public int obtenerIntervaloMovimiento() {
        return Math.max(60,(int) Math.round(reglas.obtenerIntervaloMovimiento() * 0.70)); //redondea
    }
}