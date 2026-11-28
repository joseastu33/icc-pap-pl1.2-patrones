
package icc.pap.pl1.patrones.juego;

/**
 * PartidaSnake representa el contexto de la partida.
 * Administra los estados, la serpiente, la comida,
 * la puntuación y los efectos del juego.
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

public class PartidaSnake implements SujetoJuego {

    private EstadoPartida estadoPartida;

    private SerpienteSnake serpienteSnake;
    private GeneradorComida generadorComida;
    private EstadoJuego estadoActual;
    private TableroJuego tableroJuego;
    private ComidaSnake comidaSnake;

    private int puntuacion = 0;

    private final List<ObservadorJuego> listaObservadores =
            new ArrayList<>();

    private final GestorEfectos gestorEfectos =
            new GestorEfectos();

    // =======================================
    // CONSTRUCTORES
    // =======================================

    public PartidaSnake(
            TableroJuego tableroJuego,
            SerpienteSnake serpienteSnake) {

        this.tableroJuego = tableroJuego;
        this.serpienteSnake = serpienteSnake;

        this.estadoPartida = EstadoPartida.PREPARADO;

        this.generadorComida = new GeneradorComida();

        this.comidaSnake = generadorComida.generarComidaSnake(
                this.tableroJuego,
                this.serpienteSnake
        );

        this.estadoActual = new EstadoPreparado(this);

        if (this.comidaSnake == null) {
            this.estadoActual = new EstadoTerminado(this);
        }
    }

    public PartidaSnake(
            TableroJuego tableroJuego,
            SerpienteSnake serpienteSnake,
            ComidaSnake comidaSnake) {

        this.tableroJuego = tableroJuego;
        this.serpienteSnake = serpienteSnake;

        this.generadorComida = new GeneradorComida();
        this.comidaSnake = comidaSnake;

        this.estadoPartida = EstadoPartida.PREPARADO;

        this.estadoActual = new EstadoPreparado(this);

        if (this.comidaSnake == null) {
            this.estadoActual = new EstadoTerminado(this);
        }
    }

    // =======================================
    // PATRÓN STATE
    // =======================================

    public EstadoJuego getEstadoActual() {
        return estadoActual;
    }

    public void cambiarEstado(EstadoJuego nuevoEstadoJuego) {

        this.estadoActual = nuevoEstadoJuego;

        notificarObservadores(crearEstadoVista());
    }

    public void iniciarJuego() {
        estadoActual.iniciarJuego();
    }

    public void pausarJuego() {
        estadoActual.pausarJuego();
    }

    public void reanudarJuego() {
        estadoActual.reanudarJuego();
    }

    public void terminarJuego() {

        estadoActual.terminarJuego();

        System.out.println(
                "COLISION | Estado actual: "
                + estadoActual.getClass().getSimpleName()
        );
    }

    public boolean estaJugando() {
        return estadoActual instanceof EstadoJugando;
    }

    // =======================================
    // MOVIMIENTO DE LA SERPIENTE
    // =======================================

    public void moverSerpiente() {

        CoordenadasTablero siguientePosicion =
                serpienteSnake.calcularSiguientePosicion();

        System.out.println(
                "Cabeza siguiente: X="
                + siguientePosicion.getCoorX()
                + ", Y="
                + siguientePosicion.getCoorY()
                + " | Columnas="
                + tableroJuego.getColumnas()
                + " | Filas="
                + tableroJuego.getFilas()
        );

        boolean serpienteCome = comidaSnake != null
                && siguientePosicion.getCoorX()
                == comidaSnake.getCoordenadasTablero().getCoorX()
                && siguientePosicion.getCoorY()
                == comidaSnake.getCoordenadasTablero().getCoorY();

        // Comprobar colisiones
        if (fueraDelTablero(siguientePosicion)
                || chocaConCuerpo(siguientePosicion, serpienteCome)) {

            terminarJuego();
            return;
        }

        // Determinar crecimiento
        int cantidadCrecimiento = 0;

        if (serpienteCome) {

            cantidadCrecimiento = 1;

            if (comidaSnake.getTipoComidaSnake()
                    == TipoComidaSnake.CRECIMIENTO) {

                cantidadCrecimiento = 2;
            }
        }

        // Calcular puntuación
        if (serpienteCome) {

            puntuacion += gestorEfectos
                    .obtenerReglas()
                    .calcularPuntos();
        }

        // Mover serpiente
        serpienteSnake.desplazarSegmentos(cantidadCrecimiento);

        notificarObservadores(crearEstadoVista());

        // Si comió, activar efecto y generar nueva comida
        if (serpienteCome) {

            gestorEfectos.activar(
                    comidaSnake.getTipoComidaSnake()
            );

            comidaSnake = generadorComida.generarComidaSnake(
                    tableroJuego,
                    serpienteSnake
            );

            if (comidaSnake == null) {
                terminarJuego();
            } else {
                notificarObservadores(crearEstadoVista());
            }
        }
    }

    // =======================================
    // DETECCIÓN DE COLISIONES
    // =======================================

    public boolean fueraDelTablero(CoordenadasTablero posicion) {

        return posicion.getCoorX() < 0
                || posicion.getCoorX() >= tableroJuego.getColumnas()
                || posicion.getCoorY() < 0
                || posicion.getCoorY() >= tableroJuego.getFilas();
    }

    private boolean chocaConCuerpo(
            CoordenadasTablero posicionNueva,
            boolean crece) {

        List<SegmentoSnake> cuerpo =
                serpienteSnake.getCuerpoSerpiente();

        int limite = crece
                ? cuerpo.size()
                : cuerpo.size() - 1;

        for (int i = 1; i < limite; i++) {

            CoordenadasTablero posicionSegmento =
                    cuerpo.get(i).getCoordenadasTablero();

            if (posicionNueva.getCoorX()
                    == posicionSegmento.getCoorX()
                    && posicionNueva.getCoorY()
                    == posicionSegmento.getCoorY()) {

                return true;
            }
        }

        return false;
    }

    // =======================================
    // PATRÓN OBSERVER
    // =======================================

    @Override
    public void agregarObservador(ObservadorJuego observadorJ) {

        if (!listaObservadores.contains(observadorJ)) {
            listaObservadores.add(observadorJ);
        }
    }

    @Override
    public void eliminarObservador(ObservadorJuego observadorJ) {

        listaObservadores.remove(observadorJ);
    }

    @Override
    public void notificarObservadores(EstadoJuegoVista estado) {

        for (ObservadorJuego observador : listaObservadores) {

            observador.actualizar(estado);
        }
    }

    private EstadoJuegoVista crearEstadoVista() {

        return new EstadoJuegoVista(
                puntuacion,
                estadoActual.getClass().getSimpleName(),
                gestorEfectos.tienePuntuacionDoble(),
                gestorEfectos.tieneVelocidadModificada(),
                gestorEfectos.getTiempoDoble(),
                gestorEfectos.getTiempoVelocidad()
        );
    }

    // =======================================
    // GESTIÓN DE EFECTOS
    // =======================================

    public void actualizarEfectos(long milisegundos) {

        if (!estaJugando()) {
            return;
        }

        boolean hayEfectosActivos =
                gestorEfectos.tienePuntuacionDoble()
                || gestorEfectos.tieneVelocidadModificada();

        if (!hayEfectosActivos) {
            return;
        }

        long segundosAntesDoble =
                gestorEfectos.getTiempoDoble() / 1000;

        long segundosAntesVelocidad =
                gestorEfectos.getTiempoVelocidad() / 1000;

        gestorEfectos.actualizar(milisegundos, true);

        long segundosDespuesDoble =
                gestorEfectos.getTiempoDoble() / 1000;

        long segundosDespuesVelocidad =
                gestorEfectos.getTiempoVelocidad() / 1000;

        if (segundosAntesDoble != segundosDespuesDoble
                || segundosAntesVelocidad != segundosDespuesVelocidad) {

            notificarObservadores(crearEstadoVista());
        }
    }

    public int obtenerIntervaloMovimiento() {

        return gestorEfectos
                .obtenerReglas()
                .obtenerIntervaloMovimiento();
    }

    // =======================================
    // GENERACIÓN Y UBICACIÓN DE COMIDA
    // =======================================

    public boolean ubicarComida(
            int x,
            int y,
            TipoComidaSnake tipo) {

        // Comprobar coordenadas dentro del tablero
        if (x < 0 || x >= tableroJuego.getColumnas()
                || y < 0 || y >= tableroJuego.getFilas()) {

            return false;
        }

        // Comprobar que la posición esté libre
        for (SegmentoSnake segmento :
                serpienteSnake.getCuerpoSerpiente()) {

            CoordenadasTablero coordenadas =
                    segmento.getCoordenadasTablero();

            if (coordenadas.getCoorX() == x
                    && coordenadas.getCoorY() == y) {

                return false;
            }
        }

        // Crear comida
        CoordenadasTablero coordenadasComida =
                new CoordenadasTablero(x, y);

        comidaSnake = new ComidaSnake(
                coordenadasComida,
                tipo
        );

        // Actualizar interfaz
        notificarObservadores(crearEstadoVista());

        return true;
    }

    public boolean generarComidaAleatoria() {

        comidaSnake = generadorComida.generarComidaSnake(
                tableroJuego,
                serpienteSnake
        );

        if (comidaSnake == null) {
            terminarJuego();
            return false;
        }

        notificarObservadores(crearEstadoVista());

        return true;
    }

    // =======================================
    // GETTERS
    // =======================================

    public SerpienteSnake getSerpienteSnake() {
        return serpienteSnake;
    }

    public ComidaSnake getComidaSnake() {
        return comidaSnake;
    }

    public TableroJuego getTableroJuego() {
        return tableroJuego;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    // =======================================
    // REINICIAR PARTIDA
    // =======================================

    public void reiniciarPartida() {

        // Recuperar posición inicial de la serpiente
        serpienteSnake.reiniciar();

        // Reiniciar puntuación
        puntuacion = 0;

        // Reiniciar efectos
        gestorEfectos.reiniciar();

        // Generar comida nueva
        comidaSnake = generadorComida.generarComidaSnake(
                tableroJuego,
                serpienteSnake
        );

        // Recuperar estado inicial
        if (comidaSnake == null) {

            estadoActual = new EstadoTerminado(this);

        } else {

            estadoActual = new EstadoPreparado(this);
        }

        // Actualizar observadores
        notificarObservadores(crearEstadoVista());
    }
}
