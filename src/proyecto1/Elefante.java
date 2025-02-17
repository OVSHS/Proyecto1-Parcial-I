/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public class Elefante extends Pieza{
     public Elefante(Bando bando, Tablero tablero) {
        super(bando, tablero);
    }
 @Override
    public boolean MovimientoPiezas(int filaO, int colO, int filaD, int colD) {
        if (Math.abs(filaD - filaO) != 2 || Math.abs(colD - colO) != 2) {
            return false;
        }
        if (bando == Bando.NEGRO && filaD >= 5) {
            return false;
        }
        if (bando == Bando.ROJO && filaD <= 4) {
            return false;
        }
        int midFila = (filaO + filaD) / 2;
        int midCol = (colO + colD) / 2;
        if (tablero.hayPieza(midFila, midCol)) {
            return false;
        }
        return true;
    }
}
