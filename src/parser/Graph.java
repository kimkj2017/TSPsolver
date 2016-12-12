package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	
	public ArrayList<HashMap<Integer, Double>> graph;
	public HashMap<String, String> attribs;
	
	public Graph(HashMap<String, String> attribs) {
		this.attribs = new HashMap<String, String>();
		for (String key : attribs.keySet()) {
			this.attribs.put(key, attribs.get(key));
		}
		this.graph = new ArrayList<HashMap<Integer, Double>>();
	}
		
	public String getAttribString() {
		String ret = "";
		for (String key : this.attribs.keySet()) {
			ret += key + ": " + this.attribs.get(key) + "\n";
		}
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = getAttribString();
		ret += "---------------------\n";
		int vertex = 0;
		for (HashMap<Integer, Double> hm : this.graph) {
			ret += "Vertex " + vertex + "\n";
			for (Integer key : hm.keySet()) {
				ret += key + ": " + hm.get(key) + "\n";
			}
			vertex++;
		}
		return ret;
	}
}
