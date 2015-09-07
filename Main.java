package assignment_1;

import java.io.*;
import java.util.*;

public class Main {

	public static int startRow;
	public static int startColumn;
	public static int currentRow;
	public static int currentColumn;
	public static ArrayList<String> linesInMaze;
	
	public static void main(String args[]) throws IOException {
		
        File text = new File("smallMaze.txt");
      
        Scanner sc = new Scanner(text);
        linesInMaze = new ArrayList<String>();
        
        currentRow = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            linesInMaze.add(line);
            if(line.contains("S")){
            	startColumn = line.lastIndexOf("S");
            	startRow = currentRow;
            	//break;
            }
            currentRow++;
        }       
        
        currentRow = startRow;
        currentColumn = startColumn;
        
        depthFirstSearch();
        
//        System.out.println("Start Position is: " + startRow + ", " + startColumn);
//        System.out.println("Current Position is: " + currentRow + ", " + currentColumn);
//        System.out.println(canMoveRight());
        
    }   
	
	private static <R,C> void depthFirstSearch() {
		//reset variables
		Tree<R,C> depthFirstTree = new Tree<R,C>(startRow, startColumn);
		Node<R,C> currentNode = depthFirstTree.root;
		currentRow = startRow;
        currentColumn = startColumn;
        
        
        //find current node frontier
        if(canMoveUp()){
        	currentNode.children.add(new Node(currentRow-1,currentColumn));
        }
        if(canMoveDown()){
        	currentNode.children.add(new Node(currentRow+1,currentColumn));
        }
        if(canMoveLeft()){
        	currentNode.children.add(new Node(currentRow,currentColumn-1));
        }
        if(canMoveRight()){
        	currentNode.children.add(new Node(currentRow,currentColumn+1));
        }
//        if(currentNode.children.isEmpty()){
//        	
//        }
        //switch focus to first node on frontier
        currentNode = currentNode.children.get(0);
        currentRow = currentNode.rowData;
        currentColumn = currentNode.columnData;
		
		//System.out.println(depthFirstTree.root.columnData);
		//System.out.println(depthFirstTree.root.rowData);
		return;
		
	}
	
	private static boolean canMoveUp(){
		if(linesInMaze.get(currentRow-1).charAt(currentColumn)==' '){
			return true;
		}else{
			return false;
		}
	}
	private static boolean canMoveDown(){
		if(linesInMaze.get(currentRow+1).charAt(currentColumn)==' '){
			return true;
		}else{
			return false;
		}
	}
	private static boolean canMoveLeft(){
		if(linesInMaze.get(currentRow).charAt(currentColumn-1)==' '){
			return true;
		}else{
			return false;
		}
	}
	private static boolean canMoveRight(){
		if(linesInMaze.get(currentRow).charAt(currentColumn+1)==' '){
			return true;
		}else{
			return false;
		}
	}

}
