import java.util.ArrayList;

public class Planta {
	
	private String estado;
	private String nombre;
	private String fruto;
	private Huerta plantada;
	private Fruto[] frutos;
	
	
	public Planta(String cad1, String cad2, int num) {
		
		if (cad1 != null && cad1.equals("") == false) {
			
			this.nombre = cad1;
		}
		else {
			
			this.nombre = "vegetal";
		}
		
		
		if(cad2 != null && cad2.equals("") == false) {
			
			this.fruto = cad2;
		}
		else {
			
			this.fruto = "pitaya";
		}
		
		if (num < 1) {		
			num = 1;
		}
		frutos = new Fruto[num]; 
		
		this.estado = "semilla"; 
		this.plantada = null; 
		
		
	}
	
	//---------------------------------
	public Planta(Trifido trif) {
		
		this.estado = trif.getEstado();
		this.nombre = trif.getNombre();
		this.fruto = trif.getFruto();
		this.frutos = new Fruto [trif.getFrutos().length];
		
		for(int i=0;i<frutos.length;i++) {
			
			if(trif.getFrutos()[i] != null)
				
				this.frutos[i] = new Fruto(trif.getFrutos()[i]);
		}
		
	}
	//--------------------------------------
	
	
	public boolean abona(int num) {
		
		boolean ret = false; 
		
		if (this.plantada != null ) {   
			
			if (this.estado.equals("semilla")) {  
				
				this.estado = "germinado";
				ret = true;
			}
			else if (this.estado.equals("germinado")) {
				
				this.estado = "brote";
				ret = true;
			}
			else if (this.estado.equals("brote")) {
				
				this.estado = "adulta";
				ret = true;
			}
			else if (this.estado.equals("adulta")) {
				
				Fruto fruto = new Fruto(this.fruto);
				
				boolean salir = false;	
				int pos = -1; 
				
				for(int i=0; i<frutos.length && salir == false; i++) {
					
					if(frutos[i] == null) {
						
						salir = true;   
						pos = i;
						
						frutos[i] = fruto; 
						ret = true;											
					}
				}
				
				
				for (int i=0; i<frutos.length;i++) {
					
					if (i != pos && this.frutos[i] != null) { 
						
						if(frutos[i].transforma(num)) {
							
							ret = true;	
						}
						
							
						if (frutos[i].getPeso() > num) {  
							
							frutos[i] = null;
							ret = true;
						}
					}	
				}
			}
		}
		
		return ret;
	}
	
	
	
	
	
	
	public ArrayList<Fruto> recolecta() {
		
		ArrayList<Fruto> dev = new ArrayList<Fruto>();
		
		for(int i=0; i<frutos.length; i++) {
			
			if(frutos[i] != null) {
				if (frutos[i].getEstado().equals("comestible")) {
				
					dev.add(frutos[i]);
					frutos[i] = null;
				}
			}
		}
		

		Fruto aux;
		for(int i=0; i<dev.size(); i++) {
			
			for(int j=0; j<dev.size()-1; j++) {
				
				if(dev.get(j+1).getPeso()<dev.get(j).getPeso()) { 
					
					aux = dev.get(j);
					dev.set(j, dev.get(j+1));
					dev.set(j+1, aux);
				}
			}
		}
		
		return dev;
	}
	
	
	
	
	public String getNombre() {
		
		return this.nombre;
	}
	
	
	public void arranca() {
		
		this.plantada = null;	
	}
	
	
	public String getEstado() {
		
		return this.estado;
		
	}
	public void setEstado(String estado) {
		
		this.estado = estado;
	}
	
	public void setFrutos(Fruto[] frutos) {
		this.frutos = frutos;
	}
	
	
	public Huerta getPlantada() {
		
		return this.plantada;		
	}
	
	public void setPlantada(Huerta huer){
		
			this.plantada = huer;
	}
	
	public Fruto[] getFrutos() {
		
		return frutos;
	}
	
	public String getFruto() {
		
		return fruto;
	}
		
}
