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
public class LogUltimosPartidos extends JFrame{
 private JFrame menuFrame;
    private Almacenamiento almacenamiento;

    public LogUltimosPartidos(String username, JFrame menuFrame, Almacenamiento almacenamiento) {
        this.menuFrame = menuFrame;
        this.almacenamiento = almacenamiento;
        setTitle("Log de mis ultimas partidas");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(null);

        String[] logs = almacenamiento.obtenerLogs();
        int count = almacenamiento.obtenerLogsCount();
        StringBuilder sb = new StringBuilder();
        for (int i = count - 1; i >= 0; i--) {
            if (logs[i] != null && logs[i].toLowerCase().contains(username.toLowerCase())) {
                sb.append(logs[i]).append("\n");
            }
        }
        String info = sb.toString();
        if (info.isEmpty()) {
            info = "No hay registros de partidos para este usuario.";
        }
        JTextArea textArea = new JTextArea(info);
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
