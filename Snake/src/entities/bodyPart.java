package entities;

import java.awt.Graphics;
import java.awt.Color;

public class bodyPart {//creates the middle parts of the snake
	private int xCoor, yCoor, width, height;
	
	public bodyPart(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {//draws the screen
		g.setColor(Color.BLACK); 
		g.fillRect(xCoor* width, yCoor * height, width, height);//gives snake depth
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width + 2, yCoor * height + 2, width, height);//draws initial snake state
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
}
