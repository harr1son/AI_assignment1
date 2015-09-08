package assignment_1;

import java.io.*;
import java.util.*;

public class Main {

	public static int startRow;
	public static int startColumn;
	public static int currentRow;
	public static int currentColumn;
	public static ArrayList<String> linesInMaze;
	public static ArrayList<String> depthFirstSolution;
	
	public static void main(String args[]) throws IOException {
		
		Scanner user_input = new Scanner(System.in);
		
		String name_of_file;
		System.out.print("Which maze file are we searching?: ");
		name_of_file = user_input.next();
		user_input.close();
		
        File text = new File(name_of_file);
      
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
        
		depthFirstSolution = (ArrayList<String>) linesInMaze.clone();
        depthFirstSearch();
        
    }   
	
	private static <R,C> void depthFirstSearch() {
		//reset variables
		Tree<R,C> depthFirstTree = new Tree<R,C>(startRow, startColumn);
		Node<R,C> currentNode = depthFirstTree.root;
		currentRow = startRow;
        currentColumn = startColumn;
        

        goDeeper(currentNode);
        
		return;
		
	}
	
	private static <R,C> boolean goDeeper(Node<R,C> currentNode){
		
		if(linesInMaze.get(currentNode.rowData).charAt(currentNode.columnData)=='G'){
			System.out.println("FOUND G LOCATION AT: "+currentNode.rowData+", "+currentNode.columnData);
			for(int i=0;i<depthFirstSolution.size();i++){
				System.out.println(depthFirstSolution.get(i));
			}
			return true;
		}
		
		if(linesInMaze.get(currentNode.rowData).charAt(currentNode.columnData)!='S'){
			String newRow = depthFirstSolution.get(currentNode.rowData);
			newRow = newRow.substring(0,currentNode.columnData)+'.'+newRow.substring(currentNode.columnData+1);
			depthFirstSolution.set(currentNode.rowData, newRow);
			for(int i=0;i<depthFirstSolution.size();i++){
				System.out.println(depthFirstSolution.get(i));
			}
		}
		
		currentRow = currentNode.rowData;
        currentColumn = currentNode.columnData;
		int childCount = 0;
		
		if(canMoveUp(depthFirstSolution)){
			if((currentRow == startRow && currentColumn == startColumn) || (currentRow-1 != currentNode.parent.rowData)){
				currentNode.children.add(new Node<R, C>(currentRow-1,currentColumn,currentNode));
				childCount++;
			}
        }
		
		if(canMoveRight(depthFirstSolution)){
        	if((currentRow == startRow && currentColumn == startColumn) || (currentColumn+1 != currentNode.parent.columnData)){
        		currentNode.children.add(new Node<R, C>(currentRow,currentColumn+1,currentNode));
        		childCount++;
        	}
        }
		
        if(canMoveDown(depthFirstSolution)){
			if((currentRow == startRow && currentColumn == startColumn) || (currentRow+1 != currentNode.parent.rowData)){
				currentNode.children.add(new Node<R, C>(currentRow+1,currentColumn,currentNode));
				childCount++;
			}
        }
        
        if(canMoveLeft(depthFirstSolution)){
        	if((currentRow == startRow && currentColumn == startColumn) || (currentColumn-1 != currentNode.parent.columnData)){
        		currentNode.children.add(new Node<R, C>(currentRow,currentColumn-1,currentNode));
        		childCount++;
        	}
        }
        
        for(int i=0; i<childCount; i++){
        	if(!goDeeper(currentNode.children.get(i))){
        		continue;
        	}else{
        		return true;
        	}
        }
        if(linesInMaze.get(currentNode.rowData).charAt(currentNode.columnData)!='S'){
			String newRow = depthFirstSolution.get(currentNode.rowData);
			newRow = newRow.substring(0,currentNode.columnData)+' '+newRow.substring(currentNode.columnData+1);
			depthFirstSolution.set(currentNode.rowData, newRow);
		}
        return false;
	}
		
	private static boolean canMoveUp(ArrayList<String> maze){
		if(maze.get(currentRow-1).charAt(currentColumn)==' ' || maze.get(currentRow-1).charAt(currentColumn)=='G'){
			return true;
		}else{
			return false;
		}
	}
	private static boolean canMoveDown(ArrayList<String> maze){
		if(maze.get(currentRow+1).charAt(currentColumn)==' ' || maze.get(currentRow+1).charAt(currentColumn)=='G'){
			return true;
		}else{
			return false;
		}
	}
	private static boolean canMoveLeft(ArrayList<String> maze){
		if(maze.get(currentRow).charAt(currentColumn-1)==' ' || maze.get(currentRow).charAt(currentColumn-1)=='G'){
			return true;
		}else{
			return false;
		}
	}
	private static boolean canMoveRight(ArrayList<String> maze){
		if(maze.get(currentRow).charAt(currentColumn+1)==' ' || maze.get(currentRow).charAt(currentColumn+1)=='G'){
			return true;
		}else{
			return false;
		}
	}

}
