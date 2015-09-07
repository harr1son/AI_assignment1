//insert proper implementation of a stack

public class Stack{
	LinkList top;
	LinkList bottom;
	public Stack(){
		top = null;
		bottom = null;
	}
	public Point pop(){
		Point out = first.data;
		first = first.next;
		return out;
	}
	public void push(Point data){
		last = new LinkList(data, last);
	}
	
}