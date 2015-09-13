package assignment1;

import java.io.FileNotFoundException;
import java.util.Hashtable;

public class MazeWithCheese{
	StateWithCheese startingState;
	StateQueue frontier;
	Point start;
	char[][] maze;
	
	public static void main(String[] args) throws FileNotFoundException{
		String input = Helper.processInput("tinycheese.txt");
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
		Hashtable<StateWithCheese, Boolean> visitedStates = new Hashtable<StateWithCheese, Boolean>();
		System.out.println("Number with Cheese: " + nav.containsCheese.size());
		while( ! nav.isGoal() ) {
			//System.out.println("\n-----");
			//System.out.println();
			System.out.println("Step: "+ (counter ++) + " Checking node on depth " + nav.depth );
			System.out.println();
			//System.out.println("Current Location: " + nav.currentLocation + '\n');
			//System.out.println(nav.containsCheese.size());
			int x = nav.currentLocation.x;
			int y = nav.currentLocation.y;
			//START checking surrounding squares
			//System.out.println("In Checking");
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
			

			//System.out.println(frontier);
			//System.out.println("-----\n");
			//System.out.println(counter);
			//System.out.println(frontier + "\n");
			
			visitedStates.put(nav, true);
			
			nav = frontier.dequeue();
			while(visitedStates.get(nav) != null){
				System.out.println("Oops not there baby");
				throw new IndexOutOfBoundsException("Da fak");
				//nav = frontier.dequeue();
			}
			//System.out.println("------");
			//System.out.println(nav.depth + "\n");
		}
		//System.out.println(counter);
		//nav.printPath();
		int cheeseNumber = 0;

		int cheeseLetter = 0;
		
		for(Point cheeseLocation : nav.eatenCheese){
			if(cheeseNumber < 10){
				maze[cheeseLocation.y][cheeseLocation.x] = (char)('0' + cheeseNumber);
				cheeseNumber++;
			}
			else{
				maze[cheeseLocation.y][cheeseLocation.x] = (char)('A' + cheeseLetter);	
				cheeseLetter++;
			}
		}
		
		for(int i = 0; i < maze.length; i++){
			System.out.println(maze[i]);
		}
	}
	
	public void printMazeWithPath(){
		
	}

}