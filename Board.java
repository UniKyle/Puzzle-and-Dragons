public class Board {
	int width = 6; //board width
	int height = 5; //board height
	char[] tileList = {'r', 'b', 'g', 'l', 'd', 'h'}; //list of possible tiles to be generated
	//char[] tileList = {'r', 'b', 'g'}; //use for tricolor
	Tile[][] board = new Tile[height][width];
	
	public Board() { 
		randomizeBoard();
		
	}
	
	public String toString() { //returns board as a 2d grid
		String strBoard = "";
		for (Tile[] row : board) {
			for (Tile tile : row) {
				strBoard+=tile.getType() + " ";
			}
			strBoard+="\n";
		}
		return strBoard;
	}
	
	public String toString2() { //returns board as a single line of characters
		String strBoard = "";
		for (Tile[] row : board) {
			for (Tile tile : row) {
				strBoard+=tile.getType();
			}
		}
		return strBoard;
	}
	//Takes string of chars and converts it to board state. first 6 chars = top row, etc. Error if board is not filled.
	public void setBoard(String strBoard) { 
		int pos = 0;
		if (strBoard.length()!=width*height) throw new IllegalArgumentException("Board must be completely filled");
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j<board[0].length; j++) {
				board[i][j] = new Tile(strBoard.charAt(pos), pos);
				pos++;
			
			}
		}
	}
	
	public Tile[][] getBoard() {
		return board;
	}
	
	public void randomizeBoard() {
		//figure out why its going to 25 and not 30??
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j<board[0].length; j++) {
				board[i][j] = new Tile(tileList[(int) (Math.random()*tileList.length)], j+i*board.length);
				System.out.println(j+i*board.length);
				
			
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
