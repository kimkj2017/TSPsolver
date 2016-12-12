package parser;

//import org.w3c.dom.*;
//import javax.xml.parsers.*;
//import java.io.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLParsing {

	public static Graph parse(String filename) throws IOException,
			ParserConfigurationException, SAXException {
		File inputFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		HashMap<String, String> attribs = new HashMap<String, String>();
		attribs.put("name", doc.getElementsByTagName("name").item(0)
				.getTextContent());
		attribs.put("source", doc.getElementsByTagName("source").item(0)
				.getTextContent());
		attribs.put("description", doc.getElementsByTagName("description")
				.item(0).getTextContent());
		attribs.put("doublePrecision",
				doc.getElementsByTagName("doublePrecision").item(0)
						.getTextContent());
		attribs.put("ignoredDigits", doc.getElementsByTagName("ignoredDigits")
				.item(0).getTextContent());
		NodeList vertexes = doc.getElementsByTagName("graph").item(0).getChildNodes();
		Graph g = new Graph(attribs);
		for (int i = 0; i < vertexes.getLength(); i++) {
			NodeList edges = vertexes.item(i).getChildNodes();
			HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
			for (int j = 0; j < edges.getLength(); j++) {
				String name = edges.item(j).getTextContent().trim();
				int destVtx = -1;
				if (!name.equals("")) {
					destVtx = Integer.parseInt(name);
					NamedNodeMap attrib = edges.item(j).getAttributes();
					double cost = Double.parseDouble(attrib.getNamedItem("cost").getTextContent());
					dist.put(destVtx, cost);
				}			
			}
			if (!dist.isEmpty()) {
				g.graph.add(dist);
			}
		}
		return g;
	}

	public static void main(String[] args) {
		try {
			Graph g = parse("datasets/bayg29.xml");
			System.out.println(g);
			// File inputFile = new File("datasets/att48.xml");
			// DocumentBuilderFactory dbFactory = DocumentBuilderFactory
			// .newInstance();
			// DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// Document doc = dBuilder.parse(inputFile);
			// doc.getDocumentElement().normalize();
			// System.out.println("Root element :"
			// + doc.getDocumentElement().getNodeName());
			// NodeList nList = doc.getElementsByTagName("graph");
			// System.out.println("----------------------------");
			// System.out.println("Length: " + nList.getLength());
			// for (int temp = 0; temp < nList.getLength(); temp++) {
			// Node nNode = nList.item(temp);
			// System.out.println("\nCurrent Element :" + nNode.getNodeName());
			// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			// Element eElement = (Element) nNode;
			// System.out.println("First Name : "
			// + eElement.getElementsByTagName("vertex").item(0)
			// .getTextContent());
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}