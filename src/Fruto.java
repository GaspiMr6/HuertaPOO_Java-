
public class Fruto {

	
	private boolean estado;
	private double peso;
	private String nombre;
	
	
	public Fruto(String cad) {
		
			boolean esta = false;
		
			
			for (int i=0; i<Valores.getNombres().size();i++) {   
			
				if (Valores.getNombres().get(i).equalsIgnoreCase(cad)) {  
				
					esta = true;
				}
			}
			if (esta == false) {
				
				this.nombre = "pitaya";
				Valores.add("pitaya", 0.);
			}
			else {
				
				this.nombre = cad;
			}
			
			this.peso = 0.;
			this.estado = false;
			
			
		}
	

	public Fruto(Fruto fruto) {
		
		this.peso = fruto.peso;
		this.nombre = fruto.nombre;
		this.estado = fruto.estado;
	}

	
	//metodos de fruto
	public boolean transforma(int num) {
		
		boolean dev = false;
		
		if (num > 0) {
			
			this.peso = this.peso + (0.2 * num);
		
		
			if (this.peso >= 0.3 && this.estado == false) {  // si el peso indica que es comestible y pone que no lo es, cambia de estado
			
				this.estado = true;
				dev = true;
			}
			
		}
		return dev;
	}
	
	
	public double valorCalorico() {
		
		double ret = this.peso * Valores.consulta(this.nombre); 
		return ret;
	}
	
	
	public String getNombre() {
		
		return this.nombre;	
	}
	
	
	public String getEstado() {
		
		if (this.estado) {
			
			return "comestible";
		}
		else {
			
			return "inmaduro";
		}	
	}
	
	public double getPeso() {
		
		return this.peso;
	}
	
	public void setPeso(double num) {
		
		this.peso = num;
	}
	public void setEstado(double pe) {
		
		if(pe >= 0.3)
			this.estado = true;
		else
			this.estado = false;
	}
}
