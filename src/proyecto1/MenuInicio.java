/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Mario
 */
public class MenuInicio {

    private static Almacenamiento almacenamiento = new AlmacenamientoMemoria();

    public static Almacenamiento getAlmacenamiento() {
        return almacenamiento;

    }

    public MenuInicio() {

        JFrame frame = new JFrame("Menu de Inicio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(676, 700);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Imagen de fondo
        ImageIcon imagenIcon = new ImageIcon("src/imagenes/imagen 3.jpg");
        JLabel backgroundLabel = new JLabel(imagenIcon);
        backgroundLabel.setBounds(0, 0, 676, 700);
        frame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Titulo
        JLabel titleLabel = new JLabel("MENU INICIO", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); 
        titleLabel.setBounds(50, 30, 276, 40);
        backgroundLabel.add(titleLabel);

        // Boton LOGIN
        JButton loginButton = new JButton("INICIAR SESION");
        loginButton.setBounds(50, 100, 200, 50);
        loginButton.setBackground(new Color(60, 63, 65));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(loginButton);

        // Boton CREAR JUGADOR
        JButton createPlayerButton = new JButton("CREAR JUGADOR");
        createPlayerButton.setBounds(50, 170, 200, 50);
        createPlayerButton.setBackground(new Color(60, 63, 65));
        createPlayerButton.setForeground(Color.WHITE);
        createPlayerButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(createPlayerButton);

        // Boton SALIR
        JButton exitButton = new JButton("SALIR");
        exitButton.setBounds(50, 240, 200, 50);
        exitButton.setBackground(new Color(60, 63, 65));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundLabel.add(exitButton);

        // Accion INICIO SESION
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(almacenamiento);
                frame.setVisible(false);
            }
        });

        // Accion CREAR JUGADOR
        createPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Crearjugador(almacenamiento);
                frame.setVisible(false);
            }
        });

        // Accion SALIR
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);

    }
}
