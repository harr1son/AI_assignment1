public class PriorityLinkList{
	Point data;
	LinkList next;
	int heuristic; //whatever heuristic value

	public LinkList(Point data, LinkList next, int heuristic){
		this.data = data;
		this.next = next;
		this.heuristic = heuristic;
	}
	
}