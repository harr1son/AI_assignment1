package assignment1;

public class AStar {
	Point start;
	Point goal;
	char[][] maze;
	String input;
	
	public AStar(String input){
		start = Helper.getStart(input);
		goal = Helper.getGoal(input);
		maze = Helper.toCharArr(input);
		this.input = input;
	}

	/**
	 * Prints out the result of a A* Search on the maze
	 */
	public void runOn(){
		Point foundGoal = search(Helper.toBooleanArr(maze), start, goal);
		Helper.printOutWithPath(Helper.toCharArr(input), foundGoal);
	}
	
	public static int pathLength(char[][] maze, Point start, Point goal){
		Point foundGoal = search(Helper.toBooleanArr(maze), start, goal);
		int out = 0;
		while(foundGoal.parent != null){
			out++;
			foundGoal = foundGoal.parent;
		}
		return out;
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
					1 + nextExpand.distFromStart + Helper.manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y - 1 ]  ){
				frontier.insert (
					new Point( x , y - 1 , nextExpand ), 
					1 + nextExpand.distFromStart + Helper.manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x + 1 ] [ y ]  ){
				frontier.insert (
					new Point( x + 1 , y , nextExpand ), 
					1 + nextExpand.distFromStart + Helper.manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y + 1 ]  ){
				frontier.insert (
					new Point( x , y + 1 , nextExpand ), 
					1 + nextExpand.distFromStart + Helper.manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			//End adding nodes to the frontier

			//Disallowing returning to this location
			maze[ x ][ y ] = false;
			
			//Updating the next node to expand
			nextExpand = frontier.dequeue();
			nodesExpanded++;
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'
		//System.out.println("A* : " + nodesExpanded + " Nodes Expanded");
		return nextExpand;
		
		//Printing out the path
		//TODO: include print statement here
		//End print
	}

	
}

