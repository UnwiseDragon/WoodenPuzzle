package solver;

public class SupportFunctions {
	
	/*
	 * Checks to see if the piece could fit in the board.
	 * The coordinate (i, j) indicates the position on the board
	 * where the top left of the piece will be placed.
	 * 
	 * If this function finds a violation it returns false.
	 * Such violations include:
	 * - the current piece tile doesn't match the current b tile
	 * - the current piece tile is overflowing the board
	 * 
	 * Pieces.N == wildcard (matches ANY tile)
	 */
	public static boolean fits(Piece p, Board b, int i, int j) {
		boolean fits = true;
		byte[][] ptiles = p.get_tiles();
		byte[][] btiles = b.get_layout();

		for (int k=0; k<p.get_height(); k++) {
			for (int l=0; l<p.get_width(); l++) {
				if(i+k >= b.WIDTH || j+l >= b.WIDTH) {
					fits = false;
					continue;
				}
				if(ptiles[k][l] == Pieces.N) continue;
				if(ptiles[k][l] != btiles[i+k][j+l]) fits = false;
			}
		}
		
		return fits;
	}
	
	/*
	 * returns a CoordinateSet of valid coordinates on
	 * the Board b that Piece p could occupy
	 */
	public static CoordinateSet generate_valid_placements(Piece p, Board b){
		CoordinateSet placements = new CoordinateSet();
		for (int i=0; i<b.WIDTH; i++) {
			for (int j=0; j<b.WIDTH; j++) {
				
				//(i, j) is the coordinate of the top left corner of the piece
				if(fits(p, b, i, j)) {
					placements.add(p.get_state(), (byte)j, (byte)i);
//					System.out.println(p);
				}
				
				Piece flip = p.flip_on_axis();
				if(fits(flip, b, i, j)) {
					placements.add(flip.get_state(), (byte)j, (byte)i);
//					System.out.println(flip);
				}
				
				Piece mf = p.flip_on_axis().mirror();
				if(fits(mf, b, i , j)) {
					placements.add(mf.get_state(), (byte)j, (byte)i);
//					System.out.println(mf);
				}
				
				Piece mirror = p.mirror();
				if(fits(mirror, b, i , j)) {
					placements.add(mirror.get_state(), (byte)j, (byte)i);
//					System.out.println(mirror);
				}
			}
		}
		
		
		return placements;
	}
}
