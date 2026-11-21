package icc.pap.pl1.patrones.observer;

public  interface ObservadorJuego {
    
//hacemos interfaz ya que diferentes componentes puedaden convertirse en observadores
 void actualizar(EstadoJuegoVista estado);
 // este metodo actualizar es importante ya que sera llamado cuando la partida notifique que ocurrio un cambio
 //Estadojuegovista estado contiene los datos actualizados que necestia mostrar para la interfaz
 
}
