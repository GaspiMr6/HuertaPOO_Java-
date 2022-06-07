import java.util.ArrayList;

public class Vehiculo {

	private Persona ocupante;
	private boolean[] combustible;
	
	
	public Vehiculo(int tam) {
		
	if(tam < 2) {
		
		tam = 2;
	}
	
	ocupante = null;
	combustible = new boolean[tam]; //esta bien?
		
	}

	
	public boolean traslada(Coordenada cor) {
		
		boolean dev = false;
		boolean puede = false;
		boolean salir = false;
		boolean hay_alguna_localizada = false;
		int dist = 0;
		int comb = 0;
	
		if(cor != null && ocupante != null && this.ocupante.getHuerta() != null) {
		
			for(int i=0;i<this.combustible.length;i++) {
				
				if(this.combustible[i]==true) comb++;
			}
			dist = ((int) cor.distancia(this.getOcupante().getHuerta().getLocalizacion()));
		
			if(comb >= cor.distancia(this.getOcupante().getHuerta().getLocalizacion()) && dist >= 0) {
			
				for(int k=0; k<Huerta.getLocalizadas().size() && salir == false;k++) {
			
					if(Huerta.getLocalizadas().get(k) != null && Huerta.getLocalizadas().get(k).getLocalizacion().iguales(cor)) {
					
						hay_alguna_localizada = true;
						
						if(!(Huerta.getLocalizadas().get(k) == (this.ocupante.getHuerta()))) {
						
							this.ocupante.setHuerta(Huerta.getLocalizadas().get(k));
							salir = true;
							
							puede = true;
						}
					
						else {
							
							hay_alguna_localizada = true;
						}
					}
				}
				
				if(hay_alguna_localizada == false) {
					
					if(this.ocupante.getHuerta() != null) {
						this.ocupante.getHuerta().setLocalizacion(cor);
						puede = true;
					}
				}
		
				if(puede == true) {
					dev = true;		
					
					for(int i = combustible.length - 1; i >= 0 && dist > 0 ; i--) {
						if(combustible[i]) {
							combustible[i] = false;
							dist--;
						}
					}
					
					
				}
			}
		}
		return dev;
	}
	
	
	
	public int repostaje(ArrayList<Fruto> array) {
		
		double comb = 0.;
		int cont = 0;
		
		if(array != null) {
			
			for(int i=0; i<array.size(); i++) {
				
				comb = comb + array.get(i).valorCalorico();
			}
			
			int num2 = (int)comb;	
			
			for(int k=0; k<this.combustible.length && num2 > 0; k++) {
				
				if(this.combustible[k] == false) {
					
					this.combustible[k] = true;
					num2--;
					cont++;
				}
			}
		}
		return cont;
	}
	
	public boolean sube(Persona pers) {
		
		boolean dev = false;
		
		if(this.ocupante == null && pers != null && pers instanceof Inmune) {
			
			if(((Inmune) pers).getVehiculo() == null) {  
				
				dev = ((Inmune) pers).apropia(this);
			}
			
		}
		return dev;
	}
	
	public boolean baja() {
		
		boolean dev = false;
		
		if(this.ocupante != null) {
			
			dev = ((Inmune)this.ocupante).abandona();
		}
		return dev;
	}
	
	
	public Persona getOcupante() {
		
		return ocupante;	
	}
	
	public boolean[] getCombustible() {
		
		return combustible;
	}
	public void setOcupante(Persona pers) {
		this.ocupante = pers;
	}
}
