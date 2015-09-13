package assignment1;

public class StateQueue {
	StateLinkList first;
	StateLinkList last;
	int size;
	public StateQueue(){
		first = null;
		size = 0;
	}
	public StateWithCheese dequeue(){
		StateWithCheese out = first.data;
		first = first.next;
		size --;
		return out;
	}
	public void insert(StateWithCheese data, int heuristic){
		//int heuristic = data.fit;
		if(first == null){
			first = new StateLinkList(data, null, null, heuristic);
		}

		else{
			if(heuristic < first.heuristic){
				first = new StateLinkList(data, null, first, heuristic);
			}
			else{
				StateLinkList nav = first;
				
			
				while(heuristic >= nav.heuristic && nav.next != null){
					nav = nav.next;
				}
				//System.out.println();
				if (heuristic > nav.heuristic){
					nav.next = new StateLinkList(data, nav, nav.next, heuristic);
				}
				else if(heuristic == nav.heuristic){
					nav.next = new StateLinkList(data, nav, null, heuristic);
				}
				else{
					nav = new StateLinkList(data, nav.prev, nav, heuristic);
				}
			}
		}

	}

	public void insert(StateWithCheese data){
		int heuristic = data.fit;
		if(first == null){
			first = new StateLinkList(data, null, null, heuristic);
		}

		else{
			if(heuristic < first.heuristic){
				first = new StateLinkList(data, null, first, heuristic);
			}
			else{
				StateLinkList nav = first;
				
			
				while(heuristic >= nav.heuristic && nav.next != null){
					nav = nav.next;
				}
				//System.out.println();
				if (heuristic > nav.heuristic){
					nav.next = new StateLinkList(data, nav, nav.next, heuristic);
				}
				else if(heuristic == nav.heuristic){
					nav.next = new StateLinkList(data, nav, null, heuristic);
				}
				else{
					nav = new StateLinkList(data, nav.prev, nav, heuristic);
				}
			}
		}

	}
	public String toString(){
		StateLinkList nav = first;
		String out = "";
		while(nav != null){
			out += " -> " + nav.heuristic;
			nav = nav.next;
		}
		return out;
	}
	public static void main(String[] args){
		StateQueue q = new StateQueue();
		int[] vals = {0,2,0,1,0,0};
		System.out.println(q);
		for( int i : vals){
			int insert = (int)(10*Math.random());
			StateWithCheese tmp = new StateWithCheese();
			
			q.insert(tmp, insert);
			System.out.println(q);
		}
		for(int i = 0; i < vals.length; i++){
			//q.dequeue();
		}
		
		//System.out.println(q);
	}
}
