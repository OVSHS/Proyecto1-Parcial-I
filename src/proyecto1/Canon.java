/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public class Canon extends Pieza{
     public Canon(Bando bando, Tablero tablero) {
        super(bando, tablero);
    }

    @Override
    public boolean MovimientoPiezas(int filaO, int colO, int filaD, int colD) {
        if (filaO != filaD && colO != colD) {
            return false;
        }
        boolean hayPiezaDestino = tablero.hayPieza(filaD, colD);
        int piezasIntermedias = 0;
        if (filaO == filaD) {
            int start = Math.min(colO, colD) + 1;
            int end = Math.max(colO, colD) - 1;
            for (int c = start; c <= end; c++) {
                if (tablero.hayPieza(filaO, c)) {
                    piezasIntermedias++;
                }
            }
        } else {
            int start = Math.min(filaO, filaD) + 1;
            int end = Math.max(filaO, filaD) - 1;
            for (int f = start; f <= end; f++) {
                if (tablero.hayPieza(f, colO)) {
                    piezasIntermedias++;
                }
            }
        }
        if (hayPiezaDestino) {
            return (piezasIntermedias == 1);
        } else {
            return (piezasIntermedias == 0);
        }
    }
}
