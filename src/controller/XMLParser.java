package controller;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLParser {

	private File myXMLFile;
	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private Array<Array<Square>> myGrid = new Array(); //needs a Square class for this to work
	
	public XMLParser(File XMLFile){
		//initialize XML Parser using XML File
		myXMLFile = XMLFile;
	}
	
	//other functions here to scan in file and parse through lines, storing them in proper data structures
	
	//function to return ArrayList that signifies the Grid
	public Array<Array<Square>> getGrid(){
		return myGrid;
	}
	
	//function to return HashMap of parameters
	public HashMap<String, String> getParameters(){
		return myParameters;
	}
	
	
	
	
	
}
