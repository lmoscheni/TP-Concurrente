package modelo;

public class Tablero {
	Celda[][] tablero;
	int xMax;
	int yMax;

	public void mover(int x, int y, Jugador jugador) throws InterruptedException {
		if(esPosicionValida(x, y)){
			tablero[x][y].ponerJugador(jugador);
		}else{
			System.out.println("Posicion Invalida, estas en el borde");
		}
	}

	public void moverAdelante(int x, int y,Jugador jugador) throws InterruptedException {
		if(esPosicionValida(x, y)){
			tablero[x][y].ponerJugadorCuandoTengaVecinos(jugador);
		}else{
			System.out.println("Posicion Invalida, estas en el borde");
		}
		
	}

	public boolean tengoVecinos(int x, int y,Jugador jugador) {
		return tengoVecinoALosLados(x, y, jugador) || tengoVecinoAtras(x, y, jugador);
	}
	
	private boolean tengoVecinoAtras(int x, int y, Jugador jugador){
		if(jugador.miEquipo() == "Norte" && esPosicionValida(x, y-1)){
			return tablero[x][y-1].jugador.miEquipo() == jugador.miEquipo();
		}
		if(jugador.miEquipo() == "Sur" && esPosicionValida(x, y-1)){
			return tablero[x][y+1].jugador.miEquipo() == jugador.miEquipo();
		}else{
			return false;
		}
	}
	
	private boolean tengoVecinoALosLados(int x, int y, Jugador jugador){
		return tengoVecinosXmas1(x, y, jugador) || tengoVecinosXmenos1(x, y, jugador);
	}
	
	private boolean tengoVecinosXmas1(int x, int y, Jugador jugador){
		if(esPosicionValida(x-1, y)){
			return tablero[x-1][y].jugador.miEquipo() == jugador.miEquipo();
		}else{
			return false;
		}
	}
	
	private boolean tengoVecinosXmenos1(int x, int y, Jugador jugador){
		if(esPosicionValida(x+1, y)){
			return tablero[x+1][y].jugador.miEquipo() == jugador.miEquipo();
		}else{
			return false;
		}
	}
	
	private boolean esPosicionValida(int x, int y){
		if((x > xMax || x < 0) || (y > yMax || y < 0)){
			return false;
		}
		return true;
	}
	
	public boolean hayTesoro(int x, int y){
		return this.tablero[x][y].hayTesoro();
	}
	
	public boolean hayTesoroEnemigo(int x, int y, Jugador jugador){
		if(hayTesoro(x,y)){
			return this.tablero[x][y].elTesoroEsDeMiEquipo(jugador);
		}else{
			return false;
		}
	}
	
	
}
