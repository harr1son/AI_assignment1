
public class PriorityLinkList{
	Point data;
	PriorityLinkList next;
	PriorityLinkList prev;

	int heuristic; //whatever heuristic value

	public PriorityLinkList(Point data, PriorityLinkList prev, PriorityLinkList next, int heuristic){
		this.data = data;
		this.prev = prev;
		if(prev != null){
			prev.next = this;
		}
		this.next = next;
		if(next != null){
			next.prev = this;
		}
		this.heuristic = heuristic;
	}
	
}
