package assignment1;

import java.io.FileNotFoundException;

public class MazeWithCheese{
	StateWithCheese startingState;
	StateQueue frontier;
	Point start;
	char[][] maze;
	
	public static void main(String[] args) throws FileNotFoundException{
		String input = Helper.processInput("smallcheese.txt");
		MazeWithCheese m = new MazeWithCheese(input);
		m.runOn();
	}
	
	public MazeWithCheese(String inputText){
		startingState = new StateWithCheese(inputText);
		maze = Helper.toCharArr(inputText);
		start = Helper.getStart(inputText);
		frontier = new StateQueue();
	}
	
	public void runOn(){
		StateWithCheese nav = startingState;
		int counter = 0;
		while( ! nav.isGoal() ) {
			counter ++;
			int x = nav.currentLocation.x;
			int y = nav.currentLocation.y;
			//START checking surrounding squares
			if( maze[y][x - 1] != '%'){ //'l'
				StateWithCheese newState = new StateWithCheese(nav, 'l');
				frontier.insert(newState);
			}
			if( maze[y + 1][x] != '%'){//'u'
				StateWithCheese newState = new StateWithCheese(nav, 'u');
				frontier.insert(newState);
			}
			if( maze[y][x + 1] != '%'){//'r'
				StateWithCheese newState = new StateWithCheese(nav, 'r');
				frontier.insert(newState);
			}
			if( maze[y - 1][x] != '%'){//'d'
				StateWithCheese newState = new StateWithCheese(nav, 'd');
				frontier.insert(newState);
			}
			nav = frontier.dequeue();
		}
		System.out.println(counter);
		nav.printPath();
		
	}
	
	public static Point search(boolean[][] maze, Point start, Point goal){
		PriorityQueue frontier = new PriorityQueue();
		Point nextExpand = start;
		int x = nextExpand.x;
		int y = nextExpand.y;
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
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'

		return nextExpand;
		
		//Printing out the path
		//TODO: include print statement here
		//End print
	}
	

}