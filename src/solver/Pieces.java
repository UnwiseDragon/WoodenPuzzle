package solver;

/*
 * These are the pieces that came with the game.
 * -1 means "no tile" or "no value" and is treated
 * as a wildcard.
 * 
 * This class is meant to be a reference for game pieces
 * It is not supposed to be EVER instantiated
 */
public abstract class Pieces {
	
	/*
	 * N is for aesthetic purposes only
	 * It's a lot easier to notice that N is unusual
	 * than it is to notice that -1 is unusual
	 */
	public static final byte N = -1;
	
	//1 tile
	static final byte[][] p1 = {{1}};
	static final byte[][] p2 = {{2}};
	static final byte[][] p3 = {{4}};
	
	//2 tiles
	static final byte[][] p4 = {
			{1},
			{1}
	};
	static final byte[][] p5 = {
			{1},
			{2}
	};
	static final byte[][] p6 = {
			{1},
			{3}
	};
	static final byte[][] p7 = {
			{2},
			{3}
	};
	static final byte[][] p8 = {
			{2},
			{3}
	};
	static final byte[][] p9 = {
			{1},
			{4}
	};
	static final byte[][] p10 = {
			{2},
			{4}
	};
	static final byte[][] p11 = {
			{3},
			{3}
	};
	static final byte[][] p12 = {
			{3},
			{4}
	};
	
	//3 tiles
	static final byte[][] p13 = {
			{1},
			{3},
			{1},
	};
	static final byte[][] p14 = {
			{1, N},
			{2, 2}
	};
	static final byte[][] p15 = {
			{N, 1},
			{4, 2}
	};
	static final byte[][] p16 = {
			{N, 3},
			{1, 4}
	};
	static final byte[][] p17 = {
			{3, N},
			{4, 2}
	};
	
	//4 tiles
	static final byte[][] p18 = {
			{N, 2},
			{1, 2},
			{N, 2}
	};
	static final byte[][] p19 = {
			{1, 2},
			{3, 2}
	};
	static final byte[][] p20 = {
			{2, 1},
			{N, 3},
			{N, 4}
	};
}
