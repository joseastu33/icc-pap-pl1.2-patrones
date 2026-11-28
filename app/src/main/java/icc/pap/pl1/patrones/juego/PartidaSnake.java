package icc.pap.pl1.patrones.juego;
/**
 * PartidaSnake: representa al CONTEXTO de la partida y cada CONTEXTO permite hacer operaciones diferentes
 */

import java.util.List;
import java.util.ArrayList;

import icc.pap.pl1.patrones.decorador.GestorEfectos;
import icc.pap.pl1.patrones.modelo.ComidaSnake;
import icc.pap.pl1.patrones.modelo.CoordenadasTablero;
import icc.pap.pl1.patrones.modelo.SegmentoSnake;
import icc.pap.pl1.patrones.modelo.SerpienteSnake;
import icc.pap.pl1.patrones.modelo.TableroJuego;
import icc.pap.pl1.patrones.modelo.TipoComidaSnake;
import icc.pap.pl1.patrones.state.EstadoJuego;
import icc.pap.pl1.patrones.state.EstadoJugando;
import icc.pap.pl1.patrones.state.EstadoPartida;
import icc.pap.pl1.patrones.state.EstadoPreparado;
import icc.pap.pl1.patrones.state.EstadoTerminado;
import icc.pap.pl1.patrones.observer.EstadoJuegoVista;
import icc.pap.pl1.patrones.observer.ObservadorJuego;
import icc.pap.pl1.patrones.observer.SujetoJuego;

public class PartidaSnake implements SujetoJuego{

    private EstadoPartida estadoPartida; //administra una PARTIDA y determina EN QUE ESTADO se encuentra
    private SerpienteSnake serpienteSnake;
    private GeneradorComida generadorComida;
    private EstadoJuego estadoActual;
    private TableroJuego tableroJuego;
    private ComidaSnake comidaSnake;
    private int puntuacion = 0;
    private final List<ObservadorJuego> listaObservadores =new ArrayList<>();
    private final GestorEfectos gestorEfectos = new GestorEfectos();

    public PartidaSnake (TableroJuego tableroJuego, SerpienteSnake serpienteSnake){
        this.tableroJuego = tableroJuego;
        this.serpienteSnake = serpienteSnake;
        this.estadoPartida = EstadoPartida.PREPARADO;
        this.generadorComida = new GeneradorComida();
        this.comidaSnake = generadorComida.generarComidaSnake(this.tableroJuego, this.serpienteSnake);
        this.estadoActual = new EstadoPreparado(this);

        if(this.comidaSnake == null){
            this.estadoActual = new EstadoTerminado(this);
        }
    }

    public PartidaSnake(TableroJuego tableroJuego, SerpienteSnake serpienteSnake, ComidaSnake comidaSnake) {
        this.tableroJuego = tableroJuego;
        this.serpienteSnake = serpienteSnake;
        this.generadorComida = new GeneradorComida();
        this.comidaSnake = comidaSnake;

        this.estadoActual = new EstadoPreparado(this);

        if (this.comidaSnake == null) {
            this.estadoActual = new EstadoTerminado(this);
        }
    }
    public EstadoJuego getEstadoActual(){
        return estadoActual;
    }
    private EstadoJuegoVista crearEstadoVista(){
        return new EstadoJuegoVista(
            puntuacion, estadoActual.getClass().getSimpleName(),
            gestorEfectos.tienePuntuacionDoble(), gestorEfectos.tieneVelocidadModificada(),
            gestorEfectos.getTiempoDoble(), gestorEfectos.getTiempoVelocidad());
    }

    public void cambiarEstado(EstadoJuego nuevoEstadoJuego){
        this.estadoActual = nuevoEstadoJuego;
        notificarObservadores(crearEstadoVista());
    }
    //conectar las OPERACIONES de los ESTADOS con **PartidaSnake**
    public void iniciarJuego(){
        estadoActual.iniciarJuego();
    }

    public void pausarJuego(){
        estadoActual.pausarJuego();
    }

    public void reanudarJuego(){
        estadoActual.reanudarJuego();
    }

