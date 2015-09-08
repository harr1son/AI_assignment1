package assignment1;

import java.io.*;
import java.util.*;

//import java.util.Queue;
//import java.util.Stack;

public class Main{
	
public static void main(String args[]) throws IOException {
		
		//Scanner user_input = new Scanner(System.in);
		
		String name_of_file = "bigMaze.txt";
		//System.out.print("Which maze file are we searching?: ");
		//name_of_file = user_input.next();
		//user_input.close();
		
        File text = new File(name_of_file);
      
        Scanner sc = new Scanner(text);
        String input = "";
        
        
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            input += line + "\n";
        }
        sc.close();
        
        //Breadth first start
        
        char[][] charMaze = charArr(input);
        Point start = getStart(input);
        Point goal = getGoal(input);
        boolean[][] maze = processInput(input);
        
        Point end = breadthFirst(maze, start, goal);
        end = end.parent;
        while(end.parent != null){
        	charMaze[end.y][end.x] = '.';
        	end = end.parent;
        }
        
        for(int i = 0; i < charMaze.length; i++){
        	System.out.println(charMaze[i]);
        }

        //Breadth first end
        
        System.out.println();
        
        //Depth first start
        charMaze = charArr(input);
        start = getStart(input);
        goal = getGoal(input);
        maze = processInput(input);
        
        end = depthFirst(maze, start, goal);
        end = end.parent;
        while(end.parent != null){
        	charMaze[end.y][end.x] = '.';
        	end = end.parent;
        }
        
        for(int i = 0; i < charMaze.length; i++){
        	System.out.println(charMaze[i]);
        }
        //Depth first end
        
        System.out.println();
        
        //Greedy BFS start
        charMaze = charArr(input);
        start = getStart(input);
        goal = getGoal(input);
        maze = processInput(input);
        
        end = greedyBFS(maze, start, goal);
        end = end.parent;
        while(end.parent != null){
        	charMaze[end.y][end.x] = '.';
        	end = end.parent;
        }
        
        for(int i = 0; i < charMaze.length; i++){
        	System.out.println(charMaze[i]);
        }
        //Greedy BFS end
        
        //A* start
        charMaze = charArr(input);
        start = getStart(input);
        goal = getGoal(input);
        maze = processInput(input);
        
        end = aStar(maze, start, goal);
        end = end.parent;
        while(end.parent != null){
        	charMaze[end.y][end.x] = '.';
        	end = end.parent;
        }
        
        for(int i = 0; i < charMaze.length; i++){
        	System.out.println(charMaze[i]);
        }
        //A* end
    }

	public static char[][] charArr(String inputText){
		String[] strArr = inputText.split("\r\n|\r|\n");
		char[][] out = new char[strArr.length][strArr[0].length()];
		for(int row = 0; row < strArr.length; row++){
			out[row] = strArr[row].toCharArray();
		}
		return out;
	}
	
	public static Point getStart(String inputText){
		int xOut = -1;
		int yOut = -1;
		String[] lines = inputText.split("\r\n|\r|\n");
		for(int y = 0; y < lines.length; y++){
          for(int x = 0; x < lines[y].length(); x++){
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
		for(int y = 0; y < lines.length; y++){
          for(int x = 0; x < lines[y].length(); x++){
              if (lines[y].charAt(x) == 'G'){
              	xOut = x;
              	yOut = y;
              	break;
              }
          }
      }
      return new Point(xOut, yOut);
	}
	public static boolean[][] processInput(String inputText){
		/**
		* Process the input. Returns boolean matrix where true is legal location.
		*/
		String[] lines = inputText.split("\r\n|\r|\n");
		boolean[][] out = new boolean[lines[0].length()][lines.length];
      for(int x = 0; x < out.length; x++){
          for(int y = 0; y < out[x].length; y++){
              if (lines[y].charAt(x) != '%'){
					out[x][y] = true;
				}
          }
      }
      return out;
	}
	/**
	 * TODO: change this to not have return value
	 */
	public static Point breadthFirst(boolean[][] maze, Point start, Point goal){
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

	/**
	 * TODO: change this to not have return value
	 */
	public static Point depthFirst(boolean[][] maze, Point start, Point goal){
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

			//Disallowing returning to this location
			maze[ x ][ y ] = false;

			
			//Updating the next node to expand
			nextExpand = frontier.pop();
			x = nextExpand.x;
			y = nextExpand.y;

		}
		//By here, the nextExpand node, and parents, should be the path from 'S' to 'G'
		
		//Temporarially include return 
		
		return nextExpand;
		//Printing out the path
		//TODO: include print statement here
		//End print
	}

	public static int manhattanDistance(Point a, Point b){
		return Math.abs( a.x - b.x ) + Math.abs( a.y - b.y );
	}
	
	public static int manhattanDistance(int x1, int y1, int x2, int y2){
		return Math.abs( x1 - x2 ) + Math.abs( y1 - y2 );
	}

	public static Point greedyBFS(boolean[][] maze, Point start, Point goal){
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

	public static Point aStar(boolean[][] maze, Point start, Point goal){
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
