/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public class Caballo extends Pieza{
     public Caballo(Bando bando, Tablero tablero) {
        super(bando, tablero);
    }

    @Override
    public boolean MovimientoPiezas(int filaO, int colO, int filaD, int colD) {
        int deltaFila = Math.abs(filaD - filaO);
        int deltaCol = Math.abs(colD - colO);

        if ((deltaFila == 2 && deltaCol == 1) || (deltaFila == 1 && deltaCol == 2)) {
            int pivoteFila = filaO + (filaD - filaO) / 2;
            int pivoteCol = colO + (colD - colO) / 2;
            if (!tablero.hayPieza(pivoteFila, pivoteCol)) {
                return true;
            }
        }
        return false;
    }
}
