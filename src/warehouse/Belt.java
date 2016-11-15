package warehouse;

import java.util.LinkedList;
import java.util.List;
import java.awt.Point;

import warehouse.Floor;
import warehouse.Item;
//import warehouse.Point;

public class Belt implements Tickable {
	Floor F;
	List<Point> beltArea;
	private Point beltLocation;
	private Point pickLocation;
	private Point packLocation;
	private LinkedList<Item> itemList;
	private int beltCapacity;
	
	/**
	 * @author Leon Grund
	 * @param Floor Object
	 * Floor is needed to find location of belt area, cells etc.
	 */
	public Belt(Floor F){
		this.F = F;
		List<Point> beltArea = F.getBeltArea();
		Point beltLocation = F.getBeltLocation();
		Point pickLocation = F.getPickLocation();
		Point packLocation = F.getPackLocation();
		
		itemList = new LinkedList<Item>();
		beltCapacity = 6;
	}

	/*---------- G E T T E R S ----------*/
	/**
	   * public method to see whether belt is moving
	   * @return true if belt can be moved, is moving
	   */
	
	
	
	
	public void moveBin(Item I, Point Loc){I.setLocation(Loc);}
	
	/**
	   * public method to see the number of Items on the belt
	   * @return int, number of Items on belt
	   */
	public int numOfItems(){return itemList.size();}
	
	/**
	   * public method to get an item that is on the belt and remove it
	   * @param ID number of Item
	   * @return Item with corresponding ID number
	   */
	
	
	
	
	public void doPacker(){
		if(packLocation == itemList.getFirst().getLocation()){
			System.out.println("Remove: " + itemList.getFirst().getItemID());
			itemList.removeFirst();
			return;
		}
		System.out.println("Packer waiting for bin");

	}
	
	public void doPicker(Item I){
		if(numOfItems() == beltCapacity){System.out.println("Belt is full");return;}
		System.out.println("Item: " + I.getItemID() + " placed in bin");
		I.setLocation(pickLocation);
		itemList.addLast(I);}
	
	/**
	   * public method to see all items on the belt
	   * @return ArrayList of Items, every item on the belt 
	   */
	public LinkedList<Item> getOrder(){return itemList;}
	
	/**
	   * public method to see if the next order can start to be fulfilled
	   * @return true if the next order can start 
	   */
	public boolean isOrderComplete(){
		if(itemList.isEmpty()){return true;}
		return false;
		}
	

	/*---------- S E T T E R S ----------*/
	
	public void clearBelt(){itemList.clear();}
	
	/**
	   * the tick() method is where belt moving gets done;
	   * it will have to move any Bin or Parcel withing the Cell
	   * of a Belt area to the next Cell, and this has to be done
	   * on all Points of the beltarea in parallel (not coded yet here)
	   * 
	   * after moving the belt, tick() should check to see whether
	   * or not a Bin has arrived at the Packer - then doPacker() 
	   * should be called, which removes the Bin, creates a Parcel 
	   * and puts that Parcel on the belt (in more advanced versions,
	   * one Bin might make more than one Parcel, if Items are too 
	   * big to fit entirely into one Parcel). After the Parcel is
	   * in a Cell at the Packer, the belt will be stopped until some
	   * later tick when the Packer finishes the Parcel.
	   * 
	   * even fancier ideas are to give the Packer a queue of Bins
	   * and remove each Bin that arrives, taking some non-trivial
	   * number of ticks to make Parcels, returning them to the 
	   * beltarea when they are completed
	   * 
	   * and a really thorough Belt would simulate the shipping dock,
	   * collecting a lot of parcels and grouping them into a truck
	   *
	   */
	
	/**
	   * public tick belt
	   * @return tick belt
	   */
	  public void tick() {
		  System.out.println("----------Start: T I C K belt----------");
		  //doPicker()
		  boolean flag = false;
			  for(Item I: itemList){
				  System.out.println("Before Location " + I.getItemID() + ": " + I.getLocation());
				  for(Point P: beltArea){
					 if(flag){moveBin(I,P); flag= false;}
					 if(P == I.getLocation()) {flag = true;}	  
			  }
				  System.out.println("Before Location " + I.getItemID() + ": " + I.getLocation());
		  }
			  doPacker();
			  System.out.println("----------End: T I C K belt----------"); 
	    }
	  
	  /**
	   * Local method to see whether belt can be moved
	   
	  private boolean isMovable() {
		for (Point p: beltarea) {
		  Cell c = F.getCell(p);
		  Object o = c.getContents();
		  if (o == null) continue;  // skip empty cell
		  if ((o instanceof Bin) && !((Bin)o).isFinished()) return false;
		  if ((o instanceof Parcel) && !((Parcel)o).isFinished()) return false;
		  }
		return true;  // nothing stops belt from moving
	    }
	  */
	  /**
	   * Local method doPacker() simulates a Bin arriving to the 
	   * Packer via the belt moving. 
	   * 
	   */
	
	  
	  /**
	   * Called by Orders to check whether a new Bin can be safely started
	   */
	  public boolean binAvailable() {
		  if(numOfItems() != beltCapacity) return true;
		return false;
	    }
	  /**
	   * Called by Orders to simulate a Picker starting a new Bin
	  
	  public Bin getBin() {
		return null; 
	    }
	  */
}
