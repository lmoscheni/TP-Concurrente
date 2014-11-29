package modelo;

public class Jugador extends Thread{
	
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
	
//	public void moverAtras() throws InterruptedException {
//		if(this.equipo.emisferioDeOrigen() == "Sur"){
//			this.moverAdelanteDesdeSur();
//		}
//		if(this.equipo.emisferioDeOrigen() == "Norte"){
//			this.moverAdelanteDesdeNorte();
//		}
//	}
	
	public void moverAdelanteDesdeNorte() throws InterruptedException{
		this.tablero.moverAdelante(posicionX,posicionY+1,this);
		this.buscarTesoroEnemigo(posicionX,posicionY+1);
	}
	
//	public void moverAtrasDesdeNorte() throws InterruptedException {
//		this.tablero.moverAdelante(posicionX,posicionY-1,this);
//		this.buscarTesoroEnemigo(posicionX,posicionY+1);
//	}
	
	public void moverAdelanteDesdeSur() throws InterruptedException{
		this.tablero.moverAdelante(posicionX,posicionY-1,this);
		this.buscarTesoroEnemigo(posicionX,posicionY-1);
	}
	
//	public void moverAtrasDesdeSur() throws InterruptedException {
//		this.tablero.moverAdelante(posicionX,posicionY+1,this);
//		this.buscarTesoroEnemigo(posicionX,posicionY-1);
//	}
	
	public void moverDerecha() throws InterruptedException{
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.tablero.mover(posicionX +1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX+1,posicionY);
		}
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.tablero.mover(posicionX -1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX-1 ,posicionY);
		}
	}
	
	public void moverIzquierda() throws InterruptedException{
		if(this.equipo.emisferioDeOrigen() == "Sur"){
			this.tablero.mover(posicionX -1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX-1,posicionY);
		}
		if(this.equipo.emisferioDeOrigen() == "Norte"){
			this.tablero.mover(posicionX +1, posicionY,this);
			this.buscarTesoroEnemigo(posicionX-1,posicionY);
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
		}
			
	}

	public void run() {
		while(true) {
			int i = (int) (Math.random() * 4);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			
			if(i == 0){
				try {
					this.moverIzquierda();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(i == 1) {
				try {
					this.moverDerecha();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(i == 2) {
				try {
					this.moverAdelante();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			else {
//				try {
//					this.moverAtras();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
	}
}
