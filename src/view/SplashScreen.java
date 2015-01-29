package view;

import controller.CellSocietyController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SplashScreen {
	private Group myRoot;
	private int myWidth;
	private int myHeight;
	private CellSocietyController myController;
	
	
	/**
	 * This function will initialize the splash screen. The splash screen needs to display text that says the title
	 * of the program, which will come from the class that returns the input stream of the property files.
	 * 
	 * This function will also have to have add button that takes it to the FileLoaderScreen class.
	 */
	public Scene init(int width, int height){
		myRoot = new Group();
		myWidth = width;
		myHeight = height;
		myController = new CellSocietyController(width, height);
		addTitle();
		addLoadButton();
		return new Scene(myRoot, width, height, Color.WHITE);
	}
	
	/**
	 * This function will add the title in. This is example code of what it might do. It will have to get the name of the
	 * program from the .properties file which will then dictate where it goes.
	 */
	private void addTitle(){
		//getProp() should return a Property which has the inputstream of the .property file loaded into it.
		String splashTitle = getProp().getProperty("title");
		Text splashTitleText = new Text(splashTitle);
		splashTitleText.setTranslateX(myWidth/2 - splashTitleText.getLayoutBounds().getWidth());
		splashTitleText.setTranslateY(myHeight / 4);
		myRoot.getChildren().add(splashTitleText);
	}
	
	/**
	 * This function will add the button to the scene. This includes all the formatting of the button.
	 * It will have to get the button's position from the .properties file. 
	 */
	private void addLoadButton(){
		String loadButtonString = getProp().getProperty("load button text");
		Button loadButton = new Button(loadButtonString);
		myRoot.getChildren().add(loadButton);
		loadButton.setOnAction(e -> goToFileLoaderScreen());
	}
	
	/**
	 * This function, when clicked, will take the user to the fileLoader class through the controller, which will 
	 * allow the user to select a file they want to load.
	 */
	private void goToFileLoaderScreen(){
		myController.transitionToFileLoaderScreen();
	}
}
