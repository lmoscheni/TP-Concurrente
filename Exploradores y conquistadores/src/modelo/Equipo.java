package modelo;

public class Equipo {
	
	String nombre;
	String origen;
	int cantidadDeTesoros;
	int tesorosPorCapturar;

	public Equipo(String e, String o, int cTesoros, int tesorosEnemigos) {
		this.nombre = e;
		this.origen = o;
		this.cantidadDeTesoros = cTesoros;
		this.tesorosPorCapturar = tesorosEnemigos;
	}
	
	public String emisferioDeOrigen() {
		return origen;
	}
	
	public void captureTesoro(){
		this.tesorosPorCapturar--;
	}
	

}
