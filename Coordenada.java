
public class Coordenada {

	//atributos
	private double latitud;
	private	double longitud;
	
	//metodos
	//public Coordenada(double latitud, double longitud) {
	//	this.latitud = latitud; //el this se refiere al objeto que ejecuta el codigo(al de arriba)
	//}
	
	// es lo mismo que:
	 public Coordenada(double x, double y){
		 if (x >= -90.0 && x <= 90.)
			 latitud = x;
		 else
			 latitud = 0.;
			
		 
		 if(y >= -180. && y<=180.)
			 longitud = y;
		 else
			 longitud = 0.;  
	 }
		
	 
	public boolean iguales(Coordenada coord) {
		
		//return latitud == coord.latitud && longitud == coord.longitud;
		if (coord != null && latitud == coord.latitud && longitud == coord.longitud)
			return true;
		
		else
			return false;
	}
	
	public double distancia(Coordenada coord) {
		if (coord != null) {
			
			return Math.sqrt(Math.pow((latitud-coord.latitud),2)+ Math.pow(longitud - coord.longitud, 2));
		
		}
		else		
			return -1.;
	}
	
	
	//BORRAR ESTO
	public double getLatitud() {
		
		return this.latitud;
	}
public double getLongitud() {
		
		return this.longitud;
	}
	
	//tiobeindex pag que dice cuales son los lenguajes mas usados
	
}
