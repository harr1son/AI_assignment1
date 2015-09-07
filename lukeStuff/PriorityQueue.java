//insert proper implementation of priority queue 

public class PriorityQueue{
	PriorityLinkList first;
	PriorityLinkList last;
	public PriorityQueue(){
		first = null;
		last = null;
	}
	public Point dequeue(){
		Point out = first.data;
		first = first.next;
		return out;
	}
	public void insert(Point data, int heuristic){

		if(first == null){
			first = new PriorityLinkList(data, null, heuristic);
			last = first;
		}

		else{
			nav = first;
			while(nav.heuristic < heuristic){
				nav = nav.next;
			}
		}

	}

}