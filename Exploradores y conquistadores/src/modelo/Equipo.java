package modelo;

public class Equipo {
	
	String nombre;
	String origen;
	int cantidadDeTesoros;
	int tesorosPorCapturar;

	public Equipo(String nombre, String origen, int cantidadDeTesoros, int tesorosEnemigos) {
		this.nombre = nombre;
		this.origen = origen;
		this.cantidadDeTesoros = cantidadDeTesoros;
		this.tesorosPorCapturar = tesorosEnemigos;
	}
	
	public String emisferioDeOrigen() {
		return origen;
	}
	
	public void captureTesoro(){
		this.tesorosPorCapturar--;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public boolean gano(){
		return tesorosPorCapturar == 0;
	}
	

}
