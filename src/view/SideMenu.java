package view;

import controller.CellSocietyController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SideMenu {

	//this is a tentative class
	//I thought this could be the view that contains sliders and stuff
	//it will be a little menu off to the side of the main screen
	
	private int myWidth;
	private int myHeight;
	private String[] myNames;
	private double[] myValues;
	private double[] myMins;
	private double[] myMaxs;
	private CellSocietyController myController;

	public SideMenu(int width, int height, String[] names, double[] values, double[] min, double[] max, 
			CellSocietyController controller){
		myWidth = width/4;
		myHeight = height/2;
		myNames = names;
		myValues = values;
		myMins = min;
		myMaxs = max;
		myController = controller;
	}
	
	public void createOptionWindow() {
		Group options = new Group();
		//open new window with graph
		Stage stage = new Stage();
		stage.setTitle("Options");
		options.getChildren().add(addOptionsPanel());
		stage.setScene(new Scene(options, myWidth, myHeight, Color.LIGHTGRAY));
		stage.setResizable(false);
		stage.setX(myWidth * 7); 
		stage.show();
		
	}

	private Node addOptionsPanel() {
		VBox sliders = new VBox(myHeight / 20);
		for(int i = 0; i < myNames.length; i++){
			Text label = new Text(myNames[i] + ":");
			label.setTranslateX(myWidth/20);
			sliders.getChildren().add(label);
			
			final int index = i;
			Slider slider = new Slider();
			slider.setValue(myValues[i]);
			slider.setMin(myMins[i]);
			slider.setMax(myMaxs[i]);
			slider.setBlockIncrement((myMaxs[i] - myMins[i]) / 10);
			slider.setShowTickLabels(true);
			sliders.getChildren().add(slider);
			
			slider.valueProperty().addListener(new ChangeListener<Number>() {
	            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
	                    myController.changeSimulationParameters(myNames[index], (double) new_val);
	            }
	        });
		}
		
		return sliders;
	}
}
