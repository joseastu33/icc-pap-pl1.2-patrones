package icc.pap.pl1.patrones.decorador;

public class PuntuacionDoble extends DecoradorReglas {
    //Decorador concreto
//aqui heramos de DecoradorReglas por lo tanto tiene acceso al objeto reglas y sus metodos
    public PuntuacionDoble(ReglasJuego reglas) {
        super(reglas);
        //aqui super llama ala metodo de la clase padre de decoradorReglas
    }
//aqui solo multiplica los puntos por dos
    @Override
    public int calcularPuntos() {
        return reglas.calcularPuntos() * 2;
    }
}
//en si esta clase esta para cambiar la regla de multiplicar por dos en la otra clase hija es la velocidad