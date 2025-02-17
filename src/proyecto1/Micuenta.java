/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mario
 */
public class Micuenta {


   private JFrame frame;
    private Almacenamiento almacenamiento; 
    private String username;
    private Usuario usuarioEncontrado;      
    private int userIndex = -1;
    private JFrame menuFrame;

    // Constructor 
    public Micuenta(String username, Almacenamiento almacenamiento,JFrame menuFrame) {
        this.menuFrame = menuFrame;
        this.username = username;
        this.almacenamiento = almacenamiento;

        // Buscar al usuario 
        Usuario[] users = almacenamiento.obtenerUsuarios();
        int userCount = almacenamiento.obtenerCantidadUsuarios();

        for (int i = 0; i < userCount; i++) {
            if (users[i] != null
                && users[i].isActivo()
                && users[i].getUsername().equalsIgnoreCase(username)) {
                usuarioEncontrado = users[i];
                userIndex = i; 
                break;
            }
        }

        // Validar si se encontro
        if (usuarioEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontro el usuario en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        // Ventana principal
        frame = new JFrame("Mi Cuenta");
        frame.setSize(676, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        ImageIcon imagenIcon = new ImageIcon("src/imagenes/imagencuenta.jpg");
        JLabel backgroundLabel = new JLabel(imagenIcon);
        backgroundLabel.setBounds(0, 0, 676, 700);
        frame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        
        JLabel titleLabel = new JLabel("MI CUENTA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(100, 20, 300, 30);
        frame.add(titleLabel);

        // Boton: Ver mi Informacion
        JButton infoButton = new JButton("Ver mi Información");
        infoButton.setBounds(50, 80, 180, 40);
        infoButton.setBackground(new Color(60, 60, 60));
        infoButton.setForeground(Color.WHITE);
        infoButton.setFont(new Font("Arial", Font.BOLD, 14));
        infoButton.setFocusPainted(false);
        infoButton.setBorderPainted(false);
        infoButton.setOpaque(true);
        frame.add(infoButton);

        // Boton: Cambiar Contraseña
        JButton changePassButton = new JButton("Cambiar Contraseña");
        changePassButton.setBounds(50, 140, 180, 40);
        changePassButton.setBackground(new Color(60, 60, 60));
        changePassButton.setForeground(Color.WHITE);
        changePassButton.setFont(new Font("Arial", Font.BOLD, 14));
        changePassButton.setFocusPainted(false);
        changePassButton.setBorderPainted(false);
        changePassButton.setOpaque(true);
        frame.add(changePassButton);

        // Boton: Eliminar Cuenta
        JButton deleteButton = new JButton("Eliminar mi Cuenta");
        deleteButton.setBounds(50, 200, 180, 40);
        deleteButton.setBackground(new Color(60, 60, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setOpaque(true);
        frame.add(deleteButton);

        // Boton: Cerrar
        JButton closeButton = new JButton("Cerrar");
        closeButton.setBounds(50, 260, 180, 40);
        closeButton.setBackground(new Color(60, 60, 60));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setOpaque(true);
        frame.add(closeButton);

        // Acciones
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VermiInformacion(usuarioEncontrado);
            }
        });

        changePassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                new Cambiocontra(usuarioEncontrado);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario[] users = almacenamiento.obtenerUsuarios();
                new Eliminarcuenta(usuarioEncontrado, users, userIndex, frame);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menuFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}

