import java.util.ArrayList;
import java.util.Random;

public class Mines {

	
	private int gridX;
	private int gridY;
	
	
	public Mines() {
		super();
	}

	public Mines(int gridX, int gridY) {
		super();
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public int getGridX() {
		return gridX;
	}
	public void setGridX(int gridX) {
		this.gridX = gridX;
	}
	public int getGridY() {
		return gridY;
	}
	public void setGridY(int gridY) {
		this.gridY = gridY;
	}
	
	public Mines mineGenerator(){
		return 	new Mines(new Random().nextInt(8), new Random().nextInt(8));
	}
	
	public ArrayList<Mines> minesList(int total){
		
		ArrayList<Mines> list = new ArrayList<Mines>();
		for(int i=0; i<total; i++){
			list.add(mineGenerator());
		}
		return list;
	}
	
}
