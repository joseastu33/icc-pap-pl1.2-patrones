package icc.pap.pl1.patrones.observer;

public class EstadoJuegoVista {
    // vamos a poner los atrivutos que guardan la info de de la partida 
    private final int puntuacion;
    private final String estado;
    private final boolean puntuacionDoble;
    private final boolean velocidadModificada;
    private final long tiempoDoble;
    private final long tiempoVelocidad;
       
    //final para que lso datos del objeto no cambien despues de construirlo
    //constructor
    public EstadoJuegoVista(int puntuacion, String estado, boolean puntuacionDoble, boolean velocidadModificada,
        long tiempoDoble, long tiempoVelocidad) {
            this.puntuacion = puntuacion;
            this.estado = estado;
            this.puntuacionDoble = puntuacionDoble;
            this.velocidadModificada = velocidadModificada;
            this.tiempoDoble = tiempoDoble;
            this.tiempoVelocidad = tiempoVelocidad;
         }

         public int getPuntuacion() {
             return puntuacion;
         }

         public String getEstado() {
             return estado;
         }

         public boolean isPuntuacionDoble() {
             return puntuacionDoble;
         }

         public boolean isVelocidadModificada() {
             return velocidadModificada;
         }

         public long getTiempoDoble() {
             return tiempoDoble;
         }

         public long getTiempoVelocidad() {
             return tiempoVelocidad;
         }

        
         
}
