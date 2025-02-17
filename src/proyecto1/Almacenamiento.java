/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Mario
 */
public interface Almacenamiento {
     void anadirUsuario(Usuario user);             
    Usuario[] obtenerUsuarios();                  
    int obtenerCantidadUsuarios();                
    void establecerCantidadUsuarios(int cantidad);

   
    Usuario obtenerUsuarioActual();
    void establecerUsuarioActual(Usuario user);

  
    void anadirLog(String log);
    String[] obtenerLogs();
    int obtenerLogsCount();
}
