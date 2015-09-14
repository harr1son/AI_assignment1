package assignment1;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class StateWithCheese {
	StateWithCheese parentState;
	ArrayList<Point> containsCheese;
	ArrayList<Point> eatenCheese;
	ArrayList<Point> path;
	char[][] maze;
	Point currentLocation;
	final int depth;
	final int heuristic;
	final int fit;
	public void printPath(){
		//System.out.println(currentLocation);
		StateWithCheese nav = parentState;
		while(nav.parentState != null){
			//System.out.println(nav.currentLocation);
			nav = nav.parentState;
		}
	}
	public StateWithCheese(){
		heuristic = 0;
		fit = 0;
		depth = 0;
	}
	public StateWithCheese(String inputText){
		maze = Helper.toCharArr(inputText);
		depth = 0;
		containsCheese = new ArrayList<Point>();
		eatenCheese = new ArrayList<Point>();
		path = new ArrayList<Point>();
		currentLocation = Helper.getStart(inputText);
		containsCheese = Helper.findCheese(maze);
		heuristic = heuristic();
		fit = heuristic + depth;
	}
	/**
	 * Constructor that makes child state from parent state and move.
	 * Assumes move is legal. If not, will break structure of maze. 
	 * Moves are:
	 * 		'l' for left
	 * 		'r' for right
	 * 		'u' for up
	 * 		'd' for down
	 */
	public StateWithCheese(StateWithCheese parentState, char move){
		this.parentState = parentState;
		path = new ArrayList<Point>(parentState.path);
		maze = parentState.maze;
		//START updating location
		if(move == 'l'){
			currentLocation = new Point(
					parentState.currentLocation.x - 1, 
					parentState.currentLocation.y
					);
		}
		
		else if(move == 'r'){
			currentLocation = new Point(
					parentState.currentLocation.x + 1, 
					parentState.currentLocation.y
					);
		}

		else if(move == 'u'){
			currentLocation = new Point(
					parentState.currentLocation.x, 
					parentState.currentLocation.y + 1
					);
		}

		else if(move == 'd'){
			currentLocation = new Point(
					parentState.currentLocation.x, 
					parentState.currentLocation.y - 1
					);
		}
		else{
			//For our own benefit
			throw new IllegalArgumentException("Misused own code");
		}
		path.add(currentLocation);
		//END updating location
		
		depth = parentState.depth + 1; 
		//System.out.println("Depth: " +depth);
		containsCheese = new ArrayList<Point>();
		eatenCheese = new ArrayList<Point>(parentState.eatenCheese);
		for(int index = 0; index < parentState.containsCheese.size(); index++){
			if( parentState.containsCheese.get(index) . equals(currentLocation) ){
				eatenCheese.add( parentState.containsCheese.get(index) );
				//System.out.println("Eaten Cheese at " +  parentState.containsCheese.get(index));
			}
			else{
				containsCheese.add( parentState.containsCheese.get(index)  );
			}
		}
		
		heuristic = heuristic();
		fit = depth + heuristic;
		//System.out.println("Location " + currentLocation);
		//System.out.println("Fitness " + fit);
	}
	
	/**
	 * Returns the shortest A* distance from the current location through all 
	 * of the cheese locations. This heuristic is NOT admissible.
	 */
	//DO NOT DELETE
	public int heuristic(){
		//Creating shallow copy of ArrayList to pop values off 
		ArrayList<Point> notChecked = new ArrayList<Point>(containsCheese);

		PrintStream originalStream = System.out;

		PrintStream dummyStream = new PrintStream(new OutputStream(){
		    public void write(int b) {

		    }
		});
		System.setOut(dummyStream);
		
		int out = 0; //A running total
		//System.out.println(notChecked.size());
		Point nav = currentLocation;
		int counter = 0;
		while( notChecked.size() != 0 ){
			counter++;
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			for(int index = 0; index < notChecked.size(); index++){
				int toCompare = AStar.pathLength(maze, nav , notChecked.get(index) );
				if( toCompare < min ) {
					minIndex = index;
					min = toCompare;
				}
			}
			out += min;
			nav = notChecked.get(minIndex);
			notChecked.remove(minIndex);
		}

		/*if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}*/
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		System.setOut(originalStream);
		
		return out ;
	}
	
	//Probably our best (almost) admissible heuristic
	public int heuristicOther(){
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		//Suppressing output for the AStar
		PrintStream originalStream = System.out;

		PrintStream dummyStream = new PrintStream(new OutputStream(){
		    public void write(int b) {

		    }
		});
		System.setOut(dummyStream);
		//End output supression code
		for(int index = 0; index < containsCheese.size(); index++){
			//int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );
			int toCompare = 0;// BreadthFirst.search()
			
			Point found = AStar.search(
					Helper.toBooleanArr(maze), 
					currentLocation, 
					containsCheese.get(index));
			
			while(found.parent != null){
				found = found.parent;
				toCompare++;
			}
			if( toCompare > max) {
				max = toCompare;
				maxIndex = index;
			}
		}
		
		Point farthest = containsCheese.get(maxIndex); //Farthest Cheese

		int farthestFromFarthestDistance = Integer.MIN_VALUE;
		int farthestFromFarthestIndex = -1;
		
		for(int index = 0; index < containsCheese.size(); index++){
			int toCompare = 0;
			Point found = AStar.search(
					Helper.toBooleanArr(maze), 
					farthest, 
					containsCheese.get(index));
			
			while(found.parent != null){
				found = found.parent;
				toCompare++;
			}
			if( toCompare > farthestFromFarthestDistance) {
				farthestFromFarthestDistance = toCompare;
				farthestFromFarthestIndex = index;
			}
		}
		

		Point farthestFromFarthest = containsCheese.get(farthestFromFarthestIndex); //Farthest Cheese
		
		int out = AStar.pathLength(maze, currentLocation, farthestFromFarthest) +
				AStar.pathLength(maze, farthestFromFarthest, farthest)  
				+containsCheese.size();
				;
		if(out < 0){
			return 0;
		}
		System.setOut(originalStream);
		return out ;
	}
	
	public boolean isGoal(){
		return containsCheese.size() == 0;
	}
	
	public boolean equals(StateWithCheese that){
		if(eatenCheese.size() != that.eatenCheese.size()){
			return false;
		}
		else if ( ! currentLocation.equals(that.currentLocation) ){
			return false;
		}
		else{
			for(Point cheese : eatenCheese){
				boolean inOther = false;
				for(Point otherCheese : that.eatenCheese){
					if (cheese.equals(otherCheese)){
						inOther = true;
						break;
					}
				}
				if(!inOther){
					return false;
				}
			}
			return true;
		}
	}
	
}
