package modelo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Celda {
	
	// Manejo de concurrencia
	Lock lock = new ReentrantLock();
	Condition espera = lock.newCondition();
	Condition esperaDeVecinos = lock.newCondition();
	
	//Variables de dominio
	Jugador jugador;
	String equipoDueñoDelTesoro;
	
	//Constructor
	public Celda(){
		this.jugador = null;
		this.equipoDueñoDelTesoro = null;
	}
	
	public void ponerJugador(Jugador unJugador) throws InterruptedException{
		lock.lock();	
		while(this.hayJugador()){
			this.espera.await();
		}
		this.jugador = unJugador;
//		this.esperaDeVecinos.signalAll();
		lock.unlock();
	}

	public void ponerJugadorCuandoTengaVecinos(Jugador unJugador) throws InterruptedException{
		lock.lock();
		while(this.hayJugador() || ! unJugador.tieneVecinos()){
			this.esperaDeVecinos.await();
//			this.espera.await();
		}
		this.jugador = unJugador;
		lock.unlock();
	}
	
	public void quitarJugador(){
		lock.lock();
		this.jugador = null;
		this.espera.signalAll();
		this.esperaDeVecinos.signalAll();
		lock.unlock();
	}
	
	public void quitarTesoro(){
		this.equipoDueñoDelTesoro = null;
	}
	
	private boolean hayJugador() {
		return this.jugador != null;
	}
	
	public boolean hayTesoro(){
		return this.equipoDueñoDelTesoro != null;
	}
	
	public boolean elTesoroEsDeMiEquipo(Jugador jugador){
		return this.equipoDueñoDelTesoro == jugador.miEquipo();
	}
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}
	
	public Jugador getJugador(){
		return this.jugador;
	}
	
	public void setTesoro(String equipo){
		this.equipoDueñoDelTesoro = equipo;
	}
}
