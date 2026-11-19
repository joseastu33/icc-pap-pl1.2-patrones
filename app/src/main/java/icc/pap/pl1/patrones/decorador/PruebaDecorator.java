package icc.pap.pl1.patrones.decorador;

public class PruebaDecorator {
// clase unicamente para ver si funciona el decorator anadir puntos los normal es 10 se agregan y el intervalo de 180
    public static void main(String[] args) {

        ReglasJuego reglas = new ReglasBase();

        System.out.println("Reglas normales:");
        System.out.println("Puntos: " + reglas.calcularPuntos());
        System.out.println("Intervalo: "
                + reglas.obtenerIntervaloMovimiento());

        reglas = new PuntuacionDoble(reglas);
        reglas = new VelocidadModificada(reglas);

        System.out.println("\nCon ambos decoradores:");
        System.out.println("Puntos: " + reglas.calcularPuntos());
        System.out.println("Intervalo: "  + reglas.obtenerIntervaloMovimiento());
    }
}