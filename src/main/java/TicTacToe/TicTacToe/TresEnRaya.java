package TicTacToe.TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
    
	public TresEnRaya() {
		setTitle("TicTacToe");
        setBounds(400,300,600,600);
        
        // Panel lateral
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        
        botonNuevaPartida = new JButton("Nueva partida");
        etiqueta1 = new JLabel("ESTO ES EL TE");
        etiqueta2 = new JLabel("Jugador 1");
        etiqueta3 = new JLabel("Nombre");
        campoTexto1 = new JTextField();
        etiqueta5 = new JLabel("Jugador 2");
        etiqueta6 = new JLabel("Nombre");
        campoTexto2 = new JTextField();
        etiqueta7 = new JLabel("Tipo");
        radioHumano2 = new JRadioButton("Humano");
        radioCPU2 = new JRadioButton("CPU");
        ButtonGroup tipo = new ButtonGroup();
        tipo.add(radioHumano2);
        tipo.add(radioCPU2);
        
        etiqueta1.setText(campoTexto1.getText()+ ", coloca ficha...");
        
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
        
        // Agregar los componentes al panel lateral
        panelLateral.add(botonNuevaPartida);
        panelLateral.add(etiqueta1);
        panelLateral.add(etiqueta2);
        panelLateral.add(etiqueta3);
        panelLateral.add(campoTexto1);
        panelLateral.add(etiqueta5);
        panelLateral.add(etiqueta6);
        panelLateral.add(campoTexto2);
        panelLateral.add(etiqueta7);
        panelLateral.add(radioHumano2);
        panelLateral.add(radioCPU2);

        // Agregar el panel lateral y el panel de juego al marco principal
        add(panelLateral, BorderLayout.EAST);
        JPanel panelJuego = new JPanel();
        panelJuego.setLayout(new GridLayout(3, 3));
        iniciarTabla(panelJuego);
        add(panelJuego, BorderLayout.CENTER);
        
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
		            	
		            	if (!radioCPU2.isSelected()) {
		            		
		            	}
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
