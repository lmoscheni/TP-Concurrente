package main;

//import java.util.Random;
import modelo.Equipo;
import modelo.Explorador;
import modelo.Jugador;
import modelo.Tablero;

public class Partida {

	public static void main(String[] args) {
		
		Tablero tablero = new Tablero(11, 11);
		
		
		Equipo azul = new Equipo("Azul", "Norte", 3, 3);
		Equipo rojo = new Equipo("Rojo", "Sur", 3, 3);
		
		//Jugadores Equipo Azul
		Jugador j1 = new Jugador(0, 0, azul, tablero);
		Jugador j2 = new Jugador(4, 2, azul, tablero);
		Jugador j3 = new Jugador(8, 3, azul, tablero);
		
		//Jugadores Equipo Rojo
		Jugador j4 = new Jugador(10, 10, rojo, tablero);
		Jugador j5 = new Jugador(4, 8, rojo, tablero);
		Jugador j6 = new Jugador(6, 9, rojo, tablero);
		
		//Agrego jugadores del equipo Azul
		tablero.putJugador(0, 0, j1);
		tablero.putJugador(4, 2, j2);
		tablero.putJugador(8, 3, j3);
		
		//Agrego jugadores del equipo Rojo
		tablero.putJugador(10, 10, j4);
		tablero.putJugador(4, 8, j5);
		tablero.putJugador(6, 9, j6);
		
		//Agrego los tesoros del equipo Azul
		tablero.putTesoro(1,7,"Azul");
		tablero.putTesoro(4,7,"Azul");
		tablero.putTesoro(8,9,"Azul");
		
		//Agrego los tesoros del equipo Rojo	
		tablero.putTesoro(7,1,"Rojo");
		tablero.putTesoro(9,4,"Rojo");
		tablero.putTesoro(4,4,"Rojo");
		
		//Creo los exploradores del equipo Azul
		Explorador e1a = new Explorador(j1, 1000,"Leandro",azul, rojo);
		Explorador e2a = new Explorador(j1, 1500,"Mati",azul, rojo);
		Explorador e3a = new Explorador(j1, 500,"Diego",azul, rojo);
		
		//Creo los exploradores del equipo Rojo
		//Creo los exploradores del equipo Azul
		Explorador e1r = new Explorador(j1, 1000,"Jorge",azul, rojo);
		Explorador e2r = new Explorador(j1, 1500,"Esteban",azul, rojo);
		Explorador e3r = new Explorador(j1, 500,"Nicolas",azul, rojo);
		
		//Inicio la partida
		System.out.println("Inicia la partida");
		e1a.start();
		e2a.start();
		e3a.start();
		e1r.start();
		e2r.start();
		e3r.start();
		

	}
}

//int xa;
//int ya;
//Random random = new Random();
//xa = (int)(random.nextDouble() * 4 + 2);
//ya = (int)(random.nextDouble() * 4 + 2);
//tablero.putTesoro(xa, ya, jugador);
//System.out.println(xa);
//System.out.println(ya);
