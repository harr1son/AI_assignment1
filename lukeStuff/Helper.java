package assignment1;

import java.io.*;
import java.util.*;

/*
 * This class is only a colleciton of static methods that are useful throughout the project.
 */

public class Helper {

	public static int manhattanDistance(Point a, Point b){
		return Math.abs( a.x - b.x ) + Math.abs( a.y - b.y );
	}
	public static int manhattanDistance(int x1, int y1, int x2, int y2){
		return Math.abs( x1 - x2 ) + Math.abs( y1 - y2 );
	}
	public static String processInput(String nameOfFile) throws FileNotFoundException{
		
        File text = new File(nameOfFile);
      
        Scanner sc = new Scanner(text);
        String output = "";
        
        
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            output += line + "\n";
        }
        sc.close();
        return output;
	}
	
	public static char[][] toCharArr(String inputText){
		String[] strArr = inputText.split("\r\n|\r|\n");
		char[][] out = new char[strArr.length][strArr[0].length()];
		for(int row = 0; row < strArr.length; row++){
			out[row] = strArr[row].toCharArray();
		}
		return out;
	}
	
	public static boolean[][] toBooleanArr(String inputText){
		
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
	
	public static boolean[][] toBooleanArr(char[][] charMaze){
		
		boolean[][] out = 
				new boolean[charMaze[0].length]
						[charMaze.length];
		for(int x = 0; x < out.length; x++){
			for(int y = 0; y < out[x].length; y++){
				if (charMaze[y][x] != '%'){
					out[x][y] = true;
				}
			}
		}
		return out;
	}
	
	/**
	 * Given the maze in a char array and the goal point found, this function prints out the solution maze.  
	 */
	public static void printOutWithPath(char[][] charMaze, Point goalWithParents){
		Point nav = goalWithParents.parent;
		int counter = 0;
		while(nav.parent != null){
			charMaze[nav.y][nav.x] = '.';
			nav = nav.parent;
			counter++;
		}
		System.out.println("Path Length: " + counter);
		for (int i = 0; i < charMaze.length; i++ ){
			System.out.println( charMaze[i] ) ;
		}
		System.out.println();
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
	
	public static ArrayList<Point> findCheese(char[][] charArr){
		ArrayList<Point> out = new ArrayList<Point>();
		for(int y = 0; y < charArr.length; y++){
			
			for(int x = 0; x < charArr[y].length; x++){
				if (charArr[y][x] == '.'){
					out.add(
							new Point( x , y )
							);
				}
			}
			
		}
		return out;
	}
	
	public static ArrayList<ArrayList<Point>> permute(ArrayList<Point> list) {
		ArrayList<ArrayList<Point>> result = new ArrayList<ArrayList<Point>>();
	 
		//start from an empty list
		result.add(new ArrayList<Point>());
	 
		for (int i = 0; i < list.size(); i++) {
			//list of list in current iteration of the array num
			ArrayList<ArrayList<Point>> current = new ArrayList<ArrayList<Point>>();
	 
			for (ArrayList<Point> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size()+1; j++) {
					// + add num[i] to different locations
					l.add(j, list.get(i));
	 
					ArrayList<Point> temp = new ArrayList<Point>(l);
					current.add(temp);
	 
					//System.out.println(temp);
	 
					// - remove num[i] add
					l.remove(j);
				}
			}
	 
			result = new ArrayList<ArrayList<Point>>(current);
		}
	 
		return result;
	}
	
}
