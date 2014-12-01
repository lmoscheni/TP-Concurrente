package modelo;

public class Jugador{
	
	Equipo equipo;
	Tablero tablero;
	int posicionX;
	int posicionY;
	
	public Jugador(int x, int y, Equipo e, Tablero t) {
		this.posicionX = x;
		this.posicionY = y;
		this.equipo = e;
		this.tablero = t;
	}
	
	public void moverAdelante() throws InterruptedException{
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.moverAdelanteDesdeSur();
		}
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.moverAdelanteDesdeNorte();
		}
	}
	
	public void moverAdelanteDesdeNorte() throws InterruptedException{
		this.tablero.moverAdelante(posicionX,posicionY+1,this);
		this.buscarTesoroEnemigo(posicionX,posicionY+1);
		this.dejarCelda();
		this.actualizarPosicion(posicionX, posicionY + 1);
	}
	
	public void moverAdelanteDesdeSur() throws InterruptedException{
		this.tablero.moverAdelante(posicionX,posicionY-1,this);
		this.buscarTesoroEnemigo(posicionX,posicionY-1);
		this.dejarCelda();
		this.actualizarPosicion(posicionX, posicionY - 1);
	}
	
	public void moverDerecha() throws InterruptedException{
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.tablero.mover(posicionX +1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX+1,posicionY);
			this.dejarCelda();
			this.actualizarPosicion(posicionX + 1, posicionY);
		}
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.tablero.mover(posicionX -1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX-1 ,posicionY);
			this.dejarCelda();
			this.actualizarPosicion(posicionX - 1, posicionY);
		}
	}
	
	public void moverIzquierda() throws InterruptedException{
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.tablero.mover(posicionX -1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX-1,posicionY);
			this.dejarCelda();
			this.actualizarPosicion(posicionX - 1, posicionY);
		}
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.tablero.mover(posicionX +1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX-1,posicionY);
			this.dejarCelda();
			this.actualizarPosicion(posicionX + 1, posicionY);
		}
	}

	public boolean tieneVecinos() {
		return tablero.tengoVecinos(posicionX,posicionY,this);
	}
	
	public String miEquipo(){
		return equipo.getNombre();
	}
	
	private void buscarTesoroEnemigo(int x, int y){
		if(this.tablero.hayTesoroEnemigo(x, y, this)){
			this.equipo.captureTesoro();
			System.out.println("Capture tesoro");
		}
			
	}
	
	private void dejarCelda(){
		this.tablero.salirDeCelda(this.posicionX,this.posicionY);
	}
	
	private void actualizarPosicion(int x, int y){
		this.posicionX = x;
		this.posicionY = y;
	}

}
