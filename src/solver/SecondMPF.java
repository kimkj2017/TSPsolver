package solver;

import java.util.HashMap;
import java.util.Set;
//import java.awt.Point;

import parser.Graph;

public class SecondMPF {
	
	public Set<Integer> explored;

	public static Path run(Graph g) {
		int x_s = 0;
		int x_d = 0;
		double dist = Double.MAX_VALUE;
		for (int i = 0; i < g.graph.size(); i++) {
			HashMap<Integer, Double> edges = g.graph.get(i);
			for (Integer k : edges.keySet()) {
				if (dist > edges.get(k)) {
					dist = edges.get(k);
					x_s = i;
					x_d = k;
				}
			}
		}
//		System.out.println("(" + x_s + ", " + x_d + ")=>" + dist);
		Path p1 = FirstMPF.runIndividual(g, x_s);
		Path p2 = FirstMPF.runIndividual(g, x_d);
		System.out.println((p1.cost <= p2.cost) ? p1 : p2);
		return (p1.cost <= p2.cost) ? p1 : p2;
	}

}