import java.util.ArrayList;



public class Huerta {
	
	private Planta[][] huerto;
	private Persona cuidador;
	private Coordenada localizacion;
	private static ArrayList<Huerta> localizadas = new ArrayList<Huerta>();
	
	
	public Huerta(int filas, int columnas) {
		
		if (filas <= 0) {
			
			filas = 2;
		}
		if (columnas <= 0) {
			
			columnas = 2;
		}
	
		huerto = new Planta[filas][columnas];
		cuidador = null;
		localizacion = null;
	}
	
	
	
	public boolean planta(Planta plant) {
		
		boolean ret = false;
		
		if (plant != null) {
			if (plant.getPlantada() == null && plant.getEstado().equals("semilla")) {
				
				for (int i=huerto.length-1; i>=0&&ret==false; i--) {
				
					for(int j=huerto[i].length-1; j>=0&&ret==false; j--) {
					
						if (huerto[i][j] == null) {
					
							boolean comprobar_filas = false;
							int aux = 0;
						
							while(aux <= huerto[i].length-1) {
						
							if (huerto[i][aux]!= null) {
								if(!plant.getNombre().equals((huerto[i][aux].getNombre()))) { 
								
								comprobar_filas = true;
								}
							}	
							aux++;
						}
						
							if(comprobar_filas == false) {
								huerto[i][j]=plant;
								plant.setPlantada(this);
								ret = true;
							}	
						}
					}	
				}	
			}
		}
		return ret;
	}
	
	
	public ArrayList<Fruto> recolecta(String nombre) {
		
		ArrayList<Fruto> vector = new ArrayList<Fruto>();  
		
		
		for (int i=0;i<huerto.length; i++) {
			
			for(int j=0; j<huerto[i].length; j++) {
				
				if (huerto[i][j]!= null) {
					
					if(huerto[i][j].getFruto().equalsIgnoreCase(nombre)) {
						
						ArrayList<Fruto> aux = new ArrayList<Fruto>();
						aux = huerto[i][j].recolecta();
						
						for(int k=0; k<aux.size();k++)
							vector.add(aux.get(k));						
					}
				}
			}
		}	
		return vector;
	}			
	

	public int abona(int num, String nombre) { 
		
		boolean cambia_estado = false;
		int cont = 0;
		
		if(num > 0) {
			
			for (int i=0; i<huerto.length; i++) {
				
				for(int j=0; j<huerto[i].length; j++) {
			
					if(huerto[i][j] != null) {
						if (huerto[i][j].getNombre().equalsIgnoreCase(nombre)) {
						
							cambia_estado = huerto[i][j].abona(num);
						
							if (cambia_estado == true) {
								cont++;
							}
						}
					}
				}
			}	
		}	
		return cont;
	}

	  
	public String consulta(int fila,int col) {  
		
		String dev = null;
		
		if (fila >= 0 && fila < huerto.length && col >= 0 && col < huerto[fila].length) { 
			
			if(huerto[fila][col] != null)
				dev = huerto[fila][col].getNombre(); 
		}
		
		
		return dev;
	}
	
	public Planta arranca(String arrancar,int fila,int col) {
		
		Planta planta_arrancada = null;
	
		if (fila >= 0 && fila <= huerto.length-1 && col >= 0 && col <= huerto[0].length-1) {
		
			if (huerto[fila][col] != null) {
				if(huerto[fila][col].getNombre().equalsIgnoreCase(arrancar)) {
				
					if(huerto[fila][col] instanceof Trifido && this.getCuidador() instanceof Zombie) {
						
						return null;
					}
					else {
						planta_arrancada = huerto[fila][col]; 
				 		huerto[fila][col].arranca();
				 		huerto[fila][col] = null;
					}			  
				}
			}
		}
		return planta_arrancada;
	}
	
	public void localiza(double n1,double n2) {
		
		if (localizacion == null) {
			
			Coordenada coord_huerta = new Coordenada(n1, n2);
			this.localizacion = coord_huerta;
			
			localizadas.add(this);
		}
	}
	
	
	public ArrayList<String> getAdultas() {
		
		ArrayList<String> dev = new ArrayList<String>();
		
		for (int i=0; i<huerto.length; i++) {
			
			for(int j=0; j<huerto[i].length; j++) {
				
				if(huerto[i][j] != null) {
					
					if(huerto[i][j].getEstado().equals("adulta")){
					
						if(!dev.contains(huerto[i][j].getNombre())) { 
						
							dev.add(huerto[i][j].getNombre());
						}
					}	
				}
			}
		}
		return dev;
	}
	
	
	public Coordenada getLocalizacion() {
		
		return this.localizacion; 
	}
	
	
	public static ArrayList<Huerta> getLocalizadas() {

		return localizadas; 
	}
	
	public void setCuidador(Persona cuidad) {
		
		this.cuidador = cuidad; 
	}
	
	public Persona getCuidador() {
		
		return this.cuidador;
	}
	
	public Planta[][] getHuerto(){
		
		return this.huerto;		
	}
	
	public void setLocalizacion(Coordenada cor) {
		
		this.localizacion = cor;
	}
}






