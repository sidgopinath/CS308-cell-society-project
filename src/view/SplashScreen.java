package view;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import controller.CellSocietyController;

/**
 * This function will initialize the splash screen. The splash screen needs to display text that says the title
 * of the program, which will come from the class that returns the input stream of the property files.
 * @author Sunjeev
 *
 */

public class SplashScreen {
	private static final int SPLASH_TEXT_SIZE = 5;
	private static final int LOAD_BUTTON_SIZE = 1;	
	private Group myRoot;
	private int myWidth;
	private int myHeight;
	private CellSocietyController myController;
	private ArrayList<Button> myButtons;
	private static ResourceBundle myProperties;
	
	public SplashScreen(CellSocietyController controller){
		myController = controller;
		myProperties = ResourceBundle.getBundle("resources/resources");
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
		myButtons = new ArrayList<>();
		addTitle();
		addLoadButton();
		addRandomButton();
		addProbablityButton();
		for(Button button: myButtons){
			button.setPrefWidth(myWidth / 5);
			button.setTranslateX((myWidth - button.getPrefWidth()) / 2);
		}
		return myRoot;
	}
	
	/**
	 * collects integers for probability distribution
	 */
	private void addProbablityButton() {
		Button probButton = new Button("Probability");
		myButtons.add(probButton);
		final Scene snapScene = new Scene(probButton);  
		snapScene.snapshot(null);  
		formatNode(probButton,myWidth /2, myHeight * 1/2, LOAD_BUTTON_SIZE );
		probButton.setOnAction(e -> myController.transitionToFileLoaderScreen());
	}
	/**
	 * remove other buttons, and have user select grid height, width, and type of simulation
	 */
	private void addRandomButton() {
		Button probButton = new Button("Random");
		myButtons.add(probButton);
		final Scene snapScene = new Scene(probButton);  
		snapScene.snapshot(null);  
		formatNode(probButton,myWidth/2, myHeight * 3/4, LOAD_BUTTON_SIZE );
		probButton.setOnAction(e -> myController.transitionToFileLoaderScreen());
	}
	/**
	 * This function will add the title in. This is example code of what it might do. It will have to get the name of the
	 * program from the .properties file which will then dictate where it goes.
	 */
	private void addTitle(){
		String splashTitle = myProperties.getString("splash_title"); 
		Text splashTitleText = new Text(splashTitle);
		formatNode(splashTitleText, (myWidth - splashTitleText.getLayoutBounds().getWidth())/2, 
				myHeight / 4, SPLASH_TEXT_SIZE);
	}
	
	/**
	 * This function will add the button to the scene. This includes all the formatting of the button.
	 * It will have to get the button's position from the .properties file. 
	 */
	private void addLoadButton(){
		String loadButtonString = myProperties.getString("load_button_string");
		Button loadButton = new Button(loadButtonString);
		myButtons.add(loadButton);
		final Scene snapScene = new Scene(loadButton);  
		snapScene.snapshot(null);  
		formatNode(loadButton,myWidth / 2, myHeight * 5/8, LOAD_BUTTON_SIZE );
		loadButton.setOnAction(e -> myController.transitionToFileLoaderScreen());
	}
	
	
	
	private void formatNode(Node node, double width, double height, int scale) {
		node.setTranslateX(width);
		node.setTranslateY(height);
		node.setScaleX(scale);
		node.setScaleY(scale);
		myRoot.getChildren().add(node);
	}
}
