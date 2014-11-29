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
	
	public Celda() {
		this.jugador = null;
		this.equipoDueñoDelTesoro = null;
	}
	
	public String getEquipoDuenoDelTesoro() {
		return equipoDueñoDelTesoro;
	}
	
	public void ponerJugador(Jugador unJugador) throws InterruptedException{
		lock.lock();
		while(this.hayJugador()){
			this.espera.await();
		}
		// Para despertar a los posibles threads que puedan avanzar al tener un vecino
		this.esperaDeVecinos.signalAll();
		lock.unlock();
	}

	public void ponerJugadorCuandoTengaVecinos(Jugador unJugador) throws InterruptedException{
		lock.lock();
		while(this.hayJugador() || ! unJugador.tieneVecinos()){
			this.esperaDeVecinos.await();
		}
		lock.unlock();
	}
	
	public void quitarJugador(){
		lock.lock();
		this.jugador = null;
		this.espera.signal();
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
}
