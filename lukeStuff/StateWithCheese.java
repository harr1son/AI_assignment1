package assignment1;

import java.util.ArrayList;

public class StateWithCheese {
	StateWithCheese parentState;
	ArrayList<Point> containsCheese;
	char[][] maze;
	Point currentLocation;
	int depth;
	int heuristic;
	int fit;
	public void printPath(){
		System.out.println(currentLocation);
		StateWithCheese nav = parentState;
		while(nav.parentState != null){
			System.out.println(nav.currentLocation);
			nav = nav.parentState;
		}
	}
	public StateWithCheese(String inputText){
		maze = Helper.toCharArr(inputText);
		depth = 0;
		containsCheese = new ArrayList<Point>();
		currentLocation = Helper.getStart(inputText);
		containsCheese = Helper.findCheese(maze);
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
		//END updating location
		
		depth = parentState.depth + 1; 
		containsCheese = new ArrayList<Point>();
		for(int index = 0; index < parentState.containsCheese.size(); index++){
			if( ! parentState.containsCheese.get(index) . equals(currentLocation) ){
				containsCheese.add( parentState.containsCheese.get(index)  );
			}
		}
		
		heuristic = heuristic();
		fit = depth + heuristic;
	}
	
	/**
	 * Returns the shortest manhatten distance from the current location through all 
	 * of the cheese locations. 
	 */
	public int heuristic(){
		//Creating shallow copy of ArrayList to pop values off 
		ArrayList<Point> notChecked = new ArrayList<Point>(containsCheese);
		int out = 0; //A running total
		Point nav = currentLocation;
		while( notChecked.size() != 0 ){
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			for(int index = 0; index < notChecked.size(); index++){
				int toCompare = Helper.manhattanDistance( nav , notChecked.get(index) );
				if( toCompare < min ) {
					minIndex = index;
					min = toCompare;
				}
			}
			out += min;
			nav = notChecked.get(minIndex);
			notChecked.remove(minIndex);
		}
		
		return out;
	}
	
	public boolean isGoal(){
		return containsCheese.size() == 0;
	}
	
}
