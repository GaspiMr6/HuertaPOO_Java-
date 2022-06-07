import java.util.*;


public class Trifido extends Planta {

	private int[] posicion;
	
	public Trifido(String planta,String fruto, int max) {
		
		super(planta,fruto,max);
		posicion = null;
	}

	
		
		public boolean abona(int num) {
			
			boolean dev = false;
		
			if(this.getPlantada() != null) {
				
				if(this.getPlantada().getCuidador()!= null && !(this.getPlantada().getCuidador() instanceof Inmune)) { 
					
					if(this.getEstado().equals("adulta")) {
						
						for(int i=0; i<this.getFrutos().length;i++) {
							
							if(this.getFrutos()[i] != null) {
													
								this.getFrutos()[i].transforma(num);  
							}
						}	
					}
					
					else {
						
						this.setEstado("adulta");
					}
					
					
					int vuelta = 0;
					for(int i=0; i<this.getFrutos().length && vuelta < 2;i++) {
						
						if(this.getFrutos()[i] == null && vuelta == 0) {
							
							Fruto frut1 = new Fruto(this.getFruto());
							this.getFrutos()[i] = frut1;
							
							vuelta++;
						}
						else if(this.getFrutos()[i] == null && vuelta == 1) {
							
							Fruto frut2 = new Fruto(this.getFruto());
							this.getFrutos()[i] = frut2;
							
							vuelta++;
						}
					}
					if(!(this.getPlantada().getCuidador() instanceof Zombie)) {
						
						this.getPlantada().setCuidador(new Zombie(this.getPlantada().getCuidador()));
					}
					
					return true;	
				}
				
				else{  //No es verdad que (es inmune y hay cuidador) 
					
					boolean hay_frutos = false;
					for(int i=0; i<this.getFrutos().length;i++) {
						
						if(this.getFrutos()[i] != null) {
							
							this.getFrutos()[i] = null;
							hay_frutos = true;
						}
					}
					if(hay_frutos == true) {
						
						int pos = -1;
						
						if(Huerta.getLocalizadas().contains(this.getPlantada())) { 
							
							double dis_min= Double.MAX_VALUE;
					
							//consigo la pos del menor
							for(int i=0; i<Huerta.getLocalizadas().size(); i++) {
													
								if(Huerta.getLocalizadas().get(i).getLocalizacion().distancia(this.getPlantada().getLocalizacion()) < dis_min
									&& !(Huerta.getLocalizadas().get(i) == (this.getPlantada())) && Huerta.getLocalizadas().get(i).getCuidador() != null) {
									
									dis_min = Huerta.getLocalizadas().get(i).getLocalizacion().distancia(this.getPlantada().getLocalizacion());
									pos = i; //pos = 1 +i?
								}
							}
						
							
						}
						else { //si no esta localidada
						
							boolean salir = false;
						
							for(int i=0; i<Huerta.getLocalizadas().size() && salir == false; i++) {
							
								if(!(Huerta.getLocalizadas().get(i) == (this.getPlantada())) && Huerta.getLocalizadas().get(i).getCuidador() != null) {
									
									pos = i; //pos = i+1?
									salir = true;
								}
							}
						}
					
						boolean salir = false;
						for(int i=0; i<Huerta.getLocalizadas().get(pos).getHuerto().length && salir == false;i++) { 
							
							for(int j=0; j<Huerta.getLocalizadas().get(pos).getHuerto()[i].length && salir == false;j++) { 
								
								if(pos != -1 && Huerta.getLocalizadas().get(pos).getHuerto()[i][j] == null) {
									
									this.getPlantada().getHuerto()[posicion[0]][posicion[1]] = null; // la quito de donde estea.
									Huerta.getLocalizadas().get(pos).getHuerto()[i][j] = this; 
									posicion = new int [2];
									posicion[0] = i;
									posicion[1] = j;
									super.setPlantada(Huerta.getLocalizadas().get(pos));
									salir = true;
									return true;

								}
							}
						}	
					}
				}
			}	
			
			return dev;
		}
	
		
	
