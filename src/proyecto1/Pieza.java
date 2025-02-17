/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public abstract class Pieza {
     protected Bando bando;
    protected Tablero tablero; 

    public Pieza(Bando bando, Tablero tablero) {
        this.bando = bando;
        this.tablero = tablero;
    }

    public Bando getBando() {
        return bando;
    }

    
    public abstract boolean MovimientoPiezas(int filaO, int colO, int filaD, int colD);
}
