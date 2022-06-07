import java.util.ArrayList;

public class Valores {

	private static class Relacion { 
		
		//implementacion de la clase relacion
		private String nombre;		
		private double valor;
		
		//constructor de Relacion
		public Relacion (String nombre, double valor) { 

				if (valor <= 0)	{				
					this.valor = 0.5;				
				}
				else {
					
					this.valor = valor;
				}
				if (nombre == null || nombre.equals("")) {
					
					this.nombre = "pitaya";
				}
				else {

					this.nombre = nombre; 
				}
		}	
	}
	

	private static ArrayList<Relacion> clasificacion = new ArrayList<Relacion>();
	
	public static boolean add(String cad, double num) {
		
		boolean ya_existe = false;
		boolean ret = false;
		
		Relacion rel = new Relacion(cad,num);
		
		for (int i=0; i<clasificacion.size() && ya_existe==false; i++){
			
			if (clasificacion.get(i).nombre.equalsIgnoreCase(rel.nombre)) { 
	
				ya_existe = true;
			}	
		}
		
		if(ya_existe == false) {
			
			
			clasificacion.add(rel);
		
			ret = true;
		}
		return ret;
	}
	
	
	
	public static double consulta(String cad) {
		
		double ret = -1.0;
		
		for (int i=0; i<clasificacion.size() && ret == -1.0; i++) {
			
			if (clasificacion.get(i).nombre.equalsIgnoreCase(cad)) {
				
				ret = clasificacion.get(i).valor;
			}
		}
		
		return ret;
	}
	
	
	//metodo getNombres
	public static ArrayList<String> getNombres() {
		
		ArrayList<String> array = new ArrayList<String>();
															
		for (int i=0; i<clasificacion.size(); i++) {		
			
			array.add(clasificacion.get(i).nombre); 
			
		}
		
		return array;										
	}
}



