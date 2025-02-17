/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Mario
 */
public class RankingJugadores extends JFrame{
    private JFrame menuFrame;

    public RankingJugadores(Usuario[] usuarios, int userCount, JFrame menuFrame) {
        this.menuFrame = menuFrame;

         
        setTitle("Ranking de Jugadores");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(null);

        int activosCount = 0;
        for (int i = 0; i < userCount; i++) {
            if (usuarios[i] != null && usuarios[i].isActivo()) {
                activosCount++;
            }
        }
        Usuario[] activos = new Usuario[activosCount];
        int idx = 0;
        for (int i = 0; i < userCount; i++) {
            if (usuarios[i] != null && usuarios[i].isActivo()) {
                activos[idx++] = usuarios[i];
            }
        }
        for (int i = 0; i < activos.length - 1; i++) {
            for (int j = 0; j < activos.length - i - 1; j++) {
                if (activos[j].getPuntos() < activos[j + 1].getPuntos()) {
                    Usuario temp = activos[j];
                    activos[j] = activos[j + 1];
                    activos[j + 1] = temp;
                }
            }
        }
        StringBuilder info = new StringBuilder("Posicion - Nombre - Puntos\n\n");
        for (int i = 0; i < activos.length; i++) {
            info.append((i + 1) + " - " + activos[i].getUsername() + " - " + activos[i].getPuntos() + "\n");
        }
        JTextArea textArea = new JTextArea(info.toString());
        textArea.setFont(new Font("Arial", Font.BOLD, 14));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setHighlighter(null);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(20, 20, 360, 380);
        add(scroll);

        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(20, 410, 150, 30);
        volverButton.setBackground(new Color(60, 63, 65));
        volverButton.setForeground(Color.WHITE);
        volverButton.setFont(new Font("Arial", Font.BOLD, 14));
        volverButton.setFocusPainted(false);
        add(volverButton);

        volverButton.addActionListener(e -> {
            setVisible(false);
            menuFrame.setVisible(true);
        });

        setVisible(true);
    }
}
