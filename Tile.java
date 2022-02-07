public class Tile {
	char type; //tile's color
	int pos;
	
	public Tile(char type, int pos) {
		this.type = type;
		this.pos = pos;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public char getType() {
		return this.type;
	}
	
	public int getPos() {
		return this.pos;
	}
}
