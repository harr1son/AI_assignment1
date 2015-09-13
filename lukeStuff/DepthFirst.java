
public class DepthFirst {
	Point start;
	Point goal;
	char[][] maze;
	String input;

	public DepthFirst(String input){
		start = Helper.getStart(input);
		goal = Helper.getGoal(input);
		maze = Helper.toCharArr(input);
		this.input = input;
	}
	
	/**
	 * Prints out the result of a Depth First Search on the maze
	 */
	public void runOn(){
		Point foundGoal = search(Helper.toBooleanArr(maze), start, goal);
		Helper.printOutWithPath(Helper.toCharArr(input), foundGoal);
	}
	
	public static Point search(boolean[][] maze, Point start, Point goal){
		Stack frontier = new Stack();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
		int nodesExpanded = 0;
		while (! nextExpand.equals( goal ) ){
			//Start adding points to the frontier
			if ( maze[ x - 1 ] [ y ] ){
				frontier.push (
					new Point( x - 1 , y , nextExpand )
					);
			}
			if ( maze[ x ] [ y - 1 ]  ){
				frontier.push (
					new Point( x , y - 1 , nextExpand )
					);
			}
			if ( maze[ x + 1 ] [ y ]  ){
				frontier.push (
					new Point( x + 1 , y , nextExpand )
					);
			}
			if ( maze[ x ] [ y + 1 ]  ){
				frontier.push (
					new Point( x , y + 1 , nextExpand )
					);
			}
			//End adding nodes to the frontier

			//Disallowing returning to this location
			maze[ x ][ y ] = false;

			
			//Updating the next node to expand
			nodesExpanded++;
			nextExpand = frontier.pop();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'

		System.out.println("Depth First Search : " + nodesExpanded + " Nodes Expanded");
		return nextExpand;
		//Printing out the path
		//TODO: include print statement here
		//End print
	}

	
}
