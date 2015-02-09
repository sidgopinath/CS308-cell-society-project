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

/**
 * This is the XML parser class called by the controller
 * It reads in an xml file in the specified format and outputs the grid and parameter hashmap
 * @author Sid
 *
 */

public class XMLParser {

	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private Integer myGrid[][];

	public XMLParser(File XMLFile) {
		try {
			checkFileExtension(XMLFile);
			Document doc = initializeDoc(XMLFile);
			clean(doc.getDocumentElement().getParentNode());
			if(doc.getDocumentElement().getParentNode().toString().equals("simulation")){
				readSimFile(doc);
			}
			else{
				readStyleFile(doc);
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the style file document
	 * Puts all style elements in the parameters map
	 * Might have to be changed to pull style elements in their own style map
	 * @param doc
	 */
	private void readStyleFile(Document doc) {
		NodeList styleChildren = initializeNodeList(doc, "style");
		readParameters(styleChildren);
	}

	private void readSimFile(Document doc) {
		NodeList parameterChildren = initializeNodeList(doc, "parameter");
		readParameters(parameterChildren);
		checkParameters(myParameters);
		NodeList gridChildren = initializeNodeList(doc, "grid");
		createGrid(gridChildren);
		readGrid(gridChildren);
		checkGrid(myGrid);
	}

	/**
	 * Checks grid width and height against input grid
	 * Prints out error message and exits
	 * Perhaps IMPLEMENT try/catch stuff here?
	 * @param grid
	 */
	private void checkGrid(Integer[][] grid) {
		if(grid.length != Integer.parseInt(myParameters.get("gridHeight"))){
			System.out.println("Grid height doesn't match input grid.");
			System.exit(0);
		}
		for(int i=0; i<grid.length; i++){
			if(grid[i].length != Integer.parseInt(myParameters.get("gridWidth"))){
				System.out.println("Grid width doesn't match input grid.");
				System.exit(0);
			}
		}
	}

	/**
	 * IMPLEMENT TRY/CATCH STUFF HERE?
	 * Checks if sim type is there
	 * Could add on more to check if appropriate parameters exist for each sim type
	 * Might make a separate class to dot hat?
	 * @param paramMap
	 */
	private void checkParameters(HashMap<String, String> paramMap) {
		if(!paramMap.containsKey("simtype")){
			System.out.println("No sim type found");
			System.exit(0);
		}
		else if(paramMap.get("simtype") == null){
			System.out.println("Sim type is null");
			System.exit(0);
		}	
	}

	/**
	 * Method to check file extension name
	 * Maybe make this a try/catch situation
	 * Or make it throw an exception? Not sure how this would work exactly
	 * @param xmlFile
	 */
	private void checkFileExtension(File xmlFile) {
		String file = xmlFile.getName();
		if(!(file.endsWith("xml"))){
			System.out.println("Invalid file format. Exiting program.");
			System.exit(0);
		}
	}

	/**
	 * Initialize grid size
	 * @param gridChildren
	 */
	private void createGrid(NodeList gridChildren) {
		Node currentRow = gridChildren.item(0);
		String rowString = currentRow.getTextContent();
		String[] splitRow = rowString.split(" ");
		myGrid = new Integer[gridChildren.getLength()][splitRow.length];
	}

	/**
	 * Read in the parameters Put them in the HashMap
	 * Also used to read in the parameters in the style sheet
	 */
	private void readParameters(NodeList parameterChildren) {
		for (int i = 0; i < parameterChildren.getLength(); i++) {
			Node currentParam = parameterChildren.item(i);
			myParameters.put(currentParam.getNodeName(),
					currentParam.getTextContent());
		}
	}

	/**
	 * Method that cleans white space and stray characters out of XML file
	 * Taken from:
	 * http://stackoverflow.com/questions/978810/how-to-strip-whitespace-only
	 * -text-nodes-from-a-dom-before-serialization/16285664#16285664
	 * @param node
	 */
	public static void clean(Node node) {
		NodeList childNodes = node.getChildNodes();
		for (int n = childNodes.getLength() - 1; n >= 0; n--) {
			Node child = childNodes.item(n);
			short nodeType = child.getNodeType();
			if (nodeType == Node.ELEMENT_NODE) {
				clean(child);
			} else if (nodeType == Node.TEXT_NODE) {
				String trimmedNodeVal = child.getNodeValue().trim();
				if (trimmedNodeVal.length() == 0) {
					node.removeChild(child);
				} else {
					child.setNodeValue(trimmedNodeVal);
				}
			} else if (nodeType == Node.COMMENT_NODE) {
				node.removeChild(child);
			}
		}
	}

	/**
	 * Read in the Grid row by row Create the new "squares" by placing ints in
	 * the grid array
	 */
	private void readGrid(NodeList gridChildren) {
		for (int i = 0; i < gridChildren.getLength(); i++) {
			Node currentRow = gridChildren.item(i);
			String rowString = currentRow.getTextContent();
			String[] splitRow = rowString.split(" ");
			for (int j = 0; j < splitRow.length; j++) {
				Integer newSquare = Integer.parseInt(splitRow[j]);
				myGrid[i][j] = newSquare;
			}
		}
	}

	/**
	 * Function to do initial set-up Returns a node list of all elements of one
	 * type Ex. returns list of parameters or list of grid rows
	 */
	private NodeList initializeNodeList(Document doc, String element) {
		NodeList elementList = doc.getDocumentElement().getElementsByTagName(
				element);
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
		return doc;
	}

	/**
	 * Returns myGrid Called by controller
	 */
	public Integer[][] getGrid() {
		return myGrid;
	}

	/**
	 * Returns HashMap of parameters Called by controller
	 */
	public HashMap<String, String> getParameters() {
		return myParameters;
	}
}
