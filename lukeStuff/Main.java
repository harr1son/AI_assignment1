package assignment1;

import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String args[]) throws IOException {
		
        String input = Helper.processInput("bigMaze.txt");
        
        BreadthFirst breadthFirstInstance = new BreadthFirst(input);
        breadthFirstInstance.runOn();
        
        DepthFirst depthFirstInstance = new DepthFirst(input);
        depthFirstInstance.runOn();
        
        GreedyBestFirst greedyBestFirstInstance = new GreedyBestFirst(input);
        greedyBestFirstInstance.runOn();
        
        AStar aStarInstance = new AStar(input);
        aStarInstance.runOn();
       
    }

}
