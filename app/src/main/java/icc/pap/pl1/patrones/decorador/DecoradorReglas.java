package icc.pap.pl1.patrones.decorador;
//decorador abstracto
public abstract class DecoradorReglas implements ReglasJuego {
    //decorador abstractoe
//*
// esta clase es la padre de puntuacion doble y velocidad modificada 
//implementa los metodos de nuestra interface 
// cumple el contrato que pusimos en reglasjuego
//   */
    protected final ReglasJuego reglas;
// aqui guardamos un objeto que implementa ReglasJuego el protected permite que las clases hijas usen estos atributos


    public DecoradorReglas(ReglasJuego reglas) {
        this.reglas = reglas;
    }
// decorador envoltorio si tenemos un decorador con puntuacion doble le podemos entregar las reglas normales
   
//delega los el trabajo al objeto que recibio 
@Override
    public int calcularPuntos() {
        return reglas.calcularPuntos();
    }

    @Override
    public int obtenerIntervaloMovimiento() {
        return reglas.obtenerIntervaloMovimiento();
    }
}

// en esta clase envuelve un objeteo que cumple el contrato y delega sus operaciones 