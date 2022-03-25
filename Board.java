import java.util.ArrayList;

/**
 * Represents the board to be used in Simulator. A 2D grid of tiles of different
 * types. 
 */
public class Board {
	//board width
	int width = 6;
	//board height
	int height = 5;
	//list of possible tiles to be generated
	String[] tileList = {"r", "b", "g", "l", "d", "h"};
	//array of Tiles with size=width*height
	ArrayList<Tile> board = new ArrayList<Tile>();

	
	/**
	 * Default constructor for Board. Creates a randomized board state
	 * with no matches.
	 */
	public Board() { 
		randomizeBoard();
	}
	
	/**
	 * Displays the current board state as a 2D grid
	 * @return a string representing the board state
	 */
	public String to2dString() {
		String strBoard = "";

		for (int i=0; i<board.size(); i++) {
			strBoard += board.get(i).getType() + " ";
			if ((i+1)%width==0) {
				strBoard+= "\n";
			}
		}
		return strBoard;
	}

	/**
	 * Displays the tiles in the board as a singular string
	 * @return a string of all tiles
	 */
	public String toString() {
		String strBoard = "";

		for (int i=0; i<board.size(); i++) {
			strBoard += board.get(i).getType();
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
		
		if (strBoard.length()<board.size()) {
			throw new IllegalArgumentException("Invalid board state");
		}
		strBoard = strBoard.toLowerCase();
		board.clear();
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				newTile = new Tile(strBoard.substring(i*height+j, i*height+j+1), i, j);
				board.add(newTile);
			}
		}
	}
	
	public ArrayList<Tile> getBoard() {
		return board;
	}
	
	/**
	 * Generates a random board state from possible tiles.
	 */
	public void randomizeBoard() {
		String randType;
		Tile tile;

		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				randType = tileList[(int) (Math.random()*tileList.length)];
				tile = new Tile(randType, i, j);
				board.add(tile);
			}
		}
	}
	
	/**
	 * Generates initial board (cannot have 3 of the same tile connected 
	 * horizontally or vertically).
	 */
	// public void initBoard() {
	// 	randomizeBoard();
	// 	do {randomizeBoard();} 
	// 	while (for)
	// }
	
	//matching tiles (used for findMatches and DFSMarking)
	ArrayList<Tile[]> matches = new ArrayList<Tile[]>();
	ArrayList<Tile> m = new ArrayList<Tile>(); //TODO: rename?
	int count;
	/**
	 * Checks board for all matches and marks all tiles part of a match. A set 
	 * of matching tiles occurs when there are 3+ tiles connected vertically 
	 * or horizontally.
	 */	
	public void findMatches() { 
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				DFSMarking(board.get(i*height+j), board.get(i*height+j).getType());
				//System.out.println("DFS ran on " + board.get(i*height+j).getType() + ", result: " + m);

				if (m.size()>=3) {
					count++;
					for (Tile t : m) {
						if (getColLength(t)<2 && getRowLength(t)<2) {
							t.setType(t.getType().substring(0,1) + "xx");
						}
					}
				}
				else {
					for (Tile t : m) {
						t.setType(t.getType().substring(0,1));
					}
				}
				m.clear();
			}
		}
	}

	/**
	 * DFS helper for findMatches().
	 */
	public void DFSMarking(Tile t, String prevType) {
		//if tile is out of bounds, not of the same type, or already marked, then returns
		if (t.getRow()<0 || t.getCol()<0 || t.getRow()>=width || t.getCol()>=height 
				|| !t.getType().equals(prevType.substring(0,1)) || t.getType().length()>1) {
			return;
		}

		t.setType(t.getType()+count);
		m.add(t);

		//check tile to the left of current tile if it exists
		if (board.indexOf(t)%width!=0) {
			DFSMarking(board.get(board.indexOf(t)-1), t.getType());
		}
		//right adjacent tile
		if (board.indexOf(t)%width!=width-1) {
			DFSMarking(board.get(board.indexOf(t)+1), t.getType());
		}
		//down adjacent tile
		if (board.indexOf(t)<width*height-width) {
			DFSMarking(board.get(board.indexOf(t)+width), t.getType());
		}
		//up adjacent tile
		if (board.indexOf(t)>=width) {
			DFSMarking(board.get(board.indexOf(t)-width), t.getType());
		}
		
	}

	/**
	 * Returns number of horizontally connected tiles with the same type as t
	 */
	public int getRowLength(Tile t) {
		int count = -1;
		Tile currTile = t;

		//search to the right 
		if (board.indexOf(t)%width!=width-1) {
			currTile = t;
			while (currTile.getType().equals(t.getType())) {
				currTile = board.get(board.indexOf(currTile)+1);
				count++;
				//if next row has been reached
				if (board.indexOf(currTile)%width==0) {
					break;
				}
			}
		}
		else count++;

		//search to the left
		if (board.indexOf(t)%width!=0) {
			currTile = t;
			while (currTile.getType().equals(t.getType())) {
				currTile = board.get(board.indexOf(currTile)-1);
				count++;
				//if next row has been reached
				if (board.indexOf(currTile)%width==width+1) {
					break;
				}
			}
		}
		else count++;

		return count-1;
	}

	/**
	 * Returns number of vertically connected tiles with the same type as t
	 */
	public int getColLength(Tile t) {
		int count = -1;
		Tile currTile = t;

		//search above 
		if (board.indexOf(t)>=width) {
			currTile = t;
			while (currTile.getType().equals(t.getType())) {
				//if top row has been reached
				if (board.indexOf(currTile)<width) {
					if (currTile.getType().equals(t.getType())) count++;
					break;
				}
				currTile = board.get(board.indexOf(currTile)-width);
				count++;
			}
		}
		else count++;
		//search below
		if (board.indexOf(t)<width*height-width) {
			currTile = t;
			while (currTile.getType().equals(t.getType())) {
				//if bottom row has been reached
				if (board.indexOf(currTile)>=width*height-width) {
					if (currTile.getType().equals(t.getType())) count++;
					break;
				}
				currTile = board.get(board.indexOf(currTile)+width);
				count++;
			}
		}
		else count++;

		return count-1;
	}

}
