//insert proper implementation of queue

public class Queue{
	LinkList first;
	LinkList last;
	public Queue(){
		first = null;
		last = null;
	}
	public Point dequeue(){
		Point out = first.data;
		first = first.next;
		return out;
	}
	public void enqueue(Point data){
		last = new LinkList(data, last);
		if(first == null){
			first = last;
		}
	}

}