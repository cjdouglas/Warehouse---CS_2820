package warehouse;

// Leon Grund
public class ItemTest {
	private boolean bin;
	private boolean packag;
	private int ID;
	private int x,y;
	private String progress;
	
	public ItemTest(int item, int id){
		if(item == 0){bin = true;}
		if(item == 1){packag = true;}
		//ID = id;
	}
	
	//setters
	public void setID(int id){ID = id;}
	public void updateLocation(int x,int y){
		this.x = x;
		this.y = y;
	}
	public void setProgress(String prg){progress = prg;}
	
	//getters
	public int getID(){return ID;}
	public int[] getLocation(){return new int[] {x,y};}
	public String getProgess(){return progress;}
}
