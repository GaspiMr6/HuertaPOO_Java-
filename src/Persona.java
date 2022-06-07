import java.util.ArrayList;

public class Persona {

	private String nombre;
	private Huerta huerta;
	
	
	public Persona(String nom_per) {
		
		if (nom_per == null || nom_per.equals("")) {
			
			nom_per = "John Doe";
		}
		
		this.nombre = nom_per;  		
		huerta = null;
	}
	
	public boolean planta(Planta plant,Huerta huer) {
		
		boolean dev = false;
		
		if(plant != null && huer != null) {
		
			dev = huer.planta(plant);
		}
		return dev;
	}
	
	public Coordenada paseo() {
		
		Coordenada dev = null;
		boolean salir = false;
	
		if(this.huerta == null) {
			
			for(int i=0; i<Huerta.getLocalizadas().size() && salir == false;i++) { 
				
				if(Huerta.getLocalizadas().get(i).getCuidador() == null) { 
			
					this.huerta = Huerta.getLocalizadas().get(i); 
					this.huerta.setCuidador(this);
					salir = true;
					dev = Huerta.getLocalizadas().get(i).getLocalizacion();
				}
			}
		}
		else if (this.huerta != null) {
			
			dev = this.huerta.getLocalizacion();
		}
			
		return dev;
	}
	
	
	public ArrayList<Planta> malasHierbas(){
		
		ArrayList<Planta> dev = new ArrayList<Planta>();
		boolean array_vacio = false;
		
		
		if (this.huerta != null) {
			for (int i=0; i<this.huerta.getHuerto().length;i++) {
			
				for(int j=0; j<this.huerta.getHuerto()[i].length;j++) 	{
					array_vacio = false;
					
					if(this.huerta.getHuerto()[i][j] != null) {
				
						if(this.huerta.getHuerto()[i][j].getEstado().equals("adulta")) { 

							for(int m=0; m<this.huerta.getHuerto()[i][j].getFrutos().length && array_vacio == false;m++) {
						
									if(this.huerta.getHuerto()[i][j].getFrutos()[m] != null) {
								
										array_vacio = true;
									}
							}
							if (array_vacio == false) {
						
								dev.add(this.huerta.getHuerto()[i][j]);
								this.huerta.arranca(this.huerta.getHuerto()[i][j].getNombre(), i, j); 
							}
						}
					}
				}
			}
		}
		return dev;
	}
		
	public int abona(int abono,String nombre) {
		
		int dev = 0;
		
		if (this.huerta != null)
			dev = this.huerta.abona(abono, nombre); 
		
		return dev;
	}
		
	
	public String getNombre() {
		
		return this.nombre;
	}
	
	public Huerta getHuerta() {
		
		return this.huerta;
	}

	
	//para el constructor de zombie
	public void setHuerta(Huerta huert) {
		
		this.huerta = huert;
	}
	
	
}

