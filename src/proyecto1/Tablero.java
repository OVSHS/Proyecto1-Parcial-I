/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Mario
 */
public class Tablero extends JFrame {

    private static final int FILAS = 10;
    private static final int COLUMNAS = 9;

    private final JButton[][] botonesTablero = new JButton[FILAS][COLUMNAS];
    private final Pieza[][] piezasTablero = new Pieza[FILAS][COLUMNAS];
    private final String rutaImagenes = "/ImagenesTablero/";

    private final Usuario jugadorLogueado;
    private final Usuario oponente;
    private Almacenamiento almacenamiento;
    private JFrame menuFrame;

    private Bando turnoActual = Bando.ROJO;
    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
    private JLabel turnoLabel;
    private JPanel capturasNegro;
    private JPanel capturasRojo;
    private StringBuilder movimientosNegro = new StringBuilder();
    private StringBuilder movimientosRojo = new StringBuilder();
    private JTextArea logNegroTextArea;
    private JTextArea logRojoTextArea;

    // Constructor
    public Tablero(Usuario jugadorLogueado, Usuario oponente, Almacenamiento almacenamiento, JFrame menuFrame) {
        this.jugadorLogueado = jugadorLogueado;
        this.oponente = oponente;
        this.almacenamiento = almacenamiento;
        this.menuFrame = menuFrame;
        configurarVentana();
        inicializarComponentes();
        setVisible(true);
    }
    // Configura las propiedades principales de la ventana del tablero

    private void configurarVentana() {
        setTitle("Tablero - Xiangqi");
        setSize(1200, 1400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
    }
    // Inicializa y organiza los componentes del tablero 

    private void inicializarComponentes() {
        turnoLabel = new JLabel("Turno: " + (turnoActual == Bando.ROJO ? "ROJO: " + jugadorLogueado.getUsername() : "NEGRO: " + oponente.getUsername()), SwingConstants.CENTER);
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel panelTurno = new JPanel();
        panelTurno.setBackground(Color.DARK_GRAY);
        turnoLabel.setForeground(Color.WHITE);
        panelTurno.add(turnoLabel);
        add(panelTurno, BorderLayout.NORTH);

        JPanel panelLogs = new JPanel();
        panelLogs.setLayout(new BoxLayout(panelLogs, BoxLayout.Y_AXIS));
        JLabel negroLabel = new JLabel("Movimientos Negros:");
        negroLabel.setForeground(Color.BLACK);
        negroLabel.setFont(new Font("Arial", Font.BOLD, 14));
        logNegroTextArea = new JTextArea();
        logNegroTextArea.setEditable(false);
        logNegroTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        logNegroTextArea.setForeground(Color.WHITE);
        logNegroTextArea.setBackground(new Color(30, 30, 30));
        JScrollPane movimientonegro = new JScrollPane(logNegroTextArea);
        movimientonegro.setPreferredSize(new Dimension(300, 150));
        JLabel rojoLabel = new JLabel("Movimientos Rojas:");
        rojoLabel.setForeground(Color.BLACK);
        rojoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        logRojoTextArea = new JTextArea();
        logRojoTextArea.setEditable(false);
        logRojoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        logRojoTextArea.setForeground(Color.WHITE);
        logRojoTextArea.setBackground(new Color(30, 30, 30));
        JScrollPane movimientorojo = new JScrollPane(logRojoTextArea);
        movimientorojo.setPreferredSize(new Dimension(300, 150));
        panelLogs.add(negroLabel);
        panelLogs.add(movimientonegro);
        panelLogs.add(rojoLabel);
        panelLogs.add(movimientorojo);
        add(panelLogs, BorderLayout.WEST);

        JPanel panelSuperior = new JPanel(new GridLayout(5, COLUMNAS, 1, 1));
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                botonesTablero[fila][columna] = new JButton();
                botonesTablero[fila][columna].setOpaque(true);
                botonesTablero[fila][columna].setBorderPainted(false);
                configurarColorCasilla(fila, columna);
                asignarPiezas(fila, columna);
                final int f = fila;
                final int c = columna;
                botonesTablero[fila][columna].addActionListener(e -> manejarClick(f, c));
                panelSuperior.add(botonesTablero[fila][columna]);
            }
        }

        JPanel panelRio = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton btnRio = new JButton();
        btnRio.setBackground(Color.BLUE);
        btnRio.setEnabled(false);
        btnRio.setPreferredSize(new Dimension(1080, 30));
        panelRio.add(btnRio);

