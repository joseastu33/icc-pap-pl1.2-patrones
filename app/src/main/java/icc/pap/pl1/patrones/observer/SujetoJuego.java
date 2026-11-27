package icc.pap.pl1.patrones.observer;

public interface SujetoJuego {
      void agregarObservador(ObservadorJuego observador);
//agregar
    void eliminarObservador(ObservadorJuego observador);
//eliminar
    void notificarObservadores(EstadoJuegoVista estado);
    //notifica todos los cambios a lso obervadores registrados
}
