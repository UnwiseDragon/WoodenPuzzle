package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Solver {
	public static void solver(Board b, ArrayList<Piece> ps) {
		
		//priority board queue
		PriorityQueue<Board> pbq = new PriorityQueue<Board>();
		RoadToSolution failed_boards = new RoadToSolution();
		
		//first generate all valid placements for all pieces
		for(Iterator<Piece> itr = ps.iterator(); itr.hasNext();) {
			Piece p = itr.next();
			p.set_valid_placements(SupportFunctions.generate_valid_placements(p, b));
		}
		//sort ps in ascending order (ie. process the pieces with the fewest board placements first)		
		Collections.sort(ps);
//		Collections.reverse(ps);
//		Collections.shuffle(ps);
//		Collections.shuffle(ps);
		
		int states_visited = 0;
		
		Board originb = new Board(b.get_parent(), b.get_occupied(), b.get_pieces(), b.get_pcoords());
		Iterator<Piece> ps_itr = ps.iterator();
		
		pbq.offer(b);
		
		int i = 0;
		
		while(!b.solved() && !pbq.isEmpty()) {
			b = pbq.poll();
			System.out.println("pbq size "+pbq.size());
			System.out.println(b);
			i = b.get_num_placed();
			if (b.solved()) break;
			
			states_visited++;
			
			//if b has led to failure don't bother
			//this doesn't actually work and I'm not sure why
			if(failed_boards.contains(b)) {
				System.out.println("Failed board");
				continue;
			}
			
			System.out.println("num placed " + i);
			
			Piece cur = null;
			Iterator<Tuple> curxs = null;
			Iterator<State> curss = null;
			
			if(b.contains_isolated_tiles()) {
				failed_boards.add(b);
				continue;
			}
			
			cur = ps.get(i);
			curxs = cur.get_valid_placements().get_xs_itr();
			curss = cur.get_valid_placements().get_states_itr();
			
			System.out.println("\nCurrent piece:\n" + cur);
			System.out.println("Number of placements: "+cur.get_valid_placements().get_num_coords());
//			if(cur.get_valid_placements().get_num_coords() == 2) {
//				System.out.println("AAAAAAAAAAA");
//				Tuple temp = curxs.next();
//				State stemp = curss.next();
//			}
			while(curxs.hasNext() && curss.hasNext()) {
				Tuple cxs = curxs.next();
				
				State css = curss.next();
//				System.out.println("cxs " + cxs);
//				System.out.println("css " + css);
				Board newb = b.place_piece(cur, cxs, css);
				
				if (newb != null) {
					System.out.println("Yep! Piece placed here.");
					pbq.offer(newb);
				}
				else {
					failed_boards.add(b);
				}
//				else System.out.println("Newb was null");
			}
						
		}
		
		if(b.solved()) {
			System.out.println("Solved!");
			do {
				System.out.println();
//				System.out.println(b);
				String[][] s2 = new String [b.WIDTH][b.WIDTH];
				
//				for (int q=0; q<20; q++) {
//					System.out.print(b.get_pcoords()[q] + ", ");
//				}
				
				for (int q=0; q<20; q++) {
					Tuple t = b.get_pcoords()[q];
					if(t == null) continue;
					int x = t.get_x();
					int y = t.get_y();
					Piece p = b.get_pieces()[q];
					
					for (int k=0; k<p.get_height(); k++) {
						for (int l=0; l<p.get_width(); l++) {
							if(p.get_tiles()[k][l] == -1) continue;
							if (p.get_piece_no() < 10) s2[y+k][x+l] = Integer.toString(p.get_piece_no()) + " ";
							else s2[y+k][x+l] = Integer.toString(p.get_piece_no());
						}
					}
				}
				
				for(int j = 0; j<b.WIDTH; j++) {
					for(int k = 0; k<b.WIDTH; k++) {
						if(s2[j][k] == null) s2[j][k] = ". ";
						System.out.print(s2[j][k] + " ");
					}
					System.out.println();
				}
				
				System.out.println();
				
				b = b.get_parent();
			} while (b != null);
			System.out.println("A total of " + states_visited + " states were visited to find this solution.");
		}
		else System.out.println("Failed :(");
		System.out.println(ps);
	}
	
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
		
		Board b = new Board();
		
		solver(b, ps);
	}
}
