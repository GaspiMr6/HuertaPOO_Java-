import java.util.ArrayList;

public class Inmune extends Persona{

	private Vehiculo vehiculo;
	
	public Inmune(String nom) {
		
		super(nom);
		vehiculo = null;
	}
	
	public boolean planta(Planta plant,Huerta huert) {
		
		boolean dev = false;
		
		if (plant != null && huert != null) {
			if(plant instanceof Trifido) {
			
				Planta p = new Planta(plant.getNombre(), plant.getFruto(), plant.getFrutos().length);
				p.setEstado(plant.getEstado());
				p.setFrutos(plant.getFrutos());
				
				dev = huert.planta(p);
				
			}
			else {
				dev = huert.planta(plant);
			}		
		}
		return dev;
	}
	

	public ArrayList<Planta> malasHierbas(){
		
		ArrayList<Planta> dev = new ArrayList<>();
		
		if(this.getHuerta() != null) {
			for(int i=0; i<this.getHuerta().getHuerto().length;i++) {
			
				for(int j=0; j<this.getHuerta().getHuerto()[i].length;j++) {
				
					if(this.getHuerta().getHuerto()[i][j] != null && this.getHuerta().getHuerto()[i][j] instanceof Trifido) {
					
						dev.add(this.getHuerta().getHuerto()[i][j]);
						this.getHuerta().getHuerto()[i][j].setPlantada(null);
						this.getHuerta().getHuerto()[i][j] = null;
					}
				}
			}
		}
		return dev;
	}
	
	
	public int abona(int abono,String nom) {
		
		int dev = 0;
		
		if(nom != null && abono > 0 && this.getHuerta() != null) {
			 
			for(int i=0; i<this.getHuerta().getHuerto().length;i++) {
				
				for(int j=0; j<this.getHuerta().getHuerto()[i].length;j++) {
					
					if(this.getHuerta().getHuerto()[i][j]!= null && this.getHuerta().getHuerto()[i][j].getFruto().equals(nom)) {
						
						this.getHuerta().getHuerto()[i][j].abona(abono);
						dev++;
					}
				}
			}
		}
		return dev;
	}
	
	public boolean apropia(Vehiculo vehi) {
		
		boolean dev= false;
		
		if(this.vehiculo == null && vehi.getOcupante() == null) {
			
			this.vehiculo = vehi;
			vehi.setOcupante(this);
			dev = true;
		}
		
		return dev;
	}
	
	
	
	public boolean abandona() {
		
		boolean dev = false;
		
		if(this.vehiculo != null) {
			
			this.vehiculo.setOcupante(null);
			this.vehiculo = null;
			dev = true;
		}
		
		return dev;
	}
	
	
	public ArrayList<String> repostaje(){
		
		ArrayList<String> dev = new ArrayList<>();
		ArrayList<Fruto> recolect = new ArrayList<Fruto>();
		
		if(this.getHuerta() != null && this.getVehiculo() != null) {
		
		for(int i=0; i<this.getHuerta().getHuerto().length;i++) {
			
			for(int j=0; j<this.getHuerta().getHuerto()[i].length;j++) {
				
				if(this.getHuerta().getHuerto()[i][j] != null) {
					
					for(int k=0; k<this.getHuerta().getHuerto()[i][j].getFrutos().length;k++) {
						
						if(this.getHuerta().getHuerto()[i][j].getFrutos()[k] != null &&
								this.getHuerta().getHuerto()[i][j].getFrutos()[k].getEstado().equals("comestible")) {
							
							dev.add(this.getHuerta().getHuerto()[i][j].getFrutos()[k].getNombre());
						}
					}
				}
			}
		}
		
		
		for(int i=0; i<this.getHuerta().getHuerto().length;i++) {
			
			for(int j=0; j<this.getHuerta().getHuerto()[i].length;j++) {
				
				if(this.getHuerta().getHuerto()[i][j] != null) {
											
					ArrayList<Fruto> aux = new ArrayList<>();
						
					aux = ((Planta)this.getHuerta().getHuerto()[i][j]).recolecta();
					
					if(aux != null) {
						for(int k=0; k<aux.size();k++) {
			
							recolect.add(aux.get(k));
						}		
					}
				}
			}
		}

		this.getVehiculo().repostaje(recolect);
		
		//ojo
		String aux;
		for(int i=0; i<dev.size();i++) {
			
			for(int j=0; j<dev.size()-1;j++) {
				
				if(dev.get(j).compareTo(dev.get(j+1)) > 0){
					
					aux = dev.get(j);
					dev.set(j, dev.get(j+1));
					dev.set(j+1, aux);
				}
			}
		}
		
			
	}
		return dev;	
	}
	
	public int paseo(Coordenada cor) {
		
		int dev = 0;
		boolean bo=false;
		int cont=0;
		
		if(this.vehiculo != null && this.getHuerta() != null) {
			
			//me guardo la huerta en la que estaba
			Huerta aux = this.getHuerta();
			aux = this.getHuerta();
			
				for(int i=0; i<Huerta.getLocalizadas().size(); i++) {
				
					if(Huerta.getLocalizadas().get(i).getLocalizacion().iguales(cor))
						cont++;	
				}
				if(cont>2)
					cont=2;
			
				for(int i=0; i<cont;i++) {
				
					bo = this.getVehiculo().traslada(cor);
				
					if(bo) {
						
						dev=dev+this.malasHierbas().size();
						if(this.getHuerta().getCuidador() instanceof Zombie) {
							
							this.getHuerta().setCuidador(null); 
						}
					}
				}
				
				this.setHuerta(aux);
		}
		return dev;
	}
	
	
	public Vehiculo getVehiculo() {
		
		return this.vehiculo;
	}
	
	
	
}
