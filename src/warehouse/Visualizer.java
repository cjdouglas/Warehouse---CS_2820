package warehouse;
import java.util.*;
import javax.swin

public class Visualizer implements toVisualize {
	ArrayList<Robot> Robots;
	Floor gameBoard;
	/**
	 * @author Andrew Willette
	 *
	 *         Group A2 - Visualizer Class
	 */
	
	Visualizer(){
		this.Robots=getRobotList();
		gameBoard = getFloor();
		
	}
	
	public ArrayList<Robot> getRobotList(){
		return MasterController.getRobotList();
	}
	
	/**
	 * This should include some setup of Floor such that it contains 
	 * 1)Pick/packer Locations
	 * 2) Charger Locations
	 * 3) Belt
	 * 4) Shipping/Receiving Dock (Is this the same as picker/packers I don't know I'm in the dark on many things)
	 */
	public Floor getFloor(){
		return MasterController.getFloor();
	}
	
}


