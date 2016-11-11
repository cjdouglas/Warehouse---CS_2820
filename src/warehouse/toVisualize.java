package warehouse;
import java.util.*;
/**
 * @author Andrew Willette
 *
 *         Group A2 - Visualizer Class
 */

public interface toVisualize {

	/**
	 * I am at this point assuming we will have a class point where
	 * each component type have a Point instance attributed to it representing it's 
	 * placement
	 */
	
	
	/**
	 * when called, stores all points attributed to Robots at current time
	 */
	ArrayList<Point> getRobotLocations();
	
	/**
	 * returns number of shelves in use
	 */
	//int getShelfCount();
	
	/**
	 * Shelves must have some way of distinguishing themselves from one another
	 * At this point I am assuming it'll be id#'s, such as 1,2,3,4... how ever many
	 * are required or instantiated as necessary
	 * Below method will return shelf of desired id#
	 */
	
	//Shelf getShelf(int shelfNumber);
	
	
	
}
