package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private Integer myGrid[][];
	
	public XMLParser(File XMLFile){
		
		try{
			Document doc = initializeDoc(XMLFile);
			NodeList parameterChildren = initializeNodeList(doc, "parameters");		
			readParameters(parameterChildren);
			NodeList gridChildren = initializeNodeList(doc, "grid");		
			readGrid(gridChildren);
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Read in the parameters
	 * Put them in the HashMap
	 */
	private void readParameters(NodeList parameterChildren) {
		for(int i=0; i<parameterChildren.getLength(); i++){
			Node currentParam = parameterChildren.item(i);
			myParameters.put(currentParam.getNodeName(), currentParam.getNodeValue());
			System.out.println("Current Param:" + currentParam.getNodeType() + "--" + currentParam.getNodeValue()); //for debugging
		}
	}


	/**
	 * Read in the Grid row by row
	 * Create the new "squares" by placing ints in the grid array
	 */
	private void readGrid(NodeList gridChildren) {
		for(int i=0; i<gridChildren.getLength(); i++){
			Node currentRow = gridChildren.item(i);
			String rowString = currentRow.getNodeValue();
			String[] splitRow = rowString.split(" ");
			System.out.println("Split Row " + splitRow); //for debugging
			
			for(int j=0; j<splitRow.length; j++){
				Integer newSquare = Integer.parseInt(splitRow[j]);
				myGrid[i][j] = newSquare;
			}
		}
	}


	/**
	 * Function to do initial set-up
	 * Returns a node list of all elements of one type
	 * Ex. returns list of parameters or list of grid rows
	 */
	private NodeList initializeNodeList(Document doc, String element) {
		NodeList elementList = doc.getDocumentElement().getElementsByTagName(element);
		Node elementNode = elementList.item(0);
		NodeList elementChildren = elementNode.getChildNodes();
		return elementChildren;
	}


	/**
	 * Does initial set-up of document file.
	 */
	private Document initializeDoc(File XMLFile)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(XMLFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName() + "\n"); //for debugging
		return doc;
	}
	
	
	/**
	 * Returns myGrid
	 * Called by controller
	 */
	public Integer[][] getGrid(){
		return myGrid;
	}
	
	
	/**
	 * Returns HashMap of parameters
	 * Called by controller
	 */
	public HashMap<String, String> getParameters(){
		return myParameters;
	}
	
	
	
	
	
}
