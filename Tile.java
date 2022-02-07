public class Tile {
	//Tile's color or element. Possible types defined in Board.
	char type;
	//Tile's position. (0,0) is bottom left, etc.
	Point pos; 
	
	public Tile(char type, Point pos) {
		this.type = type;
		this.pos = pos;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	public void setPos(Point p) {
		this.pos = p;
	}
	
	public char getType() {
		return this.type;
	}
	
	public Point getPos() {
		return this.pos;
	}
}
