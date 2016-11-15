package warehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;

import warehouse.Floor;
import warehouse.Item;
//import warehouse.Point;

public class Belt implements Tickable {
	Floor F;
	List<Point> beltArea;
	//private Point beltLocation;
	private Point pickLocation;
	private Point packLocation;
	private LinkedList<List<Item>> binList;
	private int beltCapacity;
	private ArrayList<Item> listOfItemOrder;
	private List<Item> itemOrder;
	
	/**
	 * @author Leon Grund
	 * @param Floor Object
	 * Floor is needed to find location of b
	 * elt area, cells etc.
	 */
	public Belt(Floor F){
		this.F = F;
		List<Point> beltArea = F.getBeltArea();
		//Point beltLocation = F.getBeltLocation();
		Point pickLocation = F.getPickLocation();
		Point packLocation = F.getPackLocation();
		
		binList = new LinkedList<List<Item>>();
		beltCapacity = beltArea.size();
	}

	/*---------- G E T T E R S ----------*/
	/**
	   * private method to advance a Bin on belt
	   * @void I, updates the items location 
	   */
	private void moveBin(List<Item> B, Point Loc){
		for(Item i: B)
		i.setLocation(Loc);
		}
	
	private boolean checkBin(List<Item> B, Point loc){
		for(Item i: B){
			if(i.getLocation() != loc){return false;}
		}
		return true;
	}
	
	// items to add to the bin for the current order
	public void addItemsToBin(ArrayList<Item> pickedItems){
		for(Item i: pickedItems){
			if(listOfItemOrder.contains(i)){
				itemOrder.add(i);
				listOfItemOrder.remove(i);
				
				continue;
			}else{
			System.out.println("Invalid Item Error: picked item received, not on order");}
		}
	}
	
	// this order has to be complete 
	public void newOrder(ArrayList<Item> orderItems){
		if(isOrderComplete()){
			System.out.println("Start new Order");
			listOfItemOrder = orderItems;
			return;
		}
		System.out.println("Order not complete Error: Can't start new order before finishing current order");
	}
	
	/**
	   * public method to see the number of Bins/Items on the belt
	   * @return int, number of Items on belt
	   */
	public int numOfBins(){return binList.size();}
	
	/**
	   * public method to get item ID
	   * @param I The item of which you want to find the ID
	   * @return int The ID to corresponding item
	   */
	public int itemID(Item I){return I.getItemID();}
	
	/**
	 * private method simulating a packer that removes bin from the belt
	 * void removes bin at packer station
	 * @void remove first bin of binList
	 */
	private void doPacker(){
		List<Item> toRemoveBin = binList.getFirst();
		if(checkBin(toRemoveBin, packLocation)){
			System.out.println("Bin at Packer Station: Removes Bin/Order: ");
			binList.removeFirst();
			return;
		}
		System.out.println("Packer waiting for bin to arrive");
	}
	
	/**
	 * method simulating a picker that puts an item in bin on the belt
	 * @param I The Item to be placed in bin on belt
	 * @void adds Item/Bin to the end of binList
	 */
	public void doPicker(){
		if(!binAvailable()){
			System.out.println("doPicker(I) Error: Belt full");
			return;
		}
		// Bin/Order is complete -> picker places bin/order on belt
		if(isOrderComplete()){
			System.out.println("Picker places complete bin/order on belt");
			moveBin(itemOrder, beltArea.get(0));
			binList.addLast(itemOrder);
			itemOrder.clear();
			System.out.println("Picker ready to do newOrder(AryList<Item>) and AraddItemsToBin()");
		}
	}
	
	/**
	   * public method to see all items for an order
	   * @return List of Items for an order
	   */
	public List<Item> getOrder(){return itemOrder;}
	
	/**
	   * public method to see if the next order can start to be fulfilled
	   * @return true if the next order can start 
	   */
	public boolean isOrderComplete(){
		if(listOfItemOrder.isEmpty())return true;
		else return false;
		}

	  public boolean binAvailable() {
		  if(numOfBins() < beltCapacity) return true;
		  else return false;
	    }

	/*---------- S E T T E R S ----------*/
	
	public void clearBelt(){binList.clear();}
	
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
	   * public tick belt, moves belt by one point,
	   *  places an complete order/bin on belt (picker station)
	   *  removes an order/ bin at packer station
	   * @return tick belt
	   */
	  public void tick() {
		  System.out.println("----------Start: T I C K belt----------");
		  System.out.println("Order Complete?: " + isOrderComplete());
		  
		  doPicker();
		  
		  //------Advances all bins on belt by one point------
		  boolean flag = false;
			  for(List<Item> I: binList){
				  System.out.println("Order/Bin move one point" + Arrays.toString(I.toArray()));
				  for(Point P: beltArea){
					 if(flag){moveBin(I,P); flag= false; break;}
					 if(checkBin(I,P)){flag = true;}
				  }
				  if(flag){moveBin(I,packLocation); flag=false;}
		  }
		//-------Belt done moving-----
			 
		// call a packer to check if a bin arrived at packer station	 
		doPacker();
		
		System.out.println("----------End: T I C K belt----------"); 
	    }
	  }
