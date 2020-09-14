package lab06;

public class MyRectangle {
	private int startX, startY, width, height;
	
	public MyRectangle() {
		this.startX = 0;
		this.startY = 0;
		this.width = 0;
		this.height = 0;
	}
	
	public MyRectangle(int posX, int posY, int width, int height) {
		this.startX = posX;
		this.startY = posY;
		this.width = width;
		this.height = height;
	}
	
	public int area() {
		return this.width*this.height;
	}
	
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("Width: " + this.width);
		S.append(" Height: " + this.height);
		S.append(" X: " + this.startX);
		S.append(" Y: " + this.startY);
		return S.toString();
	}

	public boolean isInside(int x, int y)
	{
		// This is the trickiest of the methods to write.  This should
		// return true if point (x,y) is anywhere within the borders of the
		// current MyRectangle (including the borders themselves).  Use a
		// pencil and paper to figure out how this can be determined with
		// just a few simple relational operations.
		boolean inX = (x>=this.startX && x<=(this.startX+this.width));
		boolean inY = (y>=this.startY && y<=(this.startY+this.height));
		return (inX&&inY);
	}

	public void setSize(int newWidth, int newHeight) {
		// Update width and height as shown
		this.width = newWidth;
		this.height = newHeight;
	}

	public void setPosition(int newX, int newY) {
		// Update startX and startY as shown
		this.startX = newX;
		this.startY = newY;
	}

}
