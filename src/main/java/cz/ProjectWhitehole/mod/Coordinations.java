package cz.ProjectWhitehole.mod;

public class Coordinations {
	private int x,y,z;
	private boolean validated;
	
	public Coordinations(String x, String y, String z){
		try {
			this.x = Integer.parseInt(x);
			this.y = Integer.parseInt(y);
			this.z = Integer.parseInt(z);
			validated = true;
		} catch (NumberFormatException e) {
			validated = false;				
		}		
	}
	
	public Coordinations(int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
		validated = true;
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public int getZ(){
		return this.z;
	}
	public boolean areValid(){
		return validated;
	}
}
