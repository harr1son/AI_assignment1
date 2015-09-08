package assignment_1;

import java.util.ArrayList;
import java.util.List;

public class Node<R,C> {
	public int rowData;
	public int columnData;
	public Node<R,C> parent;
	public List<Node<R,C>> children;

	public Node(int row, int column) {
		this.rowData = row;
		this.columnData = column;
		this.children =  new ArrayList<Node<R,C>>();
	}
	
	public Node(int row, int column, Node<R,C> parent) {
		this.rowData = row;
		this.columnData = column;
		this.parent = parent;
		this.children =  new ArrayList<Node<R,C>>();
	}
	
	
		
}
