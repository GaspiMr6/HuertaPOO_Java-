import java.util.ArrayList;

public class Zombie extends Persona{

	public Zombie(Persona pers) {
		
		super(pers.getNombre());
		this.setHuerta(pers.getHuerta());
		pers.setHuerta(null);   
	}
	
	public boolean planta(Planta plant,Huerta huer) {
		
		boolean dev = false;
		Trifido trif;
		
		if(plant != null && huer != null) {
			if(huer == (this.getHuerta())) {
			
				if(!(plant instanceof Trifido)) {
				
					Planta planta = new Trifido(plant.getNombre(),plant.getFruto(),plant.getFrutos().length);
					trif = (Trifido) planta; 
					dev = huer.planta(trif);
				
				}
				else {
				
					dev = huer.planta(plant);
				}
			}
		}
		return dev;
	}
	
	public Coordenada paseo() {
		
		Coordenada cor = null;
		
		if (this.getHuerta() != null) {
			
			cor = this.getHuerta().getLocalizacion();			
		}

		return cor;
	}
	
	public ArrayList<Planta> malasHierbas(){
		
		ArrayList<Planta> dev = new ArrayList<>();
		
		if(this.getHuerta() != null) {
			
			for(int i=0; i<this.getHuerta().getHuerto().length;i++) {
				
				for(int j=0; j<this.getHuerta().getHuerto()[i].length;j++) {
					
					if(this.getHuerta().getHuerto()[i][j] != null 
							&& this.getHuerta().getHuerto()[i][j].getEstado().equals("adulta") 
							&& !(this.getHuerta().getHuerto()[i][j] instanceof Trifido)) {
						
						dev.add(this.getHuerta().getHuerto()[i][j]);
						this.getHuerta().getHuerto()[i][j].arranca();
						this.getHuerta().getHuerto()[i][j]=null;
					}
				}
			}	
		}
		
		return dev;
	}
	
	
	public int abona(int num,String nom) {
		
		int dev = 0;
		
		if(this.getHuerta() != null) {
			
		for(int i=0; i<this.getHuerta().getHuerto().length;i++) {
			
			for(int j=0; j<this.getHuerta().getHuerto()[i].length;j++) {
				
				if(this.getHuerta().getHuerto()[i][j] != null && this.getHuerta().getHuerto()[i][j] instanceof Trifido) {
					
					this.getHuerta().getHuerto()[i][j].abona(num);
					dev++;					
				}
			}
		}
		}
		return dev;
		
	}
	
	public int abona() {
		
		int dev = 0;
		
		if(this.getHuerta() != null) {
			for(int i=0; i<this.getHuerta().getHuerto().length;i++) {
			
				for(int j=0; j<this.getHuerta().getHuerto()[i].length;j++) {
				
					if(this.getHuerta().getHuerto()[i][j] != null && this.getHuerta().getHuerto()[i][j] instanceof Trifido) {
										
						dev = dev +  ((Trifido) this.getHuerta().getHuerto()[i][j]).abona();
					}
				}
			}
		}
		return dev;
	}
}
