package TicTacToe.TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Cursor;

public class TresEnRaya extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton[][] tablero;
	private String turno = "X";
	
	JButton botonNuevaPartida;
    JLabel etiqueta1;
    JLabel etiqueta2;
    JLabel etiqueta3;
    JTextField campoTexto1;
    
    JLabel etiqueta5;
    JLabel etiqueta6;
    JTextField campoTexto2;
    JLabel etiqueta7;
    JRadioButton radioHumano2;
    JRadioButton radioCPU2;
    private JSeparator separator;
    private JSeparator separator_1;
    private JSeparator separator_2;
    private JEditorPane editorPane;
    
	public TresEnRaya() {
		setTitle("TicTacToe");
        setBounds(400,300,600,600);
        
        // Panel lateral
        JPanel panelLateral = new JPanel();
        panelLateral.setBackground(new Color(95, 158, 160));
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        
        botonNuevaPartida = new JButton("Nueva partida");
        botonNuevaPartida.setLocation(new Point(120, 300));
        botonNuevaPartida.setMaximumSize(new Dimension(120, 23));
        botonNuevaPartida.setForeground(new Color(95, 158, 160));
        botonNuevaPartida.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        etiqueta1 = new JLabel("ESTO ES EL TE");
        etiqueta1.setForeground(new Color(224, 255, 255));
        etiqueta1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        etiqueta2 = new JLabel("Jugador 1");
        etiqueta2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        etiqueta2.setForeground(new Color(255, 255, 255));
        etiqueta2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        etiqueta3 = new JLabel("Nombre");
        etiqueta3.setForeground(new Color(255, 255, 255));
        etiqueta3.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 11));
        etiqueta5 = new JLabel("Jugador 2");
        etiqueta5.setAlignmentX(Component.RIGHT_ALIGNMENT);
        etiqueta5.setForeground(new Color(240, 255, 255));
        etiqueta5.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        etiqueta6 = new JLabel("Nombre");
        etiqueta6.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 11));
        etiqueta6.setForeground(new Color(255, 255, 255));
        campoTexto2 = new JTextField();
        campoTexto2.setMaximumSize(new Dimension(400, 150));
        etiqueta7 = new JLabel("Tipo");
        etiqueta7.setAlignmentX(Component.RIGHT_ALIGNMENT);
        etiqueta7.setForeground(new Color(255, 255, 255));
        etiqueta7.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
        radioHumano2 = new JRadioButton("Humano",true);
        radioHumano2.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 11));
        radioHumano2.setForeground(new Color(240, 255, 240));
        radioHumano2.setBackground(new Color(95, 158, 160));
        radioCPU2 = new JRadioButton("CPU");
        radioCPU2.setForeground(new Color(240, 248, 255));
        radioCPU2.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 11));
        radioCPU2.setBackground(new Color(95, 158, 160));
        
        ButtonGroup tipo = new ButtonGroup();
        tipo.add(radioHumano2);
        tipo.add(radioCPU2);
        
        //creamos la accion para reiniciar la partida
        ActionListener reiniciar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
            	reiniciarPartida();
            	if(turno.equals("X")) {
            		turno = "O";
            	}
            	else {
            		turno = "X";
            	}
            }
        };
        botonNuevaPartida.addActionListener(reiniciar);
        radioCPU2.addActionListener(reiniciar);
        radioHumano2.addActionListener(reiniciar);
        
        // Agregar los componentes al panel lateral
        panelLateral.add(botonNuevaPartida);
        
        separator_1 = new JSeparator();
        separator_1.setMaximumSize(new Dimension(32779, 50000));
        panelLateral.add(separator_1);
        panelLateral.add(etiqueta1);
        
        separator_2 = new JSeparator();
        separator_2.setMaximumSize(new Dimension(32767, 50000));
        panelLateral.add(separator_2);
        panelLateral.add(etiqueta2);
        panelLateral.add(etiqueta3);
        campoTexto1 = new JTextField();
        campoTexto1.setMaximumSize(new Dimension(400, 150));
        campoTexto1.setAlignmentY(1.0f);
        
        etiqueta1.setText(campoTexto1.getText()+ ", coloca ficha...");
        panelLateral.add(campoTexto1);
        
        editorPane = new JEditorPane();
        editorPane.setBackground(new Color(95, 158, 160));
        panelLateral.add(editorPane);
        panelLateral.add(etiqueta5);
        panelLateral.add(etiqueta6);
        panelLateral.add(campoTexto2);
        
        separator = new JSeparator();
        panelLateral.add(separator);
        panelLateral.add(etiqueta7);
        panelLateral.add(radioHumano2);
        panelLateral.add(radioCPU2);

        // Agregar el panel lateral y el panel de juego al marco principal
        getContentPane().add(panelLateral, BorderLayout.EAST);
        JPanel panelJuego = new JPanel();
        panelJuego.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        panelJuego.setMinimumSize(new Dimension(32767, 32767));
        panelJuego.setLayout(new GridLayout(3, 3));
        iniciarTabla(panelJuego);
        getContentPane().add(panelJuego, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private void iniciarTabla(JPanel panelJuego) {
		
		tablero = new JButton[3][3];
		
		for (int fila = 0; fila < 3; fila++) {
			for (int col = 0; col < 3; col++) {
				tablero[fila][col] = new JButton("");
				tablero[fila][col].setFont(new Font("Times New Roman", Font.PLAIN, 45));
				tablero[fila][col].setBackground(new Color(224,224,224));

				
				ActionListener Act_Boton = new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) { 
		            	
		            	//Saber que boton se ha pulsado en el array
		            	JButton boton_act = (JButton) e.getSource();
		            	
		                if(boton_act.getText().equals("") && !hay6Fichas()) {
		                	//añadir la letra del jugador
		                	boton_act.setText(turno);   	
		                	
		                	if(haGanado(turno)) {
		                		JOptionPane.showMessageDialog(null, "Enhorabuena! Ganaste.");
		                		reiniciarPartida();
		                	}
		                	
		                	if(turno.equals("X")) {
		                		turno = "O";
		                	}
		                	else {
		                		turno = "X";
		                	}
		                			
		                }
		                else {
							if (hay6Fichas()) {
		                		if(boton_act.getText().equals(turno)) {
		                			boton_act.setText("");
		                		}
		                	}
		                }
		                
		                if (radioCPU2.isSelected() && turno.equals("O")) {
		                	if(!hay6Fichas()) {
		                		int fila = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	int columna = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	while(!tablero[fila][columna].getText().equals("")) {
			                		fila = (int) (Math.floor(Math.random()*(2-0+1)+0));
				                	columna = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	}
			                	tablero[fila][columna].setText("O");

		                	}
		                	else {
		                		int fila = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	int columna = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	while(!tablero[fila][columna].getText().equals("O")) {
			                		fila = (int) (Math.floor(Math.random()*(2-0+1)+0));
				                	columna = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	}
			                	tablero[fila][columna].setText("");
			                	fila = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	columna = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	while(!tablero[fila][columna].getText().equals("")) {
			                		fila = (int) (Math.floor(Math.random()*(2-0+1)+0));
				                	columna = (int) (Math.floor(Math.random()*(2-0+1)+0));
			                	}
			                	tablero[fila][columna].setText("O");
			                	

		                	}
		                	if(haGanado(turno)) {
		                		JOptionPane.showMessageDialog(null, "Enhorabuena! Ganaste.");
		                		reiniciarPartida();
		                	}
		                	
		                	if(turno.equals("X")) {
		                		turno = "O";
		                	}
		                	else {
		                		turno = "X";
		                	}
		                }
		            }
		        };
		        
				tablero[fila][col].addActionListener(Act_Boton);
				
				panelJuego.add(tablero[fila][col]);
				
			}
		}
	}
	
	private boolean haGanado(String turno1) {
		
		if(turno.equals("X")) {
			etiqueta1.setText(campoTexto2.getText()+ ", coloca ficha...");
		}
		else {
			etiqueta1.setText(campoTexto1.getText()+ ", coloca ficha...");
		}
		
		// Comprobar filas
        for (int fil = 0; fil < 3; fil++) {
        	if(tablero[fil][0].getText().equals(turno1) && tablero[fil][1].getText().equals(turno1) && tablero[fil][2].getText().equals(turno1)) {
        		return true;
        	}
		}

        // Comprobar columnas
        for (int fil = 0; fil < 3; fil++) {
        	if(tablero[0][fil].getText().equals(turno1) && tablero[1][fil].getText().equals(turno1) && tablero[2][fil].getText().equals(turno1)) {
        		return true;
        	}
		}

        
        // Check diagonals
        if(tablero[0][0].getText().equals(turno1) && tablero[1][1].getText().equals(turno1) && tablero[2][2].getText().equals(turno1)) {
    		return true;
    	}
        if(tablero[0][2].getText().equals(turno1) && tablero[1][1].getText().equals(turno1) && tablero[2][0].getText().equals(turno1)) {
    		return true;
    	}
        
        return false;
	}
	
	private boolean hay6Fichas() {
		int fichas = 0;
		for (int fila = 0; fila < 3; fila++) {
			for (int col = 0; col < 3; col++) {
				if(tablero[fila][col].getText().equals("")) {
					fichas+=1;
				}
			}
		}
		if ((9-fichas)<6) {
			return false;
		}
		return true;
	}
	
	private void reiniciarPartida() {
		turno = "O";
		etiqueta1.setText(campoTexto1.getText()+ ", coloca ficha...");
		for (int fila = 0; fila < 3; fila++) {
			for (int col = 0; col < 3; col++) {
				tablero[fila][col].setText("");
			}
		}
	}
}