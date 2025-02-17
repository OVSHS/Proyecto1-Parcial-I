/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public class Carro extends Pieza{
    public Carro(Bando bando, Tablero tablero) {
        super(bando, tablero);
    }

    @Override
    public boolean MovimientoPiezas(int filaO, int colO, int filaD, int colD) {
        if (filaO != filaD && colO != colD) {
            return false;
        }
        if (filaO == filaD) {
            int startCol = Math.min(colO, colD) + 1;
            int endCol = Math.max(colO, colD) - 1;
            for (int c = startCol; c <= endCol; c++) {
                if (tablero.hayPieza(filaO, c)) {
                    return false;
                }
            }
        } else {
            int startRow = Math.min(filaO, filaD) + 1;
            int endRow = Math.max(filaO, filaD) - 1;
            for (int r = startRow; r <= endRow; r++) {
                if (tablero.hayPieza(r, colO)) {
                    return false;
                }
            }
        }
        return true;
    } 
}
