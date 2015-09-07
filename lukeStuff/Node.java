package assignment_1;

import java.util.List;

public class Node {
	public int row;
	public int col;
	public Node parent;
	public List<Node> children;
		
	public Node(int row, int column){
		this.rowData = row;
		this.columnData = column;
	}
		
}
