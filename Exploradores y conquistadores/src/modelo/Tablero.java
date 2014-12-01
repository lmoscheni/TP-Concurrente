package modelo;

public class Tablero {
	
	public Celda[][] tablero;
	int xMax;
	int yMax;

	// Constructor
	public Tablero(int x, int y) {
		this.xMax = x-1;
		this.yMax = y-1;
		this.tablero = new Celda[x][y];
		this.cargarCeldas(x,y);
	}
		
	// Accesors
	
	public void putTesoro(int x, int y, String equipo){
		this.tablero[x][y].setTesoro(equipo);
	}
	
	public void putJugador(int x, int y, Jugador jugador){
		Celda celda = this.tablero[x][y];
		celda.setJugador(jugador);
	}
	
	//Metodos de comunicacion con otros objetos
	
	public void mover(int x, int y, Jugador jugador) throws InterruptedException {
//		System.out.println("X=" + x + " Y=" + y);
		if(esPosicionValida(x, y)){
			tablero[x][y].ponerJugador(jugador);
			jugador.buscarTesoroEnemigo(x, y);
			this.salirDeCelda(x, y, jugador);
		}
	}
	
	public void moverAdelante(int x, int y,Jugador jugador) throws InterruptedException {
//		System.out.println("X=" + x + " Y=" + y);
		if(esPosicionValida(x, y)){
			tablero[x][y].ponerJugadorCuandoTengaVecinos(jugador);
			jugador.buscarTesoroEnemigo(x, y);
			this.salirDeCelda(x, y, jugador);
		}
	}

	public boolean tengoVecinos(int x, int y,Jugador jugador) {
		return tengoVecinoALosLados(x, y, jugador) || tengoVecinoAtras(x, y, jugador);
	}
	
	public boolean hayTesoro(int x, int y){
		if(this.esPosicionValida(x, y)){
			return this.tablero[x][y].hayTesoro();
		}else{
			return false;
		}
	}
	
	public boolean hayTesoroEnemigo(int x, int y, Jugador jugador){
		if(hayTesoro(x,y)){
			return this.tablero[x][y].elTesoroEsDeMiEquipo(jugador);
		}else{
			return false;
		}
	}
	
	public void salirDeCelda(int x, int y, Jugador jugador){
		this.tablero[jugador.posicionX][jugador.posicionY].quitarJugador();
		jugador.actualizarPosicion(x, y);
	}
	
	public void quitarTesoro(int x, int y){
		this.tablero[x][y].quitarTesoro();
	}
	
	//Metodos de soporte interno
	
	private void cargarCeldas(int x, int y){
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				this.tablero[i][j] = new Celda(); 
			}
		}
	}
	
	private boolean tengoVecinoAtras(int x, int y, Jugador jugador){
		if(jugador.equipo.getOrigen() == "Norte"){
			if(esPosicionValida(x, y-1) && tablero[x][y-1].hayJugador()){
				return tablero[x][y-1].jugador.miEquipo() == jugador.miEquipo();
			}
		}
		if(jugador.equipo.getOrigen() == "Sur"){
			if(esPosicionValida(x, y+1) && tablero[x][y+1].hayJugador()){
				return tablero[x][y+1].jugador.miEquipo() == jugador.miEquipo();
			}
		}
		return false;
	}
	
	private boolean tengoVecinoALosLados(int x, int y, Jugador jugador){
		return tengoVecinoEn(x+1,y,jugador) || tengoVecinoEn(x-1, y,jugador);
	}
	
	private boolean tengoVecinoEn(int x, int y, Jugador jugador){
		if(esPosicionValida(x, y) && tablero[x][y].hayJugador()){
			return jugador.miEquipo() == tablero[x][y].jugador.miEquipo();
		}
		return false;
	}

	private boolean esPosicionValida(int x, int y){
		return (x <= xMax && x >= 0 && y <= yMax && y >= 0);
	}	
}