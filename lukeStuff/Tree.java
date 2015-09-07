package assignment_1;

import java.util.*;

public class Tree {
	public Node root;
	
	public Tree(int rowData, int columnData) {
		root = new Node();
		root.rowData = rowData;
		root.columnData = columnData;
		root.children = new ArrayList<Node>();
	}
}
