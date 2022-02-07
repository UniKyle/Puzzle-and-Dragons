public class Board {
	//board width
	int width = 6;
	//board height
	int height = 5;
	//list of possible tiles to be generated
	char[] tileList = {'r', 'b', 'g', 'l', 'd', 'h'};
	//array of Tiles with size=width*height
	Tile[] board = new Tile[width*height];
	
	/**
	 * Default constructor for Board. Creates a randomized board state
	 * with no matches.
	 */
	public Board() { 
		randomizeBoard();
	}
	
	/**
	 * Displays the current board state as a 2d 6x5 grid
	 * @return a string representing the board state
	 */
	public String toString() {
		String strBoard = "";

		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				strBoard += board[i*height+j].getType() + " ";
			}
			strBoard+= "\n";	
		}
		return strBoard;
	}

	/**
	 * Displays the Tiles in the board as a singular string
	 * @return a string of all Tiles
	 */
	public String toString2() {
		String strBoard = "";

		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				strBoard += board[i*height+j].getType();
			}
		}
		return strBoard;
	}
	/**
	 * Takes string of chars and creates a board state from it.
	 * @param strBoard - string of chars board is built with
	 * @throws IllegalArgumentException when width*height tiles are not provided
	 */
	public void setBoard(String strBoard) {
		Tile newTile;
		
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				newTile = new Tile(strBoard.charAt(i*height+j), new Point(i, j));
				board[i*j] = newTile;
			}
		}
	}
	
	public Tile[] getBoard() {
		return board;
	}
	
	/**
	 * Generates a random board state from possible tiles.
	 */
	public void randomizeBoard() {
		Tile newTile;
		char randType;

		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				randType = tileList[(int) (Math.random()*6)];
				System.out.println("random type is " + randType);
				newTile = new Tile(randType, new Point(i, j));
				board[i*height+j] = newTile;
			}
		}
	}
	/*
	public void initBoard() { //generates initial board (cannot have 3 of the same tile connected horizontally or vertically)
		randomizeBoard();
		do {randomizeBoard();} 
		while (for)
	}
	
	public void checkMatches() { //checks board for all matches. returns array of arrays where each array has every tile part of combo?
		int matches[][] = new int[0][0];
	}
	*/
}
