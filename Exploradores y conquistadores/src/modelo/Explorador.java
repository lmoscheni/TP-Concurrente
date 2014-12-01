package modelo;

import java.util.Random;

public class Explorador extends Thread{
	
	Jugador jugador;
	int delay;
	Random random;
	String nombre;
	Equipo equipoNorte;
	Equipo equipoSur;
	
	public Explorador(Jugador jugador, int delay, String nombre, Equipo norte, Equipo sur){
		this.jugador = jugador;
		this.delay = delay;
		this.nombre = nombre;
		this.random = new Random();;
		this.equipoNorte = norte;
		this.equipoSur = sur;
	}
	
	@Override
	public void run() {
		super.run();
		while(! (this.equipoNorte.gano() || this.equipoSur.gano())){
			try {
				Thread.sleep(this.delay);
				int desicion = random.nextInt(3);
				if(desicion == 0){
					this.jugador.moverAdelante();
					System.out.println("El jugador " + this.nombre + " se movio adelante." );
				}
				if(desicion == 1){
					this.jugador.moverDerecha();
					System.out.println("El jugador " + this.nombre + " se movio a la derecha." );
				}
				if(desicion == 2){
					this.jugador.moverIzquierda();
					System.out.println("El jugador " + this.nombre + " se movio a la izquierda." );
				}
				if(desicion == 3){
					System.out.println("El jugador " + this.nombre + " se quedo en el lugar." );
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(this.equipoNorte.gano()){
			System.out.println("Gano el equipo Norte");
		}else{
			System.out.println("Gano el equipo Sur");
		}
	}
}
