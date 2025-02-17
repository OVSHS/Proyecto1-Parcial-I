/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public class AlmacenamientoMemoria implements Almacenamiento{
     private static final int MAX_USUARIOS = 100;
    private Usuario[] usuarios = new Usuario[MAX_USUARIOS];
    private int contadorUsuarios = 0;

    private Usuario usuarioActual; 

    private static final int MAX_LOGS = 100;
    private String[] logsPartidas = new String[MAX_LOGS];
    private int contadorLogs = 0;

    @Override
    public void anadirUsuario(Usuario user) {
        if (contadorUsuarios < MAX_USUARIOS) {
            usuarios[contadorUsuarios] = user;
            contadorUsuarios++;
        }
    }
    @Override
    public Usuario[] obtenerUsuarios() {
        return usuarios;
    }

    @Override
    public int obtenerCantidadUsuarios() {
        return contadorUsuarios;
    }

    @Override
    public void establecerCantidadUsuarios(int cantidad) {
        this.contadorUsuarios = cantidad;
    }

    @Override
    public Usuario obtenerUsuarioActual() {
        return usuarioActual;
    }

    @Override
    public void establecerUsuarioActual(Usuario user) {
        this.usuarioActual = user;
    }

    @Override
    public void anadirLog(String log) {
        if (contadorLogs < MAX_LOGS) {
            logsPartidas[contadorLogs++] = log;
        }
    }

    @Override
    public String[] obtenerLogs() {
        return logsPartidas;
    }

    @Override
    public int obtenerLogsCount() {
        return contadorLogs;
    }
}
