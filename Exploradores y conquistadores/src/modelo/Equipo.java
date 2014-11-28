package modelo;

public class Equipo {
	
	String nombre;
	String origen;
	int cantidadDeTesoros;
	int tesorosPorCapturar;

	public String emisferioDeOrigen() {
		return origen;
	}
	
	public void captureTesoro(){
		this.tesorosPorCapturar--;
	}
	

}
