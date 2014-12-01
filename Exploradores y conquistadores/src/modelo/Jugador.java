package modelo;

public class Jugador{
	
	Equipo equipo;
	Tablero tablero;
	int posicionX;
	int posicionY;
	
	public Jugador(int x, int y, Equipo equipo, Tablero tablero) {
		this.posicionX = x;
		this.posicionY = y;
		this.equipo = equipo;
		this.tablero = tablero;
	}
	
	//Metodos de Dominio
	
	public void moverAdelante() throws InterruptedException{
		
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.moverAdelanteDesdeNorte();
		}
		
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.moverAdelanteDesdeSur();
		}
	}
	
	public void moverDerecha() throws InterruptedException{
		
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.tablero.mover(posicionX-1, posicionY,this);
//			this.buscarTesoroEnemigo(posicionX-1 ,posicionY);
		}
		
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.tablero.mover(posicionX+1, posicionY,this);
//			this.buscarTesoroEnemigo(posicionX+1,posicionY);
		}
	}
	
	public void moverIzquierda() throws InterruptedException{
		
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.tablero.mover(posicionX-1, posicionY,this);
//			this.buscarTesoroEnemigo(posicionX-1,posicionY);
		}
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.tablero.mover(posicionX+1, posicionY,this);
//			this.buscarTesoroEnemigo(posicionX+1,posicionY);
		}
	}
	
	
	//Metodos de comunicacion con otros objetos
	
	public String miEquipo(){
		return equipo.getNombre();
	}
	
	public boolean tieneVecinos() {
		return tablero.tengoVecinos(posicionX,posicionY,this);
	}
	
	public void actualizarPosicion(int x, int y){
		this.posicionX = x;
		this.posicionY = y;
	}
	
	//Metodos de Soporte Interno
	
	private void moverAdelanteDesdeNorte() throws InterruptedException{
		this.tablero.moverAdelante(posicionX,posicionY+1,this);
//		this.buscarTesoroEnemigo(posicionX,posicionY+1);
	}
	
	private void moverAdelanteDesdeSur() throws InterruptedException{
		this.tablero.moverAdelante(posicionX,posicionY-1,this);
//		this.buscarTesoroEnemigo(posicionX,posicionY-1);
	}

	public void buscarTesoroEnemigo(int x, int y){
		if(this.tablero.hayTesoroEnemigo(x, y, this)){
			this.tablero.quitarTesoro(x, y);
			this.equipo.captureTesoro();
		}		
	}
	
//	public void dejarCelda(int xNueva, int yNueva){
//		this.tablero.salirDeCelda(xNueva,yNueva,this);
//	}
	

}
