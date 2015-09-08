package assignment1;

public class Point{
	int x;
	int y;
	Point parent;
	int distFromStart; //only relevant for aStar
	public Point(int x, int y, Point parent){
		this.x = x;
		this.y = y;
		this.parent = parent;
		distFromStart = parent.distFromStart + 1;
	}
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		this.parent = null;
		distFromStart = 0;
	}
	public boolean equals(Point that){
		return (x == that.x) && (y == that.y);
 	}
	public String toString(){
		return "( "+x+" , "+y+" )";
	}
}