    public void terminarJuego(){
        estadoActual.terminarJuego();
        System.out.println("COLISION | Estado actual: "+ estadoActual.getClass().getSimpleName());
    }
    //la interfaz permite solicitar OPERACIOENS pero NO CONOCE EL ESTADO
    public boolean estaJugando(){
        return estadoActual instanceof EstadoJugando;
    }
    public void moverSerpiente(){
        //gestorEfectos.actualizar(180, estaJugando()); //los efectos disminuyen mientras
        //mientras la PARTIDA está en CURSO: SE DETIENEN cuando está PAUSADA
        CoordenadasTablero siguientePosicion = serpienteSnake.calcularSiguientePosicion();

         System.out.println(
        "Cabeza siguiente: X=" + siguientePosicion.getCoorX()
        + ", Y=" + siguientePosicion.getCoorY()
        + " | Columnas=" + tableroJuego.getColumnas()
        + " | Filas=" + tableroJuego.getFilas());

        boolean serpienteCome = comidaSnake != null 
        && siguientePosicion.getCoorX() == comidaSnake.getCoordenadasTablero().getCoorX() 
        && siguientePosicion.getCoorY() == comidaSnake.getCoordenadasTablero().getCoorY();
        //si come = true, la cabeza alcanzará a comer en el PROXIMO MOVIMIENTO
        if(fueraDelTablero(siguientePosicion) || chocaConCuerpo(siguientePosicion, serpienteCome)){
            terminarJuego();
            System.out.println(
            "PRUEBA | Cabeza después de colisión: "
            + serpienteSnake.getCuerpoSerpiente().get(0).getCoordenadasTablero().getCoorX()
            + ", "
            + serpienteSnake.getCuerpoSerpiente().get(0).getCoordenadasTablero().getCoorY());
            return;
        }
        int cantidadCrecimiento = 0;
        if(serpienteCome){
            cantidadCrecimiento = 1;
            if(comidaSnake.getTipoComidaSnake() == TipoComidaSnake.CRECIMIENTO){
                cantidadCrecimiento = 2;
            }
        }
        if(serpienteCome){
            puntuacion += gestorEfectos.obtenerReglas().calcularPuntos();
        }
        System.out.println("MOVIMIENTO | Estado: " + estadoActual.getClass().getSimpleName()
        + " | Siguiente X: " + siguientePosicion.getCoorX()
        + " | Siguiente Y: " + siguientePosicion.getCoorY());
        serpienteSnake.desplazarSegmentos(cantidadCrecimiento);
        notificarObservadores(crearEstadoVista());
        if(serpienteCome){
            //antes de generar la COMIDA, tiene que ACTIVARSE su EFECTO
            gestorEfectos.activar(comidaSnake.getTipoComidaSnake());
            comidaSnake = generadorComida.generarComidaSnake(tableroJuego, serpienteSnake);
            if(comidaSnake == null){
                terminarJuego();
            } else {
                notificarObservadores(crearEstadoVista());
            }
        }

    }

    public boolean fueraDelTablero(CoordenadasTablero posicion){
        return posicion.getCoorX() < 0 || posicion.getCoorX() >= tableroJuego.getColumnas() 
        || posicion.getCoorY() < 0 || posicion.getCoorY() >= tableroJuego.getFilas();
    }

    private boolean chocaConCuerpo(CoordenadasTablero posicionNueva, boolean crece){ //valida si el cuerpo colisiona con una pared
        List<SegmentoSnake> cuerpo = serpienteSnake.getCuerpoSerpiente();
        int limite = crece ? cuerpo.size() : cuerpo.size()-1; //operador ternario if else
            //si crece se revisan todos los segmentos desde el índice 1 (incluido LA COLA)
            //si NO crece se excluye la cola : ya que abandonará la posición actual
        for(int i = 1; i < limite; i++){
            CoordenadasTablero posicionSegmento = cuerpo.get(i).getCoordenadasTablero();
            if(posicionNueva.getCoorX() == posicionSegmento.getCoorX() && posicionNueva.getCoorY() == posicionSegmento.getCoorY()){
                return true;
            }
        }
        return false;
    }

    @Override 
    public void agregarObservador(ObservadorJuego observadorJ){
        if(!listaObservadores.contains(observadorJ)){
            listaObservadores.add(observadorJ);
        }
    }
    //un observador deja de estar SUSCRITO
    @Override 
    public void eliminarObservador(ObservadorJuego observadorJ){
        listaObservadores.remove(observadorJ);
    }

    @Override 
    public void notificarObservadores(EstadoJuegoVista estado){
        //a cada OBSERVADOR se le notifica
        for(ObservadorJuego observador : listaObservadores){
            observador.actualizar(estado); //y estos REACCIONAN CAMBIANDO SU estado
        } 
    }

    public void actualizarEfectos(long milisegundos){
        if(!estaJugando()){
            return;
        }
        boolean hayEfectosActivos = gestorEfectos.tienePuntuacionDoble() || gestorEfectos.tieneVelocidadModificada();

        if(!hayEfectosActivos){
            return;
        }
        //se GUARDAN los SEGUNDOS RESTANTES antes de ACTUALIZAR
        long segundosAntesDoble = gestorEfectos.getTiempoDoble()/1000;
        long segundosAntesVelocida = gestorEfectos.getTiempoVelocidad()/1000;

        gestorEfectos.actualizar(milisegundos, true);

        //se CONSULTA los segundos RESTANTES DESPUES de actualizar
        long segundosDespuesDoble =gestorEfectos.getTiempoDoble()/1000;
        long segundosDespuesVelocidad = gestorEfectos.getTiempoVelocidad()/1000;

        if(segundosAntesDoble != segundosDespuesDoble || segundosAntesVelocida != segundosDespuesVelocidad){
            notificarObservadores(crearEstadoVista());
        }
    }

    public int obtenerIntervaloMovimiento(){
        return gestorEfectos.obtenerReglas().obtenerIntervaloMovimiento();
    }

    public SerpienteSnake getSerpienteSnake(){
        return serpienteSnake;
    }

    public ComidaSnake getComidaSnake(){
        return comidaSnake;
    }
    public TableroJuego getTableroJuego(){
        return tableroJuego;
    }
    
    public void reiniciarPartida() {

    // Recuperar posición y dirección inicial de la serpiente
    serpienteSnake.reiniciar();

    // Reiniciar puntuación
    puntuacion = 0;

    // Eliminar efectos activos
    gestorEfectos.reiniciar();

    // Generar comida nueva
    comidaSnake = generadorComida.generarComidaSnake(
            tableroJuego,
            serpienteSnake
    );

    // Recuperar el estado inicial
    if (comidaSnake == null) {
        estadoActual = new EstadoTerminado(this);
    } else {
        estadoActual = new EstadoPreparado(this);
    }

    // Actualizar todos los observadores
    notificarObservadores(crearEstadoVista());
}
}
