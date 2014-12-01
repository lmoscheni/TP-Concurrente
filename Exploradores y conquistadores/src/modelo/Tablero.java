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
		if(esPosicionValida(x, y)){
			tablero[x][y].ponerJugador(jugador);
			jugador.actualizarPosicion(x, y);
		}else{
			System.out.println("La posicion: (" + x + "," + y + ")" + "es invalida");
		}
	}
	
	public void moverAdelante(int x, int y,Jugador jugador) throws InterruptedException {
		if(esPosicionValida(x, y)){
			tablero[x][y].ponerJugadorCuandoTengaVecinos(jugador);
			jugador.actualizarPosicion(x, y);
		}else{
			System.out.println("La posicion: (" + x + "," + y + ")" + "es invalida");
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
	
	public void salirDeCelda(int x, int y){
		if(this.esPosicionValida(x, y)){
			this.tablero[x][y].quitarJugador();
		}else{
			System.out.println("La posicion: (" + x + "," + y + ")" + "es invalida");
		}
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
		if(!(esPosicionValida(x, y+1) || esPosicionValida(x, y-1))){
			return false;
		}
		if(jugador.miEquipo() == "Norte"){
			return tablero[x][y-1].jugador.miEquipo() == jugador.miEquipo();
		}
		if(jugador.miEquipo() == "Sur"){
			return tablero[x][y+1].jugador.miEquipo() == jugador.miEquipo();
		}else{
			return false;
		}
	}
	
	private boolean tengoVecinoALosLados(int x, int y, Jugador jugador){
		return tengoVecinosXmas1(x, y, jugador) || tengoVecinosXmenos1(x, y, jugador);
	}
	
	private boolean tengoVecinosXmas1(int x, int y, Jugador jugador){
		if(esPosicionValida(x + 1, y)){
			Celda c = tablero[x+1][y];
			if(! (c.getJugador() == null)){
				String s = c.getJugador().miEquipo();
				return (s.equals(jugador.miEquipo()));
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	private boolean tengoVecinosXmenos1(int x, int y, Jugador jugador){
		if(esPosicionValida(x - 1, y)){
			Celda c = tablero[x-1][y];
			if(! (c.getJugador() == null)){
				String s = c.getJugador().miEquipo();
				return (s.equals(jugador.miEquipo()));
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	private boolean esPosicionValida(int x, int y){
		return ((x <= xMax || x >= 0) && (y <= yMax || y >= 0));
	}	
}

//public Celda obtenerCelda(int x, int y) {
//Celda d = this.tablero[x][y];
//
//return d;
//}	
//
//public boolean estoyEnAlgunExtremo(int x, int y){
//	boolean ret = this.esPosicionValida(x+1, y) && this.esPosicionValida(x-1, y)
//			      && this.esPosicionValida(x, y + 1) && this.esPosicionValida(x, y - 1);
//	return ret;
//}