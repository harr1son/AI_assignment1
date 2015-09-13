
//insert proper implementation of priority queue 

public class PriorityQueue{
	PriorityLinkList first;
	//PriorityLinkList last;
	public PriorityQueue(){
		first = null;
		//last = null;
	}
	public Point dequeue(){
		Point out = first.data;
		first = first.next;
		return out;
	}
	public void insert(Point data, int heuristic){
	//int heuristic = data.fit;
		if(first == null){
			first = new PriorityLinkList(data, null, null, heuristic);
		}

		else{
			if(heuristic < first.heuristic){
				first = new PriorityLinkList(data, null, first, heuristic);
			}
			else{
				PriorityLinkList nav = first;
				
			
				while(heuristic >= nav.heuristic && nav.next != null){
					nav = nav.next;
				}
				//System.out.println();
				if (heuristic > nav.heuristic){
					nav.next = new PriorityLinkList(data, nav, nav.next, heuristic);
				}
				else if(heuristic == nav.heuristic){
					nav.next = new PriorityLinkList(data, nav, null, heuristic);
				}
				else{
					nav = new PriorityLinkList(data, nav.prev, nav, heuristic);
				}
			}
		}

	}

}
