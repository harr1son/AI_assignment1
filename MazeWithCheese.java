package assignment1;

import java.io.FileNotFoundException;
import java.util.Hashtable;

public class MazeWithCheese{
	StateWithCheese startingState;
	StateQueue frontier;
	Point start;
	char[][] maze;
	
	public static void main(String[] args) throws FileNotFoundException{
		String input = Helper.processInput("mediumcheese.txt");
		MazeWithCheese m = new MazeWithCheese(input);
		m.runOn();
		//Point nav = m.currentLocation;
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
			int x = nav.currentLocation.x;
			int y = nav.currentLocation.y;
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
			counter ++;
			nav = frontier.dequeue();
			
		}
		System.out.println("Mouse With Cheese A* Search");
		System.out.println("Solution found with "+ counter + " expanded states");
		
		int cheeseNumber = 0;
		
		int cheeseLetter = 0;
		//Formatting output
		for(Point cheeseLocation : nav.eatenCheese){
			if(cheeseNumber < 10){
				maze[cheeseLocation.y][cheeseLocation.x] = (char)('0' + cheeseNumber);
				cheeseNumber++;
			}
			else if (cheeseLetter < 26){
				maze[cheeseLocation.y][cheeseLocation.x] = (char)('a' + cheeseLetter);	
				cheeseLetter++;
			}
		}
		
		//Outputting solution
		for(int i = 0; i < maze.length; i++){
			System.out.println(maze[i]);
		}
	}
	

}