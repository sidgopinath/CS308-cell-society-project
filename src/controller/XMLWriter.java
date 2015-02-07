package controller;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLWriter {

	//used for reference: http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	
	public XMLWriter(HashMap<String, String> paramMap, Integer[][] gridArray){
		
		try{
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
			
			//create the simulation root element
			Document xmlDoc = docBuilder.newDocument();
			Element rootElement = xmlDoc.createElement("simulation");
			xmlDoc.appendChild(rootElement);
			
			//create parameter child of simulation
			Element parameter = xmlDoc.createElement("parameter");
			rootElement.appendChild(parameter);
			
			//iterate through param map and make new parameters in xml doc
			for(String param: paramMap.keySet()){
				Element newParam = xmlDoc.createElement(param);
				newParam.appendChild(xmlDoc.createTextNode(paramMap.get(param)));
				parameter.appendChild(newParam);
				System.out.println(param + " " + paramMap.get(param));
			}
			
			//make grid element, which all rows will be children of
			Element grid = xmlDoc.createElement("grid");
			rootElement.appendChild(grid);
			
			//make row children of grid
			for(int i=0; i<gridArray.length; i++){
				String currentRow = "r" + (i+1);
				Element newRow = xmlDoc.createElement(currentRow);
				String rowString = "";
				for(int j: gridArray[i]){
					rowString = rowString + j + " ";
				}
				System.out.println(rowString);
				newRow.appendChild(xmlDoc.createTextNode(rowString));
				grid.appendChild(newRow);
			}
			
			//make it an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(xmlDoc);
			StreamResult result = new StreamResult(new File("userTestFile.xml"));
			transformer.transform(source, result);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
}
