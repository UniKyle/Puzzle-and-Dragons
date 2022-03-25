/**
 * Displays the Board and stuff
 */
public class Simulator {

	public static void main(String[] args) {
		Board board = new Board();
		board.setBoard("HHLDDDHHBGBDGHLBBBGGGLBDGGGGHB");
		System.out.println("before:\n" + board.to2dString());
		board.findMatches();
		//System.out.println(board.toString());
		System.out.println("after:\n" + board.to2dString());

	}

}
