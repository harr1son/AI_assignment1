package assignment1;

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
	 * Returns the shortest manhatten distance from the current location through all 
	 * of the cheese locations. 
	 */
	public int heuristic1(){
		//Creating shallow copy of ArrayList to pop values off 
		ArrayList<Point> notChecked = new ArrayList<Point>(containsCheese);
		
		int out = 0; //A running total
		//System.out.println(notChecked.size());
		Point nav = currentLocation;
		int counter = 0;
		while( notChecked.size() != 0 ){
			counter++;
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

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		
		return out ;
	}
	
	public int heuristic2(){
		//Creating shallow copy of ArrayList to pop values off 
		ArrayList<Point> notChecked = new ArrayList<Point>(containsCheese);
		
		int total = 0; //A running total
		int longest = -1;
		
		Point nav = currentLocation;
		int counter = 0;
		while( notChecked.size() != 0 ){
			counter++;
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			for(int index = 0; index < notChecked.size(); index++){
				int toCompare = Helper.manhattanDistance( nav , notChecked.get(index) );
				if( toCompare < min ) {
					minIndex = index;
					min = toCompare;
				}
			}
			total += min;
			nav = notChecked.get(minIndex);
			notChecked.remove(minIndex);
			if(min > longest && counter != 0){
				longest = min;
			}
		}
		//System.out.println(longest);
		//System.out.println(total - longest);
		int out = total - longest ;

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic3(){
		int total = 0;
		for(Point cheese : containsCheese){
			total += Helper.manhattanDistance(currentLocation, cheese);
		}
		
		int out = total;

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristics4(){
		return containsCheese.size()*containsCheese.size();
	}
	
	public int heuristic5(){
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		
		for(int index = 0; index < containsCheese.size(); index++){
			int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );
			
			if( toCompare > max) {
				max = toCompare;
				maxIndex = index;
			}
			
			if( toCompare < min) {
				min = toCompare;
				minIndex = index;
			}
		}
			
		
		int out = containsCheese.size() + Helper.manhattanDistance( 
						containsCheese.get(minIndex) , 
						containsCheese.get(maxIndex) );
		

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic6(){
		//Creating shallow copy of ArrayList to pop values off 
		ArrayList<Point> notChecked = new ArrayList<Point>(containsCheese);
		
		int out = 0; //A running total
		//System.out.println(notChecked.size());
		Point nav = currentLocation;
		int counter = 0;
		int max = -1;
		while( notChecked.size() != 0 ){
			counter++;
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
			if(min > max){
				max = min;
			}
			nav = notChecked.get(minIndex);
			notChecked.remove(minIndex);
		}
		if (max > 0){
			out -= max;
		}
		

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		
		return out ;
	}
	
	public int heuristic7(){
		return containsCheese.size() ;
	}
	
	public int heuristic8(){
		if(containsCheese.size() == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int index = 0; index < containsCheese.size(); index++){
			int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );
			if( toCompare > max) {
				max = toCompare;
				maxIndex = index;
			}
			if( toCompare < min) {
				min = toCompare;
				minIndex = index;
			}
		}
		
		ArrayList<Point> notChecked = new ArrayList<Point>(containsCheese);
		Point furthest = notChecked.remove(maxIndex);
		//System.out.println(furthest);
		int awayCount = 0;
		for(Point other : notChecked){
			//Checks if they are displaced in the same direction
			if( (currentLocation.x - furthest.x)*(currentLocation.x - other.x) <= 0 ){
				awayCount++;
			}
			if( (currentLocation.y - furthest.y)*(currentLocation.y - other.y) <= 0 ){
				awayCount++;
			}
		}
		int out = awayCount + max;
		

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			//return 0;
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		
		return out;
	}

	public int heuristic9(){
		return 0; 
	}
	
	public int heuristic10(){
		if(containsCheese.size() == 0){
			return 0; 
		}
		int furthestDistance = 0;
		for(Point p : containsCheese){
			int potential = Helper.manhattanDistance(currentLocation, p);
			if(furthestDistance < potential ){
				furthestDistance = potential;
			}
		}
		int out = furthestDistance;

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic11(){
		if(containsCheese.size() == 0){
			return 0;
		}
		
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		//Start by finding the furthest cheese
		for(int index = 0; index < containsCheese.size(); index++){
			int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );
			if( toCompare > max) {
				max = toCompare;
				maxIndex = index;
			}
		}
		
		int deviations = 0;
		Point furthestCheese = containsCheese.get(maxIndex);
		int xDisplacementFurthest = currentLocation.x - furthestCheese.x;
		int yDisplacementFurthest = currentLocation.y - furthestCheese.y;
		for( Point other : containsCheese ){
			int xDisplacementOther = currentLocation.x - other.x;
			int yDisplacementOther = currentLocation.y - other.y;
			if( xDisplacementOther * xDisplacementFurthest < 0 ){
				//System.out.println("Deviation in X");
				deviations ++;
			}
			else if( yDisplacementOther * yDisplacementFurthest < 0 ){
				//System.out.println("Deviation in Y");
				deviations ++;
			}
		}
		int out = deviations + max;
		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic12(){
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0; 
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;

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
		
		/*Point found = BreadthFirst.search(
				Helper.toBooleanArr(maze), 
				currentLocation, 
				containsCheese.get(maxIndex));*/
		int out = max;
		/*while(found.parent != null){
			found = found.parent;
			out++;
		}*/

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic13(){
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0; 
		}
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for(int index = 0; index < containsCheese.size(); index++){
			int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );

			if( min > toCompare) {
				min = toCompare;
				minIndex = index;
			}
		}
		
		Point found = BreadthFirst.search(
				Helper.toBooleanArr(maze), 
				currentLocation, 
				containsCheese.get(minIndex));
		int out = 0;
		//Helper.printOutWithPath(maze, found);
		while(found.parent != null){
			found = found.parent;
			out++;
		}

		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out + containsCheese.size() - 1;
	}
	
	public int heuristic14() {
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		
		for(int index = 0; index < containsCheese.size(); index++){
			int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );
			
			if( toCompare > max) {
				max = toCompare;
				maxIndex = index;
			}
			
			if( toCompare < min) {
				min = toCompare;
				minIndex = index;
			}
		}
		
		Point furthestCheese = containsCheese.get(maxIndex);
		Point closestCheese = containsCheese.get(minIndex);
		
		int out = Helper.manhattanDistance( currentLocation, furthestCheese);
		

		int xDisplacementFurthest = currentLocation.x - furthestCheese.x;
		int yDisplacementFurthest = currentLocation.y - furthestCheese.y;
		
		int xDisplacementClosest = currentLocation.x - closestCheese.x;
		int yDisplacementClosest = currentLocation.y - closestCheese.y;
		//Check to see if displaced in same direction
		if(xDisplacementFurthest * xDisplacementClosest < 0){
			out += 2 * Math.abs(xDisplacementClosest);
		}
		else if( Math.abs(xDisplacementClosest) > Math.abs(xDisplacementFurthest) ){
			out += 2 * (Math.abs(xDisplacementClosest) - Math.abs(xDisplacementFurthest));
		}
		if(yDisplacementFurthest * yDisplacementClosest < 0){
			out += 2 * Math.abs(yDisplacementClosest);
		}
		else if( Math.abs(yDisplacementClosest) > Math.abs(yDisplacementFurthest) ){
			out += 2 * (Math.abs(yDisplacementClosest) - Math.abs(yDisplacementFurthest));
		}
		if (parentState != null && eatenCheese.size() < parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic15(){
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		
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
			if(toCompare < min){
				min = toCompare;
				minIndex = index;
			}
		}
		
		Point minPoint = containsCheese.get(minIndex);
		Point maxPoint = containsCheese.get(maxIndex);
		
		
		int minX = minPoint.x;
		int minY = minPoint.y;
		
		int maxX = maxPoint.x;
		int maxY = maxPoint.y;
		
		int out = min + AStar.pathLength(maze, minPoint, maxPoint);
		

		if (parentState != null && eatenCheese.size() > parentState.eatenCheese.size()){
			out --;
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic16(){
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		
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
			if(toCompare < min){
				min = toCompare;
				minIndex = index;
			}
		}
		
		Point minPoint = containsCheese.get(minIndex);
		Point maxPoint = containsCheese.get(maxIndex); //Furthest Cheese
		

		int max2 = Integer.MIN_VALUE;
		int maxIndex2 = -1;
		
		for(int index = 0; index < containsCheese.size(); index++){
			//int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );
			int toCompare = 0;// BreadthFirst.search()
			Point found = AStar.search(
					Helper.toBooleanArr(maze), 
					maxPoint, 
					containsCheese.get(index));
			
			while(found.parent != null){
				found = found.parent;
				toCompare++;
			}
			if( toCompare > max2) {
				max2 = toCompare;
				maxIndex2 = index;
			}
		}
		

		Point maxPoint2 = containsCheese.get(maxIndex2); //Furthest Cheese
		
		
		int minX = minPoint.x;
		int minY = minPoint.y;
		
		int maxX = maxPoint.x;
		int maxY = maxPoint.y;
		
		int out = AStar.pathLength(maze, currentLocation, maxPoint2) +
				AStar.pathLength(maze, maxPoint2, maxPoint);
		

		if (parentState != null && eatenCheese.size() > parentState.eatenCheese.size()){
			out --;
			//System.out.println("REDUCE");
		}
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
	}
	
	public int heuristic(){
		//Creating shallow copy of ArrayList to pop values off 	
		if(containsCheese.size() == 0){
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		
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
			if(toCompare < min){
				min = toCompare;
				minIndex = index;
			}
		}
		
		Point minPoint = containsCheese.get(minIndex);
		Point maxPoint = containsCheese.get(maxIndex); //Furthest Cheese
		

		int max2 = Integer.MIN_VALUE;
		int maxIndex2 = -1;
		
		for(int index = 0; index < containsCheese.size(); index++){
			//int toCompare = Helper.manhattanDistance( currentLocation , containsCheese.get(index) );
			int toCompare = 0;// BreadthFirst.search()
			Point found = AStar.search(
					Helper.toBooleanArr(maze), 
					maxPoint, 
					containsCheese.get(index));
			
			while(found.parent != null){
				found = found.parent;
				toCompare++;
			}
			if( toCompare > max2) {
				max2 = toCompare;
				maxIndex2 = index;
			}
		}
		

		Point maxPoint2 = containsCheese.get(maxIndex2); //Furthest Cheese
		
		
		int minX = minPoint.x;
		int minY = minPoint.y;
		
		int maxX = maxPoint.x;
		int maxY = maxPoint.y;
		
		int out = AStar.pathLength(maze, currentLocation, maxPoint2);
		

		/*if (parentState != null && eatenCheese.size() > parentState.eatenCheese.size()){
			out --;
			
			//System.out.println("\n\n REDUCE \n\n");
			//containsCheese.get(-1);
		}*/
		if(out < 0){
			System.out.println("BAD HEURISTIC");
			containsCheese.get(-1);
		}
		return out;
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
