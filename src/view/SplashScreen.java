package view;

import controller.CellSocietyController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SplashScreen {
	private static final int SPLASH_TEXT_SIZE = 5;
	private static final int LOAD_BUTTON_SIZE = 3;
	private static final double FUDGE = 15;
	
	private Group myRoot;
	private int myWidth;
	private int myHeight;
	private CellSocietyController myController;
	
	private boolean transitionToFileLoader = false;
	
	public SplashScreen(CellSocietyController controller){
		myController = controller;
	}
	/**
	 * This function will initialize the splash screen. The splash screen needs to display text that says the title
	 * of the program, which will come from the class that returns the input stream of the property files.
	 * 
	 * This function will also have to have add button that takes it to the FileLoaderScreen class.
	 */
	public Group getNode(int width, int height){
		myRoot = new Group();
		myWidth = width;
		myHeight = height;
		addTitle();
		addLoadButton();
		return myRoot;
	}
	
	/**
	 * This function will add the title in. This is example code of what it might do. It will have to get the name of the
	 * program from the .properties file which will then dictate where it goes.
	 */
	private void addTitle(){
		//getProp() should return a Property which has the inputstream of the .property file loaded into it.
		String splashTitle = "Cellular Automata"; //getProp().getProperty("title");
		Text splashTitleText = new Text(splashTitle);
		splashTitleText.setTranslateX(center(splashTitleText));
		splashTitleText.setTranslateY(myHeight / 4);
		splashTitleText.setScaleX(SPLASH_TEXT_SIZE);
		splashTitleText.setScaleY(SPLASH_TEXT_SIZE);
		myRoot.getChildren().add(splashTitleText);
	}
	
	/**
	 * This function will add the button to the scene. This includes all the formatting of the button.
	 * It will have to get the button's position from the .properties file. 
	 */
	private void addLoadButton(){
		String loadButtonString = "load"; //getProp().getProperty("load button text");
		Button loadButton = new Button(loadButtonString);
		loadButton.setTranslateX(center(loadButton) - FUDGE);
		loadButton.setTranslateY(myHeight * 3/4);
		loadButton.setScaleX(LOAD_BUTTON_SIZE);
		loadButton.setScaleY(LOAD_BUTTON_SIZE);
		myRoot.getChildren().add(loadButton);
		loadButton.setOnAction(e -> myController.transitionToFileLoaderScreen());
	}

	private double center(Node n) {
		return (myWidth - n.getLayoutBounds().getWidth())/2;
	}
}
