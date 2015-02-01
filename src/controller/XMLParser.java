package controller;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private Integer myGrid[][]; //needs a Square class for this to work
	
	public XMLParser(File XMLFile){
		
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName() + "\n"); //for debugging
			
			//READ PARAMETERS (make into function)
			//clean this code below up somehow? seems like an unecessary number of lines
			NodeList parameterList = doc.getDocumentElement().getElementsByTagName("parameters");
			Node parameter = parameterList.item(0);
			NodeList parameterChildren = parameter.getChildNodes();
			
			for(int i=0; i<parameterChildren.getLength(); i++){
				Node currentParam = parameterChildren.item(i);
				myParameters.put(currentParam.getNodeName(), currentParam.getNodeValue());
				System.out.println("Current Param:" + currentParam.getNodeType() + "--" + currentParam.getNodeValue()); //for debugging
			}
			
			//READ GRID (make into function)
			//also clean this code up. It is practically duplicated from above too
			NodeList gridList = doc.getDocumentElement().getElementsByTagName("grid");
			Node grid = gridList.item(0);
			NodeList gridChildren = grid.getChildNodes();
			
			for(int i=0; i<gridChildren.getLength(); i++){
				Node currentRow = gridChildren.item(i);
				String rowString = currentRow.getNodeValue();
				String[] splitRow = rowString.split(" ");
				System.out.println("Split Row " + splitRow);
				
				//Fill grid with new "squares"
				for(int j=0; j<splitRow.length; j++){
					Integer newSquare = Integer.parseInt(splitRow[j]);
					myGrid[i][j] = newSquare;
				}
				
			}
			
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	//function to return ArrayList that signifies the Grid
	public Integer[][] getGrid(){
		return myGrid;
	}
	
	//function to return HashMap of parameters
	public HashMap<String, String> getParameters(){
		return myParameters;
	}
	
	
	
	
	
}
