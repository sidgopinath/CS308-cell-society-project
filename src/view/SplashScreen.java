package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	private ArrayList<Node> myAskScreenNodes;
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
		myAskScreenNodes = new ArrayList<>();
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
		probButton.setOnAction(e -> displayAskInformatio(true));
	}
	
	private void displayAskInformatio(boolean probability) {
		removeButtons();
		TextField[] dimensions = addDimensionsText();
		TextField simulationName = addSimulationNameText();
		
		Button submit = new Button("submit");
		myAskScreenNodes.add(submit);
		submit.setTranslateX(myWidth/2);
		submit.setTranslateY(myHeight * 5/6);
		myRoot.getChildren().add(submit);
		
		submit.setOnAction(e -> {
			if(simulationName.getText() != null && dimensions[0].getText() != null && dimensions[1].getText() != null){
				int numTextFields = 0;
				if(simulationName.getText().equals(myProperties.getObject("fire_simulation_name"))){
					numTextFields = 3;
				}
				else if(simulationName.getText().equals(myProperties.getObject("segregation_simulation_name"))){
					numTextFields = 3;
				}
				else if(simulationName.getText().equals(myProperties.getObject("life_simulation_name"))){
					numTextFields = 2;
				}
				else if(simulationName.getText().equals(myProperties.getObject("predator_simulation_name"))){
					numTextFields = 3;
				}
				else{
					System.out.println("Invalid Simulation Name");
					throw new IllegalArgumentException();
				}
				if(probability){
					addProbabilityText(numTextFields, dimensions, simulationName.getText());
				}
				else{
					myController.generateRandomGrid(Integer.parseInt(dimensions[0].getText()), 
							Integer.parseInt(dimensions[1].getText()), simulationName.getText());
				}
			}
		});
	}
	
	
	private void addProbabilityText(int numTextFields, TextField[] dimensions, String simName) {
		removeAskScreenNodes();
		VBox probabilities = new VBox(myHeight / 20);
		for(int i = 0; i < numTextFields; i++){
			HBox cellProbability = new HBox(myWidth / 20);
			cellProbability.setTranslateX(myWidth / 4);
			cellProbability.setTranslateY(myHeight / 2);
			
			Text probabilityName = new Text("Cell " + i + " Probability:");
			cellProbability.getChildren().add(probabilityName);
			
			TextField probabilityText = new TextField();
			probabilityText.setPromptText("Enter Value of Cell");
			cellProbability.getChildren().add(probabilityText);
			
			probabilities.getChildren().add(cellProbability);
		}
		myRoot.getChildren().add(probabilities);
		
		Button runSimulation = new Button("Run Simulation");
		runSimulation.setTranslateX(myWidth/2);
		runSimulation.setTranslateY(myHeight * 5/6);
		
		myRoot.getChildren().add(runSimulation);
		
		runSimulation.setOnAction(e -> {
			HashMap<Integer, Integer> probabilityMap = new HashMap<>();
			for(int i = 0; i < numTextFields; i++){
				HBox probabilityHBox = (HBox) probabilities.getChildren().get(i);
				TextField probability = (TextField) probabilityHBox.getChildren().get(1);
				try{
					probabilityMap.put(i, Integer.parseInt(probability.getText()));
				}
				catch(NumberFormatException e1){
					System.out.println("Invalid Value");
				}
			}
			myController.generateProbabilityRandomGrid(Integer.parseInt(dimensions[0].getText()), 
					Integer.parseInt(dimensions[1].getText()), simName, probabilityMap);
		});
	}
	private void removeAskScreenNodes() {
		for(Node node: myAskScreenNodes){
			if(myRoot.getChildren().contains(node)){
				myRoot.getChildren().remove(node);
			}
		}
		
	}
	private TextField addSimulationNameText() {
		HBox simulation = new HBox(myWidth/20);
		
		Text simulationName = new Text("Simulation Name:");
		simulation.getChildren().add(simulationName);
		
		TextField simulationNameText = new TextField();
		simulationNameText.setPromptText("Enter Name");
		simulation.getChildren().add(simulationNameText);
		
		simulation.setTranslateX(myWidth / 4);
		simulation.setTranslateY(myHeight/2);
		myRoot.getChildren().add(simulation);
		
		myAskScreenNodes.add(simulation);
		
		return simulationNameText;
		
	}
	private TextField[] addDimensionsText() {
		HBox dimensions = new HBox(myWidth / 20);
		
		Text gridHeight = new Text("Grid Height:");
		dimensions.getChildren().add(gridHeight);
		
		TextField gridHeightText = new TextField();
		gridHeightText.setPromptText("Enter Height of Grid");
		dimensions.getChildren().add(gridHeightText);
		
		Text gridWidth = new Text("Grid Width:");
		dimensions.getChildren().add(gridWidth);
		
		TextField gridWidthText = new TextField();
		gridWidthText.setPromptText("Enter Width of Grid");
		dimensions.getChildren().add(gridWidthText);

		dimensions.setTranslateX(myWidth / 20);
		dimensions.setTranslateY(myHeight * 5/8);
		myRoot.getChildren().add(dimensions);
		
		myAskScreenNodes.add(dimensions);
		
		TextField[] ret = new TextField[]{gridHeightText, gridWidthText};
		return ret;
	}
	
	private void removeButtons() {
		for(Button button: myButtons){
			if(myRoot.getChildren().contains(button)){
				myRoot.getChildren().remove(button);
			}
		}
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
		probButton.setOnAction(e -> displayAskInformatio(false));
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
