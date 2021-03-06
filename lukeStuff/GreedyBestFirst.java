
public class GreedyBestFirst {
	Point start;
	Point goal;
	char[][] maze;
	String input;
	
	public GreedyBestFirst(String input){
		start = Helper.getStart(input);
		goal = Helper.getGoal(input);
		maze = Helper.toCharArr(input);
		this.input = input;
	}
	
	/**
	 * Prints out the result of a Greedy Best First Search on the maze
	 */
	public void runOn(){
		Point foundGoal = search(Helper.toBooleanArr(maze), start, goal);
		Helper.printOutWithPath(Helper.toCharArr(input), foundGoal);
	}
	
	public static Point search(boolean[][] maze, Point start, Point goal){
		PriorityQueue frontier = new PriorityQueue();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
		int nodesExpanded = 0;
		while (! nextExpand.equals( goal ) ){
			//Start adding points to the frontier
			if ( maze[ x - 1 ] [ y ] ){
				frontier.insert (
					new Point( x - 1 , y , nextExpand ), 
					Helper.manhattanDistance( x - 1 , y , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y - 1 ]  ){
				frontier.insert (
					new Point( x , y - 1 , nextExpand ), 
					Helper.manhattanDistance( x , y - 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x + 1 ] [ y ]  ){
				frontier.insert (
					new Point( x + 1 , y , nextExpand ), 
					Helper.manhattanDistance( x + 1 , y , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y + 1 ]  ){
				frontier.insert (
					new Point( x , y + 1 , nextExpand ), 
					Helper.manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			//End adding nodes to the frontier

			//Disallowing returning to this location
			maze[ x ][ y ] = false;

			nodesExpanded ++;
			//Updating the next node to expand
			nextExpand = frontier.dequeue();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'
		System.out.println("Greedy Best First : " + nodesExpanded + " Nodes Expanded");
		return nextExpand;
		//Printing out the path
		//TODO: include print statement here
		//End print
	}


}
