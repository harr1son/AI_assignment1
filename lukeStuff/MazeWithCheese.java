public class MazeWithCheese{
	int[][] state;
	Point[] cheese;
	Point start;
	Point goal;

	public MazeWithCheese(String inputText){
		/**
		* Process the input. Fills in state variables
		* 0 corresponds to illegal;
		* 1 corresponds to legal;
		* 2 corresponds to containg Cheese;
		*/
		String[] lines = inputText.split("\r\n|\r|\n");
		state = new boolean[lines.length][lines[0].length()];
		Point cheesePoints = null;
		int nCheesePoints = 0;
		//Start nested for loops
        for(int y = 0; y < out.length; y++){
            for(int x = 0; x < out[y].length; x++){

                if (lines[y].charAt(x) == '%'){
					out[y][x] = 0;
				}

				if (lines[y].charAt(x) == '*'){
					out[y][x] = 2;
					nCheesePoints ++;
					if(cheesePoints == null){
						cheesePoints = new Point(x, y, null);
					}
					else{
						cheesePoints = new Point(x, y, cheesePoints);
					}

				}

				else {
					out[y][x] = 1;
				}

				if (lines[y].charAt(x) == 'S'){
					start = new Point(x, y);
				}

				if (lines[y].charAt(x) == 'G'){
					start = new Point(x, y);
				}

            }
        }
        //end nested for loops
        //Start the creation of cheese Point arr
        cheese = new Point[nCheesePoints]
	} 

	public int heuristic(Point frontierPoint){

	}

}