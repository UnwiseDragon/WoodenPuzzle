package solver;

class Tuple implements Comparable<Tuple>{
	private int x;
	private int y;
	public Tuple(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int get_x() {return x;}
	public int get_y() {return y;}
	public void set_x(int x) {this.x = x;}
	public void set_y(int y) {this.y = y;}
	
	public void inc_x() {++this.x;}
	public void inc_y() {++this.y;}
	
	@Override
	//in this case I only care if they are equal or not.
	//I'm not sure if this is actually needed, I just included it
	public int compareTo(Tuple other) {
		if (other.get_x() == this.get_x() && other.get_y() == this.get_y()) return 0;
		if (other.get_x() > this.get_x() && other.get_y() > this.get_y()) return -1;
		return 1;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
