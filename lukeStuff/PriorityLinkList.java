package assignment1;

public class PriorityLinkList{
	Point data;
	PriorityLinkList next;
	int heuristic; //whatever heuristic value

	public PriorityLinkList(Point data, PriorityLinkList next, int heuristic){
		this.data = data;
		this.next = next;
		this.heuristic = heuristic;
	}
	
}