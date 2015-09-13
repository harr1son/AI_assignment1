package assignment1;

public class StateLinkList {
	StateWithCheese data;
	StateLinkList prev;
	StateLinkList next;
	int heuristic; //whatever heuristic value

	public StateLinkList(StateWithCheese data, StateLinkList prev, StateLinkList next, int heuristic){
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
