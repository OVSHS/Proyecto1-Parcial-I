/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Mario
 */
public class VermiInformacion {
      private JFrame frame;
    private Usuario usuario;

    public VermiInformacion(Usuario usuario) {
         this.usuario = usuario;

        frame = new JFrame("Informacion");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        String info = "Usuario: " + usuario.getUsername() + "\n"
                + "Puntos: " + usuario.getPuntos() + "\n"
                + "Activo: " + (usuario.isActivo() ? "Si" : "No") + "\n"
                + "Fecha de registro: " + usuario.getFechaRegistro();

        JTextArea textArea = new JTextArea(info);
        textArea.setBounds(20, 20, 350, 200);
        textArea.setFont(new Font("Arial", Font.BOLD, 14));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setHighlighter(null);
        frame.add(textArea);

        JButton closeButton = new JButton("Cerrar");
        closeButton.setBounds(20, 230, 150, 30);
        closeButton.setBackground(new Color(60, 63, 65));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        frame.add(closeButton);

        closeButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    
    }
}
