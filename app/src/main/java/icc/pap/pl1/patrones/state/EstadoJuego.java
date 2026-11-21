package icc.pap.pl1.patrones.state;
/**
 * 
 * EstadoJuego: INTERFAZ que REPRESENTA que OPERACIONES y COMPORTAMIENTOS
 * debe IMPLEMENTAR cada ESTADO
 */
public interface EstadoJuego {
    //es un TIPO COMUN 
    void iniciarJuego();
    void pausarJuego();
    void reanudarJuego();
    void terminarJuego();
}
