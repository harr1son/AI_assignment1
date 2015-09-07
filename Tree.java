package assignment_1;

import java.util.*;

public class Tree<R,C> {
	public Node<R,C> root;
	
	public Tree(int rowData, int columnData) {
		root = new Node<R,C>();
		root.rowData = rowData;
		root.columnData = columnData;
		root.children = new ArrayList<Node<R,C>>();
	}
}
