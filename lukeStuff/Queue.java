package assignment1;

public class Queue{
	public static void main(String[] args){
		Queue tester = new Queue();
		Point a = new Point(1,1);
		Point b = new Point(2,2);
		Point c = new Point(3,3);
		tester.enqueue(a);
		tester.enqueue(b);
		System.out.println(tester);
		System.out.println(tester.dequeue());
		System.out.println(tester.dequeue());
	}
	
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