//import java.util.Queue;
//import java.util.Stack;

/**TODO: 
* properly implement all of the data structures. 
* make sure it doesn't go back to prev expanded nodes
*
*
*/

public class Solvers{
	public static Point getStart(String inputText){
		int xOut = -1;
		int yOut = -1;
		String[] lines = inputText.split("\r\n|\r|\n");
		for(int y = 0; y < out.length; y++){
            for(int x = 0; x < out[y].length; x++){
                if (lines[y].charAt(x) == 'S'){
                	xOut = x;
                	yOut = y;
                	break;
                }
            }
        }
        return new Point(xOut, yOut);
	}
	public static Point getGoal(String inputText){
		int xOut = -1;
		int yOut = -1;
		String[] lines = inputText.split("\r\n|\r|\n");
		for(int y = 0; y < out.length; y++){
            for(int x = 0; x < out[y].length; x++){
                if (lines[y].charAt(x) == 'G'){
                	xOut = x;
                	yOut = y;
                	break;
                }
            }
        }
        return new Point(xOut, yOut);
	}
	public static boolean[][] ProcessInput(String inputText){
		/**
		* Process the input. Returns boolean matrix where true is legal location.
		*/
		String[] lines = inputText.split("\r\n|\r|\n");
		boolean[][] out = new boolean[lines.length][lines[0].length()];
        for(int y = 0; y < out.length; y++){
            for(int x = 0; x < out[y].length; x++){
                if (lines[y].charAt(x) != '%'){
					out[y][x] = true;
				}
            }
        }
        return out;
	}
	public static void breadthFirst(boolean[][] maze, Point start, Point goal){
		Queue frontier = new Queue();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
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

			//Updating the next node to expand
			nextExpand = frontier.dequeue();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'

		//Printing out the path
		//TODO: include print statement here
		//End print
	}

	public static void depthFirst(boolean[][] maze, Point start, Point goal){
		Stack frontier = new Stack();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
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

			//Updating the next node to expand
			nextExpand = frontier.pop();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'

		//Printing out the path
		//TODO: include print statement here
		//End print
	}

	public static int manhattanDistance(Point a, Point b){
		return Math.abs( a.x - b.x ) + Math.abs( a.y - b.y );
	}

	public static void greedyBFS(boolean[][] maze, Point start, Point goal){
		PriorityQueue frontier = new PriorityQueue();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
		while (! nextExpand.equals( goal ) ){
			//Start adding points to the frontier
			if ( maze[ x - 1 ] [ y ] ){
				frontier.insert (
					new Point( x - 1 , y , nextExpand ), 
					manhattanDistance( x - 1 , y , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y - 1 ]  ){
				frontier.insert (
					new Point( x , y - 1 , nextExpand ), 
					manhattanDistance( x , y - 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x + 1 ] [ y ]  ){
				frontier.insert (
					new Point( x + 1 , y , nextExpand ), 
					manhattanDistance( x + 1 , y , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y + 1 ]  ){
				frontier.insert (
					new Point( x , y + 1 , nextExpand ), 
					manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			//End adding nodes to the frontier

			//Updating the next node to expand
			nextExpand = frontier.dequeue();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'

		//Printing out the path
		//TODO: include print statement here
		//End print
	}

	public static void aStar(boolean[][] maze, Point start, Point goal){
		PriorityQueue frontier = new PriorityQueue();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
		while (! nextExpand.equals( goal ) ){
			//Start adding points to the frontier
			if ( maze[ x - 1 ] [ y ] ){
				frontier.insert (
					new Point( x - 1 , y , nextExpand ), 
					1 + nextExpand.distFromStart + manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y - 1 ]  ){
				frontier.insert (
					new Point( x , y - 1 , nextExpand ), 
					1 + nextExpand.distFromStart + manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x + 1 ] [ y ]  ){
				frontier.insert (
					new Point( x + 1 , y , nextExpand ), 
					1 + nextExpand.distFromStart + manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			if ( maze[ x ] [ y + 1 ]  ){
				frontier.insert (
					new Point( x , y + 1 , nextExpand ), 
					1 + nextExpand.distFromStart + manhattanDistance( x , y + 1 , goal.x , goal.y )
					);
			}
			//End adding nodes to the frontier

			//Updating the next node to expand
			nextExpand = frontier.dequeue();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'

		//Printing out the path
		//TODO: include print statement here
		//End print
	}

}