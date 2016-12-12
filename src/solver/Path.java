package solver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Path {

	public LinkedList<Integer> path;
	public double cost;
	
	public Path() {
		this.path = new LinkedList<Integer>();
		this.cost = 0;
	}
	
	public boolean check() {
		HashSet<Integer> explored = new HashSet<Integer>();
		Iterator<Integer> iter = path.iterator();
		while (iter.hasNext()) {
			int node = iter.next();
			if (explored.contains(node)) {
				return false;
			} else {
				explored.add(node);
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		String ret = "Path: ";
		Iterator<Integer> iter = path.iterator();
		while (iter.hasNext()) {
			ret += iter.next() + " -> ";
		}
		return ret + "\nCost: " + cost;
	}
	
}