	public ArrayList<Fruto> recolecta() {
		
		boolean hay_frutos = false;
		
		int pos = 0;
		ArrayList<Fruto> dev = new ArrayList<>();
		
		for(int i=0; i<this.getFrutos().length && hay_frutos == false;i++) {
			
			if(this.getFrutos()[i] != null) {
				
				hay_frutos = true;
			}
		}
			
		if(hay_frutos == true) {
			
			if(Huerta.getLocalizadas().contains(this.getPlantada())) { //SI ESTA LOCALIZADA
				
				double dis_min=Double.MAX_VALUE;
				
				//consigo la pos del menor
				for(int i=0; i<Huerta.getLocalizadas().size(); i++) {
											
					if(Huerta.getLocalizadas().get(i) != null &&
							Huerta.getLocalizadas().get(i).getLocalizacion().distancia(this.getPlantada().getLocalizacion()) < dis_min
							&& Huerta.getLocalizadas().get(i) != this.getPlantada()) {
						
						dis_min = Huerta.getLocalizadas().get(i).getLocalizacion().distancia(this.getPlantada().getLocalizacion());
						pos = i;
					}
				}
				
				if(Huerta.getLocalizadas().get(pos).getHuerto()[0][0] != null) {
					
					//anadimos al arraylist los frutos
					for(int i=0; i<Huerta.getLocalizadas().get(pos).getHuerto()[0][0].getFrutos().length;i++) {
						
						if(Huerta.getLocalizadas().get(pos).getHuerto()[0][0].getFrutos()[i] != null) {
							
							dev.add(Huerta.getLocalizadas().get(pos).getHuerto()[0][0].getFrutos()[i]);
						}
					}
					
					//los ordenamos
					Fruto aux;
					for(int i=0; i<dev.size(); i++) {
						
						for(int j=0; j<dev.size()-1; j++) {
					
							if(dev.get(j).getPeso()<dev.get(j+1).getPeso()) {
								
								aux = dev.get(j);
								dev.set(j, dev.get(j+1)); 
								dev.set(j+1, aux);
							}
						}
					}
					this.getPlantada().getHuerto()[posicion[0]][posicion[1]] = null;
					Huerta.getLocalizadas().get(pos).getHuerto()[0][0] = this;
					this.setPlantada(Huerta.getLocalizadas().get(pos));
					this.posicion[0]=0;
					this.posicion[1]=0;
					
					return dev;
				}
				else {
					
					this.getPlantada().getHuerto()[posicion[0]][posicion[1]] = null;
					Huerta.getLocalizadas().get(pos).getHuerto()[0][0] = this;
					this.setPlantada(Huerta.getLocalizadas().get(pos));
					this.posicion[0]=0;
					this.posicion[1]=0;
	
					return dev; 
				}
			}
			
			//SI NO ESTA LOCALIZADA O NO ESTA PLANTADA
			if (this.getPlantada() == null || !(Huerta.getLocalizadas().contains(this.getPlantada()))){ 
			
				dev = super.recolecta();
				return dev;
			}
		}
		else { //si no hay frutos
			
			return dev; 
		}
		return dev; 
	}
	
	public void arranca() {
		
		if (this.getPlantada().getCuidador() != null && this.getPlantada().getCuidador() instanceof Inmune) { 
			
			
			this.getPlantada().getHuerto()[posicion[0]][posicion[1]] = null;
			((Planta)this).setPlantada(null);
			posicion = null;
			
		}
		else if(this.getPlantada().getCuidador() != null && !(this.getPlantada().getCuidador() instanceof Zombie)) {
			
			Zombie zomb = new Zombie(this.getPlantada().getCuidador());
			this.getPlantada().setCuidador(zomb);
		}
	}

