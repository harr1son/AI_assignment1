
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
		if (first == null){
			first = new LinkList(data, null);
			last = first;
		}
		else{
			last.next = new LinkList(data, null);
			last = last.next;
		}
	}
	public String toString(){
		String out = "";
		LinkList nav = first;
		while(nav != null){
			out += nav.data + " || ";
			nav = nav.next;
		}
		return out;
	}
}
