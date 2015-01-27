package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class SplashScreen {
	private Group myRoot;
	
	/**
	 * This function will initialize the splash screen. The splash screen needs to display text that says the title
	 * of the program, which will come from the class that returns the input stream of the property files.
	 * 
	 * This function will also have to have add button that takes it to the FileLoaderScreen class.
	 */
	public Scene init(Group root, int width, int height){
		//myRoot = root
		//addTitle()
		//addLoadButton()
		return new Scene(root, width, height, Color.WHITE);
	}
	
	/**
	 * This function will add the title. This is example code of what it might do. It will have to get the name of the
	 * program, and position of the title from the .properties file which will then dictate where it goes.
	 */
	private void addTitle(){
		//String splashTitle = getProp().getProperty("title");
		//int xTitle = getProp().getProperty("TITLE_X");
		//int yTitle = getProp().getProperty("TITLE_Y");
	}
	
	/**
	 * This function will add the button to the scene. This includes all the formatting of the button.
	 * It will have to get the button's position from the .properties file. 
	 */
	private void addLoadButton(){
		//button.setOnAction(e -> goToFileLoaderScreen);
	}
	
	/**
	 * This function, when clicked, will take the user to the fileLoader class, which will allow the user to
	 * select a file they want to load.
	 */
	private void goToFileLoaderScreen(KeyEvent e){
		//go to file loader class
	}
}
