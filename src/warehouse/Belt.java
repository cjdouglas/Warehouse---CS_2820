import java.util.ArrayList;
import java.util.LinkedList;

// Leon Grund
public class Belt {

	private boolean moves = false;
	private ArrayList<Item> itemList;
	//private LinkedList<int[]> xyList;
	private int beltCapacity;
	private int pickerX;
	private int pickerY;
	private int packerX;
	private int packerY;
	
	public Belt(int startX, int startY, int endX, int endY, int capacity){
		itemList = new ArrayList<Item>();
		//xyList = new LinkedList<int[]>();
		//xyList.add(new int[] {x,y});
		beltCapacity = capacity;
		pickerX = startX;
		pickerY = startY;
		packerX = endX;
		packerY = endY;
	}
	
	//getters
	public boolean isMoving(){return moves;}
	public int numOfItems(){return itemList.size();}
	public Item getItem(int id){return itemList.get(id);}
	public ArrayList<Item> getOrder(){return itemList;}
	//public LinkedList<int[]> coordinates(){return xyList;}
	//public int x(){return currentX;}
	//public int y(){return currentY;}
	
	
	//setters
	public void addItem(Item item){
		if(itemList.size()!= beltCapacity)itemList.add(item);
		item.setID(itemList.indexOf(item));
	}
	//public void nextStation(){;}
	//remove
	public void removeItem(Item I){if(beltCapacity != 0)itemList.remove(I.getID());}
	public void clearBelt(){itemList.clear();}
	
}
