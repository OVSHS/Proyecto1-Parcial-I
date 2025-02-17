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
public class Eliminarcuenta {
    private JFrame frame;
    private Usuario usuario;
    private Usuario[] users;
    private int userIndex;
    private JFrame parentFrame;

    public Eliminarcuenta(Usuario usuario, Usuario[] usersRef, int userIndex, JFrame parentFrame) {
        this.usuario = usuario;
        this.users = usersRef;
        this.userIndex = userIndex;
        this.parentFrame = parentFrame;

          frame = new JFrame("Eliminar Cuenta");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        
        JPanel fondoPanel = new JPanel();
        fondoPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        fondoPanel.setBackground(new Color(30, 30, 30));
        fondoPanel.setLayout(null);
        frame.setContentPane(fondoPanel);

       
        JPanel panelControles = new JPanel();
        panelControles.setBounds(20, 20, 360, 160);
        panelControles.setBackground(new Color(0, 0, 0, 150));
        panelControles.setLayout(null);
        fondoPanel.add(panelControles);

        JLabel label = new JLabel("Ingrese su contraseña actual para confirmar:");
        label.setBounds(20, 20, 320, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panelControles.add(label);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(20, 60, 200, 30);
        panelControles.add(passField);

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setBounds(240, 60, 120, 30);
        eliminarBtn.setBackground(new Color(60, 63, 65));
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        eliminarBtn.setFocusPainted(false);
        panelControles.add(eliminarBtn);
        
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Esta seguro de eliminar su cuenta?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                String actual = new String(passField.getPassword());
                if (!actual.equals(usuario.getPassword())) {
                    JOptionPane.showMessageDialog(frame,
                            "Contraseña incorrecta,no se elimino la cuenta.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Elimina la cuenta
                users[userIndex] = null;

                JOptionPane.showMessageDialog(frame,
                        "Cuenta eliminada, volviendo a MenuInicio",
                        "Cuenta Eliminada", 
                        JOptionPane.INFORMATION_MESSAGE);

                frame.dispose();
                if (parentFrame != null) {
                    parentFrame.dispose();
                }
                new MenuInicio(); 
            }
        });

        frame.setVisible(true);
    }
}
