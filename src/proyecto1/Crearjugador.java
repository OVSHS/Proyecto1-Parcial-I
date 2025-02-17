/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Mario
 */
public class Crearjugador {

    private Almacenamiento almacenamiento;
    private JFrame frame;
    private String fechaRegistro;

    public Crearjugador(Almacenamiento almacenamiento) {
        this.almacenamiento = almacenamiento;

        frame = new JFrame("Crear Jugador");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JPanel fondoPanel = new JPanel();
        fondoPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        fondoPanel.setBackground(new Color(30, 30, 30)); 
        fondoPanel.setLayout(null);
        frame.setContentPane(fondoPanel);

       
        JPanel panelControles = new JPanel();
        panelControles.setBounds(30, 30, 340, 340);
        panelControles.setBackground(new Color(0, 0, 0, 150)); 
        panelControles.setLayout(null);
        fondoPanel.add(panelControles);

        JLabel userLabel = new JLabel("Nombre:");
        userLabel.setBounds(20, 30, 100, 30);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(130, 30, 180, 30);
        panelControles.add(userField);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(20, 80, 100, 30);
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(130, 80, 180, 30);
        panelControles.add(passField);

        JLabel dateLabel = new JLabel("Fecha:");
        dateLabel.setBounds(20, 130, 100, 30);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(130, 130, 180, 30);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(calendar.getTime());
        dateField.setText(fechaActual);
        dateField.setEditable(false);
        panelControles.add(dateField);

        fechaRegistro = fechaActual;

        JButton registerButton = new JButton("CREAR PLAYER");
        registerButton.setBounds(50, 200, 240, 50);
        registerButton.setBackground(new Color(60, 63, 65));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setFocusPainted(false);
        panelControles.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());
                if (existeUsuario(username)) {
                    JOptionPane.showMessageDialog(frame, "El usuario ya existe, intente con otro");
                } else if (password.length() != 5 || password.contains(" ")) {
                    JOptionPane.showMessageDialog(frame, "La contraseña debe tener EXACTAMENTE 5 caracteres");
                } else {
                    registrarUsuario(username, password);
                    JOptionPane.showMessageDialog(frame, "Registro exitoso");
                    frame.setVisible(false);
                    new MenuPrincipal(username, almacenamiento);
                }
            }
        });

        frame.setVisible(true);
    }

    private boolean existeUsuario(String username) {
        Usuario[] arr = almacenamiento.obtenerUsuarios();
        int count = almacenamiento.obtenerCantidadUsuarios();
        for (int i = 0; i < count; i++) {
            if (arr[i] != null && arr[i].getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    private void registrarUsuario(String username, String password) {
        Usuario nuevo = new Usuario(username, password, fechaRegistro);
        almacenamiento.anadirUsuario(nuevo);
        almacenamiento.establecerCantidadUsuarios(
                almacenamiento.obtenerCantidadUsuarios() + 1
        );
        almacenamiento.establecerUsuarioActual(nuevo);
    }
}
