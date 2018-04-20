package solver;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Note: this class is solely for testing current functionality
 * it is not for actually running beyond stubs
 */
public class TestMain {
	public static void main(String args[]) {
		ArrayList<Piece> ps = new ArrayList<Piece>();
		ps.add(new Piece(1, Pieces.p1));
		ps.add(new Piece(2, Pieces.p2));
		ps.add(new Piece(3, Pieces.p3));
		ps.add(new Piece(4, Pieces.p4));
		ps.add(new Piece(5, Pieces.p5));
		ps.add(new Piece(6, Pieces.p6));
		ps.add(new Piece(7, Pieces.p7));
		ps.add(new Piece(8, Pieces.p8));
		ps.add(new Piece(9, Pieces.p9));
		ps.add(new Piece(10, Pieces.p10));
		ps.add(new Piece(11, Pieces.p11));
		ps.add(new Piece(12, Pieces.p12));
		ps.add(new Piece(13, Pieces.p13));
		ps.add(new Piece(14, Pieces.p14));
		ps.add(new Piece(15, Pieces.p15));
		ps.add(new Piece(16, Pieces.p16));
		ps.add(new Piece(17, Pieces.p17));
		ps.add(new Piece(18, Pieces.p18));
		ps.add(new Piece(19, Pieces.p19));
		ps.add(new Piece(20, Pieces.p20));
		
		for(Iterator<Piece> itr = ps.iterator(); itr.hasNext();) {
			System.out.println(itr.next());
		}
		
		System.out.println("Normal piece 20");
		System.out.println(ps.get(19));
		System.out.println("Mirrored piece 20");
		System.out.println(ps.get(19).mirror());
		System.out.println("Flipped piece 20");
		System.out.println(ps.get(19).flip_on_axis());
		System.out.println("Mirrored and flipped piece 20");
		System.out.println(ps.get(19).mirror().flip_on_axis());
		
		System.out.println("Normal piece 1");
		System.out.println(ps.get(0));
		System.out.println("Mirrored piece 1");
		System.out.println(ps.get(0).mirror());
		System.out.println("Flipped piece 1");
		System.out.println(ps.get(0).flip_on_axis());
		System.out.println("Mirrored and flipped piece 1");
		System.out.println(ps.get(0).mirror().flip_on_axis());
		
		System.out.println(ps.get(1) == ps.get(2));
	}
}
