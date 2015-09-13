package assignment1;

public class BreadthFirst {
	Point start;
	Point goal;
	char[][] maze;
	String input;
	
	public BreadthFirst(String input){
		start = Helper.getStart(input);
		goal = Helper.getGoal(input);
		maze = Helper.toCharArr(input);
		this.input = input;
	}
	
	/**
	 * Prints out the result of a Breadth First Search on the maze
	 */
	public void runOn(){
		Point foundGoal = search(Helper.toBooleanArr(maze), start, goal);
		Helper.printOutWithPath(Helper.toCharArr(input), foundGoal);
	}
	
	public static Point search(boolean[][] maze, Point start, Point goal){
		Queue frontier = new Queue();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
		int nodesExpanded = 0;
		while (! nextExpand.equals( goal ) ){
			//Start adding points to the frontier
			if ( maze[ x - 1 ] [ y ] ){
				frontier.enqueue(
					new Point( x - 1 , y , nextExpand )
					);
			}
			if ( maze[ x ] [ y - 1 ]  ){
				frontier.enqueue(
					new Point( x , y - 1 , nextExpand )
					);
			}
			if ( maze[ x + 1 ] [ y ]  ){
				frontier.enqueue(
					new Point( x + 1 , y , nextExpand )
					);
			}
			if ( maze[ x ] [ y + 1 ]  ){
				frontier.enqueue(
					new Point( x , y + 1 , nextExpand )
					);
			}
			//End adding nodes to the frontier
			
			//Disallowing returning to this location
			maze[ x ][ y ] = false;

			//Updating the next node to expand
			nodesExpanded ++;
			nextExpand = frontier.dequeue();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		System.out.println("Breadth First Search : " + nodesExpanded + " Nodes Expanded");
		//By here, the nextExpand node, and sequent parents, are a path from 'S' to 'G'
		return nextExpand;
	}
}
