package assignment1;

public class StateQueue {
	StateLinkList first;
	StateLinkList last;
	public StateQueue(){
		first = null;
		last = null;
	}
	public StateWithCheese dequeue(){
		StateWithCheese out = first.data;
		first = first.next;
		return out;
	}
	public void insert(StateWithCheese data){
		int heuristic = data.fit;
		if(first == null){
			first = new StateLinkList(data, null, heuristic);
			last = first;
		}

		else{
			StateLinkList nav = first;
			while(nav.heuristic < heuristic && nav.next != null){
				nav = nav.next;
			}
			nav.next = new StateLinkList(data, nav.next, heuristic);
		}

	}

}
