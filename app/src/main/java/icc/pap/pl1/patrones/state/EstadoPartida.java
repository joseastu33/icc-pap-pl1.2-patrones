package icc.pap.pl1.patrones.state;
/**
 * 
 * EstadoPartida: un ENUM con los 4 ESTADOS de una partida
 */
public enum EstadoPartida {
    PREPARADO,
    JUGANDO,
    PAUSADO,
    TERMINADO //una partida se encuentra unicamente en uno de dichos estados
    //de esta FORMA evitamos tener que validar con Strings
}
