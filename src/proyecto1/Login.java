/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Mario
 */
public class Login {

    private Almacenamiento almacenamiento;
    private JFrame frame;

    public Login(Almacenamiento almacenamiento) {
        this.almacenamiento = almacenamiento;

        frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        
        JPanel fondoPanel = new JPanel();
        fondoPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        fondoPanel.setBackground(new Color(30, 30, 30)); 
        fondoPanel.setLayout(null);
        frame.setContentPane(fondoPanel);

      
        JPanel panelControles = new JPanel();
        panelControles.setBounds(30, 30, 340, 240);
        panelControles.setBackground(new Color(0, 0, 0, 150)); 
        panelControles.setLayout(null);
        fondoPanel.add(panelControles);

        
       JLabel userLabel = new JLabel("Nombre:");
        userLabel.setBounds(20, 20, 100, 30);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(130, 20, 180, 30);
        panelControles.add(userField);
        
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(20, 70, 100, 30);
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(130, 70, 180, 30);
        panelControles.add(passField);

        JButton loginButton = new JButton("Iniciar Sesion");
        loginButton.setBounds(50, 160, 200, 50);
        loginButton.setBackground(new Color(60, 63, 65));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        frame.add(loginButton);

        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(260, 160, 100, 50);
        volverButton.setBackground(new Color(60, 63, 65));
        volverButton.setForeground(Color.WHITE);
        volverButton.setFont(new Font("Arial", Font.BOLD, 14));
        volverButton.setFocusPainted(false);
        frame.add(volverButton);

        // Accion del boton inicio sesion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());

                Usuario[] usuarios = almacenamiento.obtenerUsuarios();
                int count = almacenamiento.obtenerCantidadUsuarios();
                if (validarUsuarios(username, password, usuarios, 0, count)) {
                    JOptionPane.showMessageDialog(frame, "Login exitoso.");
                    Usuario usr = obtenerUsuarioPorUsername(username, usuarios, 0, count);
                    if (usr != null) {
                        almacenamiento.establecerUsuarioActual(usr);
                    }
                    frame.setVisible(false);
                    new MenuPrincipal(username, almacenamiento);
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos.");
                }
            }
        });

        // Boton Volver
        volverButton.addActionListener((ActionEvent e) -> {
            frame.setVisible(false);
            new MenuInicio();
        });

        frame.setVisible(true);
    }

    private boolean validarUsuarios(String username, String password, Usuario[] usuarios, int index, int count) {
        if (index >= count) {
            return false;
        }
        if (usuarios[index] != null
                && usuarios[index].getUsername().equalsIgnoreCase(username)
                && usuarios[index].getPassword().equals(password)) {
            return true;
        }
        return validarUsuarios(username, password, usuarios, index + 1, count);
    }

    private Usuario obtenerUsuarioPorUsername(String username, Usuario[] usuarios, int index, int count) {
        if (index >= count) {
            return null;
        }
        if (usuarios[index] != null
                && usuarios[index].getUsername().equalsIgnoreCase(username)) {
            return usuarios[index];
        }
        return obtenerUsuarioPorUsername(username, usuarios, index + 1, count);
    }
}
