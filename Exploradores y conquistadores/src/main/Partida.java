package main;

import modelo.Equipo;
import modelo.Jugador;
import modelo.Tablero;

public class Partida {

	public static void main(String[] args) {
		
		Tablero tablero = new Tablero(11, 11);
		
		Equipo azul = new Equipo("Azul", "Norte", 3, 5);
		Equipo rojo = new Equipo("Rojo", "Sur", 3, 5);
		
		//Jugadores Equipo Azul
		Jugador j1 = new Jugador(5, 7, azul, tablero);
		Jugador j2 = new Jugador(4, 9, azul, tablero);
		Jugador j3 = new Jugador(1, 3, azul, tablero);
		
		//Jugadores Equipo Rojo
		Jugador j4 = new Jugador(7, 4, rojo, tablero);
		Jugador j5 = new Jugador(10, 6, rojo, tablero);
		Jugador j6 = new Jugador(11, 10, rojo, tablero);
		
		//Agrego jugadores del equipo Azul
		tablero.agregarJugador(5, 7, j1);
		tablero.agregarJugador(4, 9, j2);
		tablero.agregarJugador(1, 3, j3);
		
		//Agrego jugadores del equipo Rojo
		tablero.agregarJugador(7, 4, j4);
		tablero.agregarJugador(10, 6, j5);
		tablero.agregarJugador(11, 10, j6);
		
		j1.start();
		j2.start();
		j3.start();
		j4.start();
		j5.start();
		j6.start();
	}
}
