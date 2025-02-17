/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public class Soldado extends Pieza{
    public Soldado(Bando bando, Tablero tablero) {
        super(bando, tablero);
    }

    @Override
    public boolean MovimientoPiezas(int filaO, int colO, int filaD, int colD) {
        if (bando == Bando.ROJO) {
            if (filaO > 4) {
                if (filaD == filaO - 1 && colD == colO) {
                    return true;
                }
                return false;
            } else {
                int dFila = filaD - filaO;
                int dCol = colD - colO;
                if ((dFila == -1 && dCol == 0) || (dFila == 0 && Math.abs(dCol) == 1)) {
                    return true;
                }
                return false;
            }
        } else {
            if (filaO < 5) {
                if (filaD == filaO + 1 && colD == colO) {
                    return true;
                }
                return false;
            } else {
                int dFila = filaD - filaO;
                int dCol = colD - colO;
                if ((dFila == 1 && dCol == 0) || (dFila == 0 && Math.abs(dCol) == 1)) {
                    return true;
                }
                return false;
            }
        }
    }
}
