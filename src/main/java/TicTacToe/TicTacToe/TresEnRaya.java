package TicTacToe.TicTacToe;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TresEnRaya extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton[][] tablero;
	private String turno = "X";
	private boolean pasadas6fichas = false;
	
	public TresEnRaya() {
		 setTitle("TicTacToe");
        setBounds(400,300,600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        iniciarTabla();
	}
	
	private void iniciarTabla() {
		
		tablero = new JButton[3][3];
		
		for (int fila = 0; fila < 3; fila++) {
			for (int col = 0; col < 3; col++) {
				tablero[fila][col] = new JButton("");
				tablero[fila][col].setFont(new Font("Times New Roman", Font.PLAIN, 45));
				
				ActionListener Act_Boton = new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) { 
		            	
		            	//Saber que boton se ha pulsado en el array
		            	JButton boton_act = (JButton) e.getSource();
		            	
		                if(boton_act.getText().equals("") && !pasadas6fichas) {
		                	//añadir la letra del jugador
		                	boton_act.setText(turno);   	
		                	
		                	if(haGanado(turno)) {
		                		JOptionPane.showMessageDialog(null, "Enhorabuena! Ganaste.");
		                		reiniciarPartida();
		                	}
		                	else{
		                		hay6Fichas();
		                	}
		                	
		                	if(turno.equals("X")) {
		                		turno = "O";
		                	}
		                	else {
		                		turno = "X";
		                	}
		                			
		                }
		                else {
		                	if(pasadas6fichas && boton_act.getText().equals(turno)) {
		                		boton_act.setText("");
		                	}
		                }
		            }
		        };
		        
				tablero[fila][col].addActionListener(Act_Boton);
				
				add(tablero[fila][col]);
				
			}
		}
	}
	
	private boolean haGanado(String turno1) {
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
			pasadas6fichas = false;
			return false;
		}
		pasadas6fichas = true;
		return true;
	}
	
	private void reiniciarPartida() {
		turno = "O";
		for (int fila = 0; fila < 3; fila++) {
			for (int col = 0; col < 3; col++) {
				tablero[fila][col].setText("");
			}
		}
	}
}
