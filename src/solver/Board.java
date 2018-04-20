package solver;

public class Board implements Comparable<Board>{
	public final int NUM_PIECES = 20;
	public final int WIDTH = 7;
	private final byte[][] default_layout = {
			{3, 1, 1, 2, 3, 1, 4},
			{2, 3, 3, 2, 4, 2, 1},
			{4, 3, 1, 2, 2, 4, 1},
			{1, 2, 1, 2, 3, 1, 2},
			{2, 4, 3, 2, 4, 4, 2},
			{1, 3, 4, 2, 1, 1, 2},
			{1, 3, 1, 2, 3, 3, 2}
	};
	private boolean[][] occupied = new boolean[WIDTH][WIDTH];
	
	private Board parent;
	public Board get_parent() {return this.parent;}
	
	public Board() {
		populate_pieces();
		populate_occupied();
		populate_pcoords();
		this.parent = null;
		this.num_placed = 0;
	}
	
	public Board(Board parent, boolean[][] occupied, Piece[] pieces, Tuple[] pcoords) {
		this.parent = parent;
		for (int i=0; i<occupied.length; i++) this.occupied[i] = occupied[i].clone();
		this.pieces = pieces.clone();
		this.pcoords = pcoords.clone();
		
		if(parent != null) this.num_placed = parent.num_placed;
		else this.num_placed = 0;
	}
	
	public byte[][] get_layout(){return this.default_layout;}
	public boolean[][] get_occupied() {return this.occupied;}
	
	/*
	 * This array represents the number of pieces
	 * that have been successfully placed on the board.
	 * When it no longer contains null values, the puzzle is solved.
	 * 
	 * I chose to use a pieces array so it can be seen exactly which
	 * orientation each piece is in on completion of the puzzle if desired
	 */
	private Piece[] pieces = new Piece[NUM_PIECES];
	private Tuple[] pcoords = new Tuple[NUM_PIECES];
	private State[] states = new State[NUM_PIECES];
	private int num_placed;
	
	public Piece[] get_pieces() {return this.pieces;}
	public Tuple[] get_pcoords() {return this.pcoords;}
	public State[] get_states() {return this.states;}
	public int get_num_placed() {return this.num_placed;}
	
	public void increment_num_placed() {++this.num_placed;}
	
	private void populate_pieces() {
		for (int i=0; i<20; i++) {this.pieces[i] = null;}
	}
	
	private void populate_pcoords() {
		for (int i=0; i<20; i++) {this.pcoords[i] = null;}
	}
	
	private void populate_occupied() {
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<WIDTH; j++) {
				this.occupied[i][j] = false;
			}
		}
	}
	
	public boolean contains_isolated_tiles() {
		int flag = 0;
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<WIDTH; j++) {
				if(!this.occupied[i][j]) {
					//if it's not 3 there exist singles for 1, 2, and 4.
					if(default_layout[i][j] != 3) return false;
					if (i+1 < WIDTH) {
						if(this.occupied[i+1][j]) flag++;
					}
					else flag++;
					if (i-1 >= 0) {
						if(this.occupied[i-1][j]) flag++;
					}
					else flag++;
					if (j+1 < WIDTH) {
						if(this.occupied[i][j+1]) flag++;
					}
					else flag++;
					if (j-1 >= 0) {
						if(this.occupied[i][j-1]) flag++;
					}
					else flag++;
				}
			}
		}
		if(flag == 4) return true;
		else return false;
	}
	
	/*
	 * places piece p iff no square it would occupy is already occupied
	 */
	public Board place_piece(Piece p, Tuple coord, State s) {
		int j = coord.get_x();
		int i = coord.get_y();
		
//		System.out.println(coord);
//		System.out.println("state " + s);
//		System.out.println();
//		System.out.println(p);
		
		if(p.get_state() != s) {
			switch(s) {
			case mirror:
				p = p.mirror();
				break;
			case flip:
				p = p.flip_on_axis();
				break;
			case mf:
				p = p.mirror().flip_on_axis();
				break;
			default:
				break;
			}
		}
		
		byte[][] ptiles = p.get_tiles();
		
		Board newb = new Board(this, this.occupied, this.pieces, this.pcoords);
//		System.out.println(newb);
		
		//even though technically this should have already been checked, best check again
		if (!SupportFunctions.fits(p, this, i, j)) {
//			System.out.println("Nope, couldn't fit.");
			return null;
		}
		else {
//			System.out.println("Yep, could fit.");
		}

		for (int k=0; k<p.get_height(); k++) {
			for (int l=0; l<p.get_width(); l++) {
				if(ptiles[k][l] == Pieces.N) continue;
				if(k+i >= WIDTH || l+j >= WIDTH) {
					return null;
				}
				if(newb.occupied[k+i][l+j]) {
//					System.out.println("Occupied at " + (l+j) + " " + (k+i) + " is true.\n-------------------\n");
					return null;
				}
			}
		}
		
		for (int k=0; k<p.get_height(); k++) {
			for (int l=0; l<p.get_width(); l++) {
				if(ptiles[k][l] == Pieces.N) continue;
				newb.get_occupied()[k+i][l+j] = true;
//				System.out.println("Setting " + (k+y) + " " + (l+x) + " to true.");
			}
		}
		newb.get_pieces()[p.get_piece_no()-1] = p;
		newb.get_pcoords()[p.get_piece_no()-1] = coord;
		newb.increment_num_placed();
		
//		System.out.println("Returning normally");
		return newb;
	}
	
	public boolean solved() {
		for (int i=0; i<NUM_PIECES; i++) if(this.pieces[i] == null) return false;
		return true;
	}

	@Override
	/*
	 * compares the pieces placed on each board
	 * if they are the same in the same locations then
	 * the boards are the same
	 */
	public int compareTo(Board other) {
		for(int i=0; i<NUM_PIECES; i++) {
			if(this.pieces[i] != other.get_pieces()[i]) {
				if(this.num_placed < other.get_num_placed()) return 1;
				else return -1;
			}
			if(this.pcoords[i] != other.get_pcoords()[i]) {
				if(this.num_placed < other.get_num_placed()) return 1;
				else return -1;
			}
		}
		return 0;
	}
	
	public String toString() {
		String s = "";
		for (int i=0; i<WIDTH; i++) {
			for (int j=0; j<WIDTH; j++) {
				if(occupied[i][j]) s += "o ";
				else s += ". ";
			}
			s += "\n";
		}
		return s;
	}
	
}
