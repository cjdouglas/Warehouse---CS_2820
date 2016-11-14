package warehouse;
import java.util.*;
import java.awt.*;
/**
 * @author Andrew Willette
 *
 *         Group A2 - Visualizer Class
 */
/**
 * To connect visualizer GUI with master component
 */
public interface toVisualize {


	
	/**
	 * 
	 * @return ArrayList of Robots in the simulation
	 */
	public ArrayList<Robot> getRobotList();
	
	/**
	 * To be called only on instantiation of visualizer to collect static members of board
	 * @return new Floor();
	 */
	public Floor getFloor();
	
	
}
