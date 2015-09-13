package assignment1;

import java.io.*;

public class Main{
	
	public static void main(String args[]) throws IOException {
		//SMALL MAZE
        String input = Helper.processInput("smallMaze.txt");
        
        BreadthFirst breadthFirstSmall = new BreadthFirst(input);
        breadthFirstSmall.runOn();
        
        DepthFirst depthFirstSmall= new DepthFirst(input);
        depthFirstSmall.runOn();
        
        GreedyBestFirst greedyBestFirstSmall = new GreedyBestFirst(input);
        greedyBestFirstSmall.runOn();
        
        AStar aStarSmall = new AStar(input);
        aStarSmall.runOn();
        
        //MEDIUM MAZE
        input = Helper.processInput("mediumMaze.txt");

        BreadthFirst breadthFirstMedium = new BreadthFirst(input);
        breadthFirstMedium.runOn();
        
        DepthFirst depthFirstMedium= new DepthFirst(input);
        depthFirstMedium.runOn();
        
        GreedyBestFirst greedyBestFirstMedium = new GreedyBestFirst(input);
        greedyBestFirstMedium.runOn();
        
        AStar aStarMedium = new AStar(input);
        aStarMedium.runOn();
        
        //BIG MAZE
        input = Helper.processInput("bigMaze.txt");

        BreadthFirst breadthFirstBig = new BreadthFirst(input);
        breadthFirstBig.runOn();
        
        DepthFirst depthFirstBig= new DepthFirst(input);
        depthFirstBig.runOn();
        
        GreedyBestFirst greedyBestFirstBig = new GreedyBestFirst(input);
        greedyBestFirstBig.runOn();
        
        AStar aStarBig = new AStar(input);
        aStarBig.runOn();
        
       
    }

}
