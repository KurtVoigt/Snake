package theGame;

import java.awt.*;//functionality on the JFrame
import javax.swing.JFrame;//container in which awt and others play in

import graphics.mainField;
public class mainWindow extends JFrame{

	public mainWindow() {//constructor
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//forces a close on user action FRAME
		setTitle("Snake");//title of window
		setResizable(false); //allows window to be resized, Maybe change later
		
		init();
	}
	
	public void init() {
		setLayout(new GridLayout(1 ,1, 0,0));//look up
		
		mainField field = new mainField();//mainFIELD IS MY SCREEN
		add(field);
		pack();//whatevers in this window, make the window that size
		
		setLocationRelativeTo(null);//centers frame to screen
		setVisible(true);//visible to user
	}
	
	public static void main(String[] args) {//MAIN
		new mainWindow();
		
	}
	
}
