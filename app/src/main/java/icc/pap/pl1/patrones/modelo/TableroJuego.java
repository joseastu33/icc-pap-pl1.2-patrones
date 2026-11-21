package icc.pap.pl1.patrones.modelo;
/**
 * 
 * TableroJuego: espacio donde la SERPIENTE se moverá
 */
public class TableroJuego {
    private int filas;
    private int columnas;

    public TableroJuego(int filas, int columnas) { 
        if( !(filas > 0 && columnas > 0) ){ //tanto las FILAS como las COLUMNAS no pueden ser 0 o MENORES A CERO
            throw new IllegalArgumentException("Filas y Columnas deben ser mayores a 0");
        }
        this.filas = filas; //sale del if: flujo natural de ejecución
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
   
}
