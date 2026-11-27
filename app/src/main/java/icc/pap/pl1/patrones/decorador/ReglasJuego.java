package icc.pap.pl1.patrones.decorador;


public interface ReglasJuego {
    // aqui tenemos el componente concreto contiene las reglas del juego 
//es un contrato que estabece que metdos se va establecer
    int calcularPuntos();
// indica cuantos puntos se obtienen por consumir una comida
   //devuelve el tiempo que debe esperar la serpiente antes de hacer otro movimiento
int obtenerIntervaloMovimiento();
}