	public void setPlantada(Huerta huert) {
			
	
		boolean salir = false;
		super.setPlantada(huert);
		
		if(huert != null) { 
			
			for(int i=0; i<huert.getHuerto().length && salir ==false ;i++) {
				
				for(int j=0; j<huert.getHuerto()[i].length && salir ==false;j++) {
					
					if(huert.getHuerto()[i][j] != null && huert.getHuerto()[i][j] == (this)) {
						
						posicion = new int[2];
						posicion[0] = i;
						posicion[1] = j;
						salir = true;
					}
				}
			}
		}	
		else
			posicion = null;
	}
	
	
	public boolean otea() {
		
	boolean salir = false;
	boolean salir2 = false;
	boolean dev = false; 
	
		if(this.getPlantada() == null) {
			
			for(int i=0; i<Huerta.getLocalizadas().size() && salir == false;i++) {
				
				if(Huerta.getLocalizadas().get(i) != null && Huerta.getLocalizadas().get(i).getCuidador() == null) {
					
					salir = true;
					
					for(int a=0; a<Huerta.getLocalizadas().get(i).getHuerto().length && salir2==false;a++) {
						
						for(int b=0; b<Huerta.getLocalizadas().get(i).getHuerto()[a].length && salir2==false; b++) {
							
							if(Huerta.getLocalizadas().get(i).getHuerto()[a][b] == null) {
								
								Huerta.getLocalizadas().get(i).getHuerto()[a][b] = this;
								this.setPlantada(Huerta.getLocalizadas().get(i)); 
								this.setEstado("adulta"); 
								dev = true;
								salir2 = true;
							}
						}
					}
				}
			}
		}
		return dev;
	}
	
	
	public int abona() {
		
		int dev = 0;
		
		if(this.getPlantada() != null && this.getPlantada().getHuerto() != null) {
		
		int i = posicion[0];
		int j = posicion[1];
				
		int filas = this.getPlantada().getHuerto().length;
		int col = this.getPlantada().getHuerto()[0].length; 
		
	
		if(i-1>=0 && j-1 >= 0 && this.getPlantada().getHuerto()[i-1][j-1] != null 
				&& !(this.getPlantada().getHuerto()[i-1][j-1] instanceof Trifido)) { 	
			

			this.getPlantada().getHuerto()[i-1][j-1].setPlantada(null);
			
			this.getPlantada().getHuerto()[i-1][j-1] = null;
			dev++;
		}
		
		if(i-1>=0 && this.getPlantada().getHuerto()[i-1][j] != null 
				&& !(this.getPlantada().getHuerto()[i-1][j] instanceof Trifido)) { 
			

			this.getPlantada().getHuerto()[i-1][j].setPlantada(null);
			this.getPlantada().getHuerto()[i-1][j] = null;
			dev++;
		}
			
		if(i-1>=0 && j+1 <= col-1 && this.getPlantada().getHuerto()[i-1][j+1] != null 
				&& !(this.getPlantada().getHuerto()[i-1][j+1] instanceof Trifido)) { 
			

			this.getPlantada().getHuerto()[i-1][j+1].setPlantada(null);
			this.getPlantada().getHuerto()[i-1][j+1] = null;
			dev++;
		}
		
		if(j-1 >= 0 && this.getPlantada().getHuerto()[i][j-1] != null 
				&& !(this.getPlantada().getHuerto()[i][j-1] instanceof Trifido)) { 
			

			this.getPlantada().getHuerto()[i][j-1].setPlantada(null);
			this.getPlantada().getHuerto()[i][j-1] = null;
			dev++;
		}
			
		if(j+1 <= col-1 && this.getPlantada().getHuerto()[i][j+1] != null 
				&& !(this.getPlantada().getHuerto()[i][j+1] instanceof Trifido)) { 
			

			this.getPlantada().getHuerto()[i][j+1].setPlantada(null);
			this.getPlantada().getHuerto()[i][j+1] = null;
			dev++;
		}
		
		if(j-1 >=0 && i+1 <= filas-1 && this.getPlantada().getHuerto()[i+1][j-1] != null 
				&& !(this.getPlantada().getHuerto()[i+1][j-1] instanceof Trifido)) { 
			

			this.getPlantada().getHuerto()[i+1][j-1].setPlantada(null);
			this.getPlantada().getHuerto()[i+1][j-1] = null;
			dev++;
		}
		
		if(i+1 <= filas-1 && this.getPlantada().getHuerto()[i+1][j] != null 
				&& !(this.getPlantada().getHuerto()[i+1][j] instanceof Trifido)) { 
			

			this.getPlantada().getHuerto()[i+1][j].setPlantada(null);
			this.getPlantada().getHuerto()[i+1][j] = null;
			dev++;
		}
		
		if(i+1 <= filas-1 && j+1 <= col-1 && this.getPlantada().getHuerto()[i+1][j+1] != null 
				&& !(this.getPlantada().getHuerto()[i+1][j+1] instanceof Trifido)) { 
			

			this.getPlantada().getHuerto()[i+1][j+1].setPlantada(null);
			this.getPlantada().getHuerto()[i+1][j+1] = null;
			dev++;
		}
		
		int frutos = dev;
		
		if(frutos > 0 && !(this.getEstado().equals("adulta"))) {
			
			this.setEstado("adulta");
			frutos--;
		}
		
		
		for(int k=0; k<frutos; k++) {
			
			boolean sal_fr = false;
			Fruto f = new Fruto(this.getFruto()); 
			
			for(int g=0; g<this.getFrutos().length && sal_fr == false;g++) {
				
				if(this.getFrutos()[g] == null) {
					
					this.getFrutos()[g] = f;
					sal_fr = true;
				}
			}		
		}
		
		}
		return dev;
	}
	
	public int[] getPosicion() {
		
		return posicion;
	}

}