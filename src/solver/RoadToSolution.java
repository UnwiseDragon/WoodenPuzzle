package solver;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * contains failed attempts
 */
public class RoadToSolution {
	private ArrayList<Board> rts = new ArrayList<Board>();
	
	public ArrayList<Board> get_rts(){return this.rts;}
	
	public boolean contains(Board b) {
		for(Iterator<Board> itr = rts.iterator(); itr.hasNext();) {
			if (itr.next() == b) return true;
		}
		return false;
	}
	
	public boolean add(Board b) {
		if(this.contains(b)) return false;
		rts.add(b);
		return true;
	}
	
	
}
