public class Tile {
	//Tile's color or element. Possible types defined in Board.
	String type;
	//Tile's position based on matrix
	int row, col; 
	
	public Tile(String type, int row, int col) {
		this.type = type;
		this.row = row;
		this.col = col;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPos(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public String toString() {
		return this.type;
	}
}
