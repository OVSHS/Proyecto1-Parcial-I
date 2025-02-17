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
public class Cambiocontra {

    private JFrame frame;
    private Usuario usuario;

    public Cambiocontra(Usuario usuario) {
        this.usuario = usuario;

        frame = new JFrame("Cambiar Contraseña");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        // Fondo
        JPanel fondoPanel = new JPanel();
        fondoPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        fondoPanel.setBackground(new Color(30, 30, 30));
        fondoPanel.setLayout(null);
        frame.setContentPane(fondoPanel);

        // Panel 
        JPanel panelControles = new JPanel();
        panelControles.setBounds(20, 20, 360, 260);
        panelControles.setBackground(new Color(0, 0, 0, 150));
        panelControles.setLayout(null);
        fondoPanel.add(panelControles);

        JLabel actualLabel = new JLabel("Contraseña Actual:");
        actualLabel.setBounds(20, 20, 150, 30);
        actualLabel.setForeground(Color.WHITE);
        actualLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(actualLabel);

        JPasswordField actualField = new JPasswordField();
        actualField.setBounds(180, 20, 150, 30);
        panelControles.add(actualField);

        JLabel nuevaLabel = new JLabel("Nueva Contraseña:");
        nuevaLabel.setBounds(20, 70, 150, 30);
        nuevaLabel.setForeground(Color.WHITE);
        nuevaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(nuevaLabel);

        JPasswordField nuevaField = new JPasswordField();
        nuevaField.setBounds(180, 70, 150, 30);
        panelControles.add(nuevaField);

        JLabel confirmarLabel = new JLabel("Confirmar Nueva:");
        confirmarLabel.setBounds(20, 120, 150, 30);
        confirmarLabel.setForeground(Color.WHITE);
        confirmarLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(confirmarLabel);

        JPasswordField confirmarField = new JPasswordField();
        confirmarField.setBounds(180, 120, 150, 30);
        panelControles.add(confirmarField);

        JButton cambiarButton = new JButton("Cambiar");
        cambiarButton.setBounds(140, 180, 100, 30);
        cambiarButton.setBackground(new Color(60, 63, 65));
        cambiarButton.setForeground(Color.WHITE);
        cambiarButton.setFont(new Font("Arial", Font.BOLD, 14));
        cambiarButton.setFocusPainted(false);
        panelControles.add(cambiarButton);

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setBounds(260, 180, 100, 30);
        cerrarButton.setBackground(new Color(60, 63, 65));
        cerrarButton.setForeground(Color.WHITE);
        cerrarButton.setFont(new Font("Arial", Font.BOLD, 14));
        cerrarButton.setFocusPainted(false);
        panelControles.add(cerrarButton);

        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actual = new String(actualField.getPassword());
                String nueva = new String(nuevaField.getPassword());
                String confirmar = new String(confirmarField.getPassword());

                if (!actual.equals(usuario.getPassword())) {
                    JOptionPane.showMessageDialog(frame,
                            "La contraseña actual es incorrecta.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (nueva.length() < 5) {
                    JOptionPane.showMessageDialog(frame,
                            "La contraseña debe tener al menos 5 caracteres.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!nueva.equals(confirmar)) {
                    JOptionPane.showMessageDialog(frame,
                            "Las contraseñas no coinciden.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                usuario.setPassword(nueva);
                JOptionPane.showMessageDialog(frame,
                        "Contraseña actualizada con exito",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        });
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
