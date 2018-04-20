package solver;

public class Piece implements Comparable<Piece>{
	//TODO these might be removed later
	private int height;
	private int width;
	
	private int piece_no;
	private State state = State.normal;
	
	private byte[][] tiles;
	private CoordinateSet valid_placements;
	
	public Piece(int piece_no, byte[][] tiles) {
		this.tiles = tiles;
		this.height = tiles.length;
		this.width = tiles[0].length;
		this.piece_no = piece_no;
		valid_placements = new CoordinateSet();
	}
	
	public int get_height() {return this.height;}
	public int get_width() {return this.width;}
	public int get_piece_no() {return this.piece_no;}
	public byte[][] get_tiles() {return this.tiles;}
	public State get_state() {return this.state;}
	public CoordinateSet get_valid_placements() {return this.valid_placements;}
	
	public void set_state(State s) {this.state = s;}
	public void set_valid_placements(CoordinateSet ps) {this.valid_placements = ps;}
	
	public boolean add_valid_placement(CoordinateSet c) {
		return false;
	}
	
	/*
	 * 2         1
	 * 1 becomes 2
	 * 
	 *   1         4 1
	 * 3 4 becomes 3 
	 */
	public Piece flip_on_axis() {
		byte[][] narr = new byte[height][width];
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				narr[height-1-i][width-1-j] = this.tiles[i][j];
			}
		}
		Piece p = new Piece(this.piece_no, narr);
		
		if(this.state == State.mirror) p.set_state(State.mf);
		else p.set_state(State.flip);
		
		return p;
	}
	
	/*
	 * 2         
	 * 1 becomes 2 1
	 * 
	 *   1           3
	 * 3 4 becomes 1 4
	 */
	public Piece mirror() {
		byte[][] narr = new byte[width][height];
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				narr[j][i] = this.tiles[i][j];
			}
		}
		Piece p = new Piece(this.piece_no, narr);
		
		if(this.state == State.flip) p.set_state(State.mf);
		else p.set_state(State.mirror);
		
		return p;
	}
	
	@Override
	public String toString() {
		String str = "\n";
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (this.tiles[i][j] == -1) str += "  ";
				else str += this.tiles[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}

	@Override
	public int compareTo(Piece other) {
		return this.valid_placements.compareTo(other.valid_placements);
	}
}

//from mirror just in case
//if((height == 3 && width == 1) || (height == 1 && width == 3)) return this.tiles;
//if(height == 2 && width == 1) {
//	narr[0][0] = this.tiles[1][0];
//	narr[1][0] = this.tiles[0][0];
//}
//else if(height == 1 && width == 2) {
//	narr[0][0] = this.tiles[0][1];
//	narr[0][1] = this.tiles[0][0];
//}
//else if(height == 2 && width == 2) {
//	narr[0][0] = this.tiles[1][1];
//	narr[0][1] = this.tiles[0][1];
//	narr[1][0] = this.tiles[1][0];
//	narr[1][1] = this.tiles[0][0];
//}