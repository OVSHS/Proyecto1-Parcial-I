/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public class General extends Pieza{
     public General(Bando bando, Tablero tablero) {
        super(bando, tablero);
    }

    @Override
    public boolean MovimientoPiezas(int filaO, int colO, int filaD, int colD) {
        int difFila = Math.abs(filaD - filaO);
        int difCol = Math.abs(colD - colO);
        if (!((difFila == 1 && difCol == 0) || (difFila == 0 && difCol == 1))) {
            return false;
        }
        if (bando == Bando.NEGRO) {
            if (filaD < 0 || filaD > 2 || colD < 3 || colD > 5) {
                return false;
            }
        } else {
            if (filaD < 7 || filaD > 9 || colD < 3 || colD > 5) {
                return false;
            }
        }
        return true;
    }
}