        JPanel panelInferior = new JPanel(new GridLayout(5, COLUMNAS, 1, 1));
        for (int fila = 5; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                botonesTablero[fila][columna] = new JButton();
                botonesTablero[fila][columna].setOpaque(true);
                botonesTablero[fila][columna].setBorderPainted(false);
                configurarColorCasilla(fila, columna);
                asignarPiezas(fila, columna);
                final int f = fila;
                final int c = columna;
                botonesTablero[fila][columna].addActionListener(e -> manejarClick(f, c));
                panelInferior.add(botonesTablero[fila][columna]);
            }
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panelSuperior);
        mainPanel.add(panelRio);
        mainPanel.add(panelInferior);
        add(mainPanel, BorderLayout.CENTER);

        JButton abandonarButton = new JButton("Abandonar");
        abandonarButton.setBackground(new Color(150, 0, 0));
        abandonarButton.setForeground(Color.WHITE);
        abandonarButton.setFont(new Font("Arial", Font.BOLD, 16));
        abandonarButton.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Deseas abandonar la partida?", "Confirmar Abandono", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                abandonarPartida();
            }
        });
        JPanel panelOpcion = new JPanel();
        panelOpcion.setBackground(Color.LIGHT_GRAY);
        panelOpcion.setLayout(new GridBagLayout());
        panelOpcion.add(abandonarButton);
        add(panelOpcion, BorderLayout.EAST);

        JPanel panelCapturas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCapturas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCapturas.setBackground(Color.GRAY);

        capturasNegro = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        capturasNegro.setBackground(Color.WHITE);
        capturasNegro.setBorder(new TitledBorder("Capturas NEGRO"));

        capturasRojo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        capturasRojo.setBackground(Color.WHITE);
        capturasRojo.setBorder(new TitledBorder("Capturas ROJO"));

        JScrollPane scrollNegro = new JScrollPane(capturasNegro);
        scrollNegro.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollNegro.setPreferredSize(new Dimension(50, 150));

        JScrollPane scrollRojo = new JScrollPane(capturasRojo);
        scrollRojo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollRojo.setPreferredSize(new Dimension(50, 150));

        panelCapturas.add(scrollNegro);
        panelCapturas.add(scrollRojo);
        add(panelCapturas, BorderLayout.SOUTH);
    }
    // Configura el color de cada casilla

    private void configurarColorCasilla(int fila, int columna) {
        if (((fila >= 0 && fila <= 2) && (columna >= 3 && columna <= 5))
                || ((fila >= 7 && fila <= 9) && (columna >= 3 && columna <= 5))) {
            botonesTablero[fila][columna].setBackground(new Color(218, 165, 32));
        } else {
            if ((fila + columna) % 2 == 0) {
                botonesTablero[fila][columna].setBackground(new Color(245, 245, 220));
            } else {
                botonesTablero[fila][columna].setBackground(new Color(0, 100, 0));
            }
        }
    }

    // Maneja el clic en una casilla, seleccionando o moviendo una pieza
    private void manejarClick(int fila, int columna) {
        if (filaSeleccionada == -1 && columnaSeleccionada == -1) {
            if (piezasTablero[fila][columna] != null && piezasTablero[fila][columna].getBando() == turnoActual) {
                filaSeleccionada = fila;
                columnaSeleccionada = columna;
                resaltarMovimientos(fila, columna);
            }
        } else {
            moverPieza(filaSeleccionada, columnaSeleccionada, fila, columna);
            restaurarColoresTablero();
            filaSeleccionada = -1;
            columnaSeleccionada = -1;
        }
    }
    // Resalta las casillas a las que puede mover la pieza seleccionada

    private void resaltarMovimientos(int filaOrigen, int colOrigen) {
        Pieza pieza = piezasTablero[filaOrigen][colOrigen];
        if (pieza != null) {
            for (int fila = 0; fila < FILAS; fila++) {
                for (int columna = 0; columna < COLUMNAS; columna++) {
                    if (pieza.MovimientoPiezas(filaOrigen, colOrigen, fila, columna)
                            && (piezasTablero[fila][columna] == null || piezasTablero[fila][columna].getBando() != pieza.getBando())) {
                        botonesTablero[fila][columna].setBackground(Color.RED);
                    }
                }
            }
        }
    }
    // Restaura el color original de todas las casillas

    private void restaurarColoresTablero() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                configurarColorCasilla(f, c);
            }
        }
    }

    // Asigna las piezas iniciales a las casillas correspondientes
    private void asignarPiezas(int fila, int columna) {
        if (fila == 0 || fila == 9) {
            if (columna == 0 || columna == 8) {
                piezasTablero[fila][columna] = new Carro(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 1 || columna == 7) {
                piezasTablero[fila][columna] = new Caballo(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 2 || columna == 6) {
                piezasTablero[fila][columna] = new Elefante(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 3 || columna == 5) {
                piezasTablero[fila][columna] = new Oficial(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 4) {
                piezasTablero[fila][columna] = new General(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            }
        }
        if ((fila == 2 && (columna == 1 || columna == 7))
                || (fila == 7 && (columna == 1 || columna == 7))) {
            piezasTablero[fila][columna] = new Canon(fila == 2 ? Bando.NEGRO : Bando.ROJO, this);
        }
        if ((fila == 3 && columna % 2 == 0)
                || (fila == 6 && columna % 2 == 0)) {
            piezasTablero[fila][columna] = new Soldado(fila == 3 ? Bando.NEGRO : Bando.ROJO, this);
        }
        if (piezasTablero[fila][columna] != null) {
            String imagen = obtenerImagenPieza(piezasTablero[fila][columna]);
            botonesTablero[fila][columna].setIcon(new ImageIcon(getClass().getResource(rutaImagenes + imagen)));
        }
    }

    // Devuelve el nombre de la imagen asociada a la pieza
    private String obtenerImagenPieza(Pieza pieza) {
        if (pieza instanceof Carro) {
            return pieza.getBando() == Bando.ROJO ? "CarroRojo.png" : "CarroNegro.png";
        }
        if (pieza instanceof Caballo) {
            return pieza.getBando() == Bando.ROJO ? "CaballoRojo.png" : "CaballoNegro.png";
        }
        if (pieza instanceof Elefante) {
            return pieza.getBando() == Bando.ROJO ? "ElefanteRojo.png" : "ElefanteNegro.png";
        }
        if (pieza instanceof Oficial) {
            return pieza.getBando() == Bando.ROJO ? "OficialRojo.png" : "OficialNegro.png";
        }
        if (pieza instanceof Canon) {
            return pieza.getBando() == Bando.ROJO ? "CañonRojo.png" : "CañonNegro.png";
        }
        if (pieza instanceof Soldado) {
            return pieza.getBando() == Bando.ROJO ? "SoldadoRojo.png" : "SoldadoNegro.png";
        }
        if (pieza instanceof General) {
            return pieza.getBando() == Bando.ROJO ? "ReyRojo.png" : "ReyNegro.png";
        }
        return "";
    }

    // Indica si hay una pieza en la casilla especificada
    public boolean hayPieza(int fila, int columna) {
        return piezasTablero[fila][columna] != null;
    }

    // Retorna la pieza en la casilla indicada, o null si no hay ninguna
    public Pieza obtenerPieza(int fila, int columna) {
        if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS) {
            return piezasTablero[fila][columna];
        }
        return null;
    }
    // Realiza el movimiento de una pieza y actualiza el estado del tablero

    public void moverPieza(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        Pieza pieza = piezasTablero[filaOrigen][colOrigen];
        Pieza piezaCapturada = piezasTablero[filaDestino][colDestino];
        if (piezaCapturada != null && piezaCapturada.getBando() == pieza.getBando()) {
            return;
        }
        if (pieza != null && pieza.MovimientoPiezas(filaOrigen, colOrigen, filaDestino, colDestino)) {
            piezasTablero[filaDestino][colDestino] = pieza;
            piezasTablero[filaOrigen][colOrigen] = null;
            if (piezaCapturada instanceof General) {
                anunciarVictoria();
                return;
            }
            if (!reyesNoPuedenVerse()) {
                piezasTablero[filaOrigen][colOrigen] = pieza;
                piezasTablero[filaDestino][colDestino] = piezaCapturada;
                JOptionPane.showMessageDialog(this,
                        "Movimiento invalido: los generales no pueden verse en la misma columna sin otra pieza intermedia",
                        "Movimiento no permitido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (piezaCapturada != null) {
                mostrarPiezaCapturada(piezaCapturada);
            }
            String imagen = obtenerImagenPieza(pieza);
            botonesTablero[filaDestino][colDestino].setIcon(new ImageIcon(getClass().getResource(rutaImagenes + imagen)));
            botonesTablero[filaOrigen][colOrigen].setIcon(null);

            String logMovimiento = "Movimiento: " + pieza.getClass().getSimpleName() + " de ("
                    + filaOrigen + ", " + colOrigen + ") a (" + filaDestino + ", " + colDestino + ")";
            if (pieza.getBando() == Bando.NEGRO) {
                movimientosNegro.append(logMovimiento).append("\n");
            } else {
                movimientosRojo.append(logMovimiento).append("\n");
            }
            movimientospiezass();

            turnoActual = (turnoActual == Bando.ROJO) ? Bando.NEGRO : Bando.ROJO;
            turnoLabel.setOpaque(true);
            if (turnoActual == Bando.ROJO) {
                turnoLabel.setText("Turno: ROJO - " + jugadorLogueado.getUsername());
                turnoLabel.setBackground(new Color(255, 102, 102));
                turnoLabel.setForeground(Color.WHITE);
            } else {
                turnoLabel.setText("Turno: NEGRO - " + oponente.getUsername());
                turnoLabel.setBackground(Color.BLACK);
                turnoLabel.setForeground(Color.WHITE);
            }
        }
    }

    private void movimientospiezass() {
        logNegroTextArea.setText(movimientosNegro.toString());
        logRojoTextArea.setText(movimientosRojo.toString());
    }

    // Verifica que los generales no se vean directamente
    public boolean reyesNoPuedenVerse() {
        int filaReyRojo = -1, colReyRojo = -1;
        int filaReyNegro = -1, colReyNegro = -1;
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                Pieza p = piezasTablero[f][c];
                if (p instanceof General) {
                    if (p.getBando() == Bando.ROJO) {
                        filaReyRojo = f;
                        colReyRojo = c;
                    } else {
                        filaReyNegro = f;
                        colReyNegro = c;
                    }
                }
            }
        }
        if (colReyRojo == colReyNegro && colReyRojo != -1) {
            int minFila = Math.min(filaReyRojo, filaReyNegro);
            int maxFila = Math.max(filaReyRojo, filaReyNegro);
            for (int f = minFila + 1; f < maxFila; f++) {
                if (piezasTablero[f][colReyRojo] != null) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    // Muestra la imagen de la pieza capturada en el panel correspondiente

    private void mostrarPiezaCapturada(Pieza piezaCapturada) {
        String imagenPieza = obtenerImagenPieza(piezaCapturada);
        JLabel piezaIcono = new JLabel(new ImageIcon(getClass().getResource(rutaImagenes + imagenPieza)));
        if (piezaCapturada.getBando() == Bando.NEGRO) {
            capturasNegro.add(piezaIcono);
        } else {
            capturasRojo.add(piezaIcono);
        }
        capturasNegro.revalidate();
        capturasNegro.repaint();
        capturasRojo.revalidate();
        capturasRojo.repaint();
    }
    // Anuncia la victoria y actualiza el estado al finalizar la partida

    private void anunciarVictoria() {
        Usuario userGanador = (turnoActual == Bando.NEGRO) ? oponente : jugadorLogueado;
        userGanador.setPuntos(userGanador.getPuntos() + 3);
        almacenamiento.anadirLog("Ganador: " + userGanador.getUsername() + " (Victoria por Jaque Mate) contra "
                + ((userGanador == jugadorLogueado) ? oponente.getUsername() : jugadorLogueado.getUsername()));
        JOptionPane.showMessageDialog(this, "¡" + userGanador.getUsername() + " HA GANADO LA PARTIDA (+3 PUNTOS) ",
                "JAQUE MATE", JOptionPane.INFORMATION_MESSAGE);
        almacenamiento.establecerUsuarioActual(userGanador);
        dispose();
        menuFrame.setVisible(true);
    }
    // Maneja la accion de abandonar la partida, otorgando puntos al ganador

    private void abandonarPartida() {
        Usuario userGanador = (turnoActual == Bando.ROJO) ? oponente : jugadorLogueado;
        userGanador.setPuntos(userGanador.getPuntos() + 3);
        almacenamiento.anadirLog("El jugador " + (turnoActual == Bando.ROJO ? jugadorLogueado.getUsername() : oponente.getUsername())
                + " abandono. Ganador: " + userGanador.getUsername());
        JOptionPane.showMessageDialog(this, "El jugador " + (turnoActual == Bando.ROJO ? jugadorLogueado.getUsername() : oponente.getUsername())
                + " ha abandonado la partida.\nFelicidades " + userGanador.getUsername() + ", +3 puntos",
                "Partida Abandonada", JOptionPane.INFORMATION_MESSAGE);
        almacenamiento.establecerUsuarioActual(userGanador);
        dispose();
        menuFrame.setVisible(true);
    }
}
