package graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import entities.Food;
import entities.bodyPart;

public class mainField extends JPanel implements Runnable{//actual field on which the game will be played 
	private Random rand;
	private static final long serialVersionUID = 1l;
	public static final int WIDTH = 400, HEIGHT = 400;
	private Thread thread;
	private boolean running = false;
	private bodyPart b;
	private ArrayList<bodyPart> snake;
	
	private Food food;
	private ArrayList<Food> foodList;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private int xCoor = 10, yCoor =10;
	private int size = 5;
	
	private int ticks = 0;//makes sure it doesn't move with every loops of run loop as the game would be too quick
	
	private Key key;
	
	public mainField() {
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		rand = new Random();
		
		snake = new ArrayList<bodyPart>();
		foodList = new ArrayList<Food>();
		start();
	}
	
	public void update() {// updates the game at the interval of sleep
		if(snake.size() == 0) {
		b = new bodyPart(xCoor, yCoor, 10);
		snake.add(b);
		}
		
		if(foodList.size() == 0) {//make new apple
			int xCoor = rand.nextInt(39);
			int yCoor = rand.nextInt(39);
			
			food = new Food(xCoor, yCoor, 10);
			foodList.add(food);
		}
		
		for(int i=0; i < foodList.size(); i++) {
			if(xCoor == foodList.get(i).getxCoor() && yCoor == foodList.get(i).getyCoor()) {
				size++;
				foodList.remove(i);
				i--;
			}
		}
		for(int i = 0; i < snake.size(); i++) {
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor() ) {
				if( i != snake.size()-1) {//checks for collision but excludes the head
					stop();
				}
			}
		}
		
		if(xCoor <0 || xCoor >39 || yCoor<0 || yCoor > 39) {//stop(); //end of screen
			if(xCoor > 39) xCoor = -0;
			else if(xCoor < 0) xCoor = 38;
			else if(yCoor < 0) yCoor = 38;
			else if(yCoor > 39) yCoor = 0;
		}
		ticks++;
		//System.out.println(ticks);
		
		if(ticks > 450000) {
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			ticks = 0;// reset counter
			
			b = new bodyPart(xCoor, yCoor, 10);
			snake.add(b);
			
			if(snake.size() > size) {
				snake.remove(0);
			}
		}
	}
	
		
	
	public void paint(Graphics g) { // responsible for drawing the screen at each update
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.ORANGE);
		for(int i =0; i<(WIDTH/10); i++) { //draws a grid, maybe take out
			g.drawLine(i*10, 0, i*10, HEIGHT); //two sets of point x1, y1, x2, y2 start and endpoints
		}
		for(int i =0; i<(HEIGHT/10); i++) {
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
		for(int i =0; i < snake.size(); i++) {//draws the snake at each position stored in each bodyPart object!
			snake.get(i).draw(g);
		}
		for(int i=0; i < foodList.size(); i++) {
			foodList.get(i).draw(g);
		}
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game Loop");
		thread.start();
	}
	
	public void stop() {
		running = false;
		try{
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {

		while(running) {
			update();
			repaint();
		}
	}
	
	private class Key implements KeyListener {

		
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_RIGHT && !left) {
				up = false;
				down = false;
				right = true;
			} 
			 if(key == KeyEvent.VK_LEFT && !right) {
				up = false;
				down = false;
				left = true;
			} 
			
			 if(key == KeyEvent.VK_UP && !down) {
				left = false;
				right = false;
				up = true;
			}
			 if(key == KeyEvent.VK_DOWN && !up) {
				left = false;
				right = false;
				down = true;
			}
			 //System.out.println("up ="+up+" down ="+down+" left=" +left+ " right ="+right+" key is"+key);
			
			}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		
			
			
			
			
			
		



	}	
	
}
