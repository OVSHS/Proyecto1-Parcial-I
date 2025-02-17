/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Mario
 */
public class MenuPrincipal {

    private JFrame frame;
    private String usuarioActual;
    private Almacenamiento almacenamiento;

    public MenuPrincipal(String usuario, Almacenamiento almacenamiento) {
        this.usuarioActual = usuario;
        this.almacenamiento = almacenamiento;

        frame = new JFrame("Menu Principal");
        frame.setSize(676, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        ImageIcon imagenIcon = new ImageIcon("src/imagenes/imagen 3.jpg");
        JLabel backgroundLabel = new JLabel(imagenIcon);
        backgroundLabel.setBounds(0, 0, 676, 700);
        frame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        JLabel titleLabel = new JLabel("MENU PRINCIPAL - " + usuarioActual, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 30, 400, 40);
        backgroundLabel.add(titleLabel);

        // Boton para jugar
        JButton playButton = new JButton("JUGAR XIANGQI");
        playButton.setBounds(50, 100, 200, 50);
        playButton.setBackground(new Color(60, 63, 65));
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(playButton);

        // Boton de cuenta
        JButton accountButton = new JButton("Mi cuenta");
        accountButton.setBounds(50, 170, 200, 50);
        accountButton.setBackground(new Color(60, 63, 65));
        accountButton.setForeground(Color.WHITE);
        accountButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(accountButton);

        // Boton de reportes
        JButton reportsButton = new JButton("Reportes");
        reportsButton.setBounds(50, 240, 200, 50);
        reportsButton.setBackground(new Color(60, 63, 65));
        reportsButton.setForeground(Color.WHITE);
        reportsButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(reportsButton);

        // Boton logout
        JButton logoutButton = new JButton("Salir de la cuenta");
        logoutButton.setBounds(50, 310, 200, 50);
        logoutButton.setBackground(new Color(60, 63, 65));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(logoutButton);

        // Accion de JUGAR
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (almacenamiento.obtenerCantidadUsuarios() < 2) {
                    JOptionPane.showMessageDialog(frame, "Se requiere minimo 2 usuarios logueados para poder jugar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    Usuario[] usuarios = almacenamiento.obtenerUsuarios();
                    int totalUsuarios = almacenamiento.obtenerCantidadUsuarios();
                    int countOponentes = 0;
                    for (int i = 0; i < totalUsuarios; i++) {
                        if (usuarios[i] != null && !usuarios[i].getUsername().equalsIgnoreCase(usuarioActual)) {
                            countOponentes++;
                        }
                    }
                    if (countOponentes == 0) {
                        JOptionPane.showMessageDialog(frame, "No hay otros jugadores disponibles para seleccionar como oponente", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String[] opciones = new String[countOponentes];
                    int idx = 0;
                    for (int i = 0; i < totalUsuarios; i++) {
                        if (usuarios[i] != null && !usuarios[i].getUsername().equalsIgnoreCase(usuarioActual)) {
                            opciones[idx++] = usuarios[i].getUsername();
                        }
                    }
                    String oponenteSeleccionado = (String) JOptionPane.showInputDialog(frame, "Seleccione el oponente:", "Seleccion de oponente", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (oponenteSeleccionado == null) {
                        return;
                    }
                    Usuario usuarioOponente = null;
                    for (int i = 0; i < totalUsuarios; i++) {
                        if (usuarios[i] != null && usuarios[i].getUsername().equalsIgnoreCase(oponenteSeleccionado)) {
                            usuarioOponente = usuarios[i];
                            break;
                        }
                    }
                    Usuario usuarioPrincipal = buscarUsuario(usuarioActual);
                    if (usuarioPrincipal != null && usuarioOponente != null) {
                        new Tablero(usuarioPrincipal, usuarioOponente, almacenamiento, frame);
                        frame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No se encontro el usuario principal u oponente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // boton MI CUENTA
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Micuenta(usuarioActual, almacenamiento, frame);
                frame.setVisible(false);
            }
        });

        // boton REPORTES
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opciones = {"Ranking de Jugadores", "Log de mis ultimos partidos"};
                String seleccion = (String) JOptionPane.showInputDialog(
                        frame,
                        "Seleccione el reporte:",
                        "Reportes",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion == null) {
                    return;
                }
                frame.setVisible(false);

                if (seleccion.equals("Ranking de Jugadores")) {
                    new RankingJugadores(almacenamiento.obtenerUsuarios(),
                            almacenamiento.obtenerCantidadUsuarios(),
                            frame);
                } else {
                    Usuario actual = almacenamiento.obtenerUsuarioActual();
                    if (actual != null) {
                        new LogUltimosPartidos(actual.getUsername(), frame, almacenamiento);
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "No hay usuario actual para mostrar sus ultimos partidos",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        frame.setVisible(true);
                    }
                }
            }
        });

        // boton salir de sesion
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuInicio();
            }
        });

        frame.setVisible(true);
    }

    private Usuario buscarUsuario(String username) {
        Usuario[] arr = almacenamiento.obtenerUsuarios();
        int count = almacenamiento.obtenerCantidadUsuarios();
        for (int i = 0; i < count; i++) {
            if (arr[i] != null && arr[i].getUsername().equalsIgnoreCase(username)) {
                return arr[i];
            }
        }
        return null;
    }
}
