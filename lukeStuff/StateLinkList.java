package assignment1;

public class StateLinkList {
	StateWithCheese data;
	StateLinkList next;
	int heuristic; //whatever heuristic value

	public StateLinkList(StateWithCheese data, StateLinkList next, int heuristic){
		this.data = data;
		this.next = next;
		this.heuristic = heuristic;
	}
}
