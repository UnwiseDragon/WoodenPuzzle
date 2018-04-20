package solver;

import java.util.ArrayList;
import java.util.Iterator;

/*
 *   x ->
 * y
 * |
 * v
 */
//I artificially defined my own set because ArrayLists guarantee ordering
public class CoordinateSet implements Comparable<CoordinateSet>{

	private ArrayList<Tuple> xs;
	private ArrayList<State> states;
	private int num_coords;
	
	/*
	 * xs contains cartesian coordinates
	 * (0, 0) is in the top left
	 * (6, 6) is in the bottom right
	 */
	
	public ArrayList<Tuple> get_xs(){return xs;}
	public ArrayList<State> get_states(){return states;}
	public int get_num_coords() {return num_coords;}
	
	public CoordinateSet() {
		this.xs = new ArrayList<Tuple>();
		this.states = new ArrayList<State>();
		this.num_coords = 0;
	}
	
	public Iterator<Tuple> get_xs_itr(){
		return xs.iterator();
	}
	public Iterator<State> get_states_itr(){
		return states.iterator();
	}
	
	public boolean contains(Tuple t) {
		for(Iterator<Tuple> itr = xs.iterator(); itr.hasNext();) {
			Tuple temp = itr.next();
			if(temp.get_x() == t.get_x() && temp.get_y() == t.get_y()) return true;
		}
		return false;
	}
	
	public boolean add(State s, byte x, byte y) {
		Tuple newt = new Tuple(x, y);
		if(x >= 0 && x <= 6 && y >= 0 && y <= 6 && !this.contains(newt)) {
//			System.out.println("this is newt: " + newt);
			num_coords++;
			xs.add(newt);
			states.add(s);
			return true;
		}
		return false;
	}
	
	public Tuple remove(Tuple t) {
		Tuple temp = null;
		if(xs.contains(t)) {
			temp = t;
			xs.remove(t);
		}
		return temp;
	}
	
	@Override
	public int compareTo(CoordinateSet other) {
		if (this.num_coords == other.get_num_coords()) return 0;
		else if (this.num_coords < other.get_num_coords()) return -1;
		else return 1;
	}
}
