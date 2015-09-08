package assignment1;

//insert proper implementation of a stack

public class Stack{
	LinkList top;
	//LinkList bottom;
	public Stack(){
		top = null;
		//bottom = null;
	}
	public Point pop(){
		Point out = top.data;
		top = top.next;
		return out;
	}
	public void push(Point data){
		top = new LinkList(data, top);
	}
	
}