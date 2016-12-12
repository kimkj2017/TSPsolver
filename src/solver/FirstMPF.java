package solver;

import java.util.*;

import parser.Graph;

public class FirstMPF {

	public static Path runIndividual(Graph g, int V) {
		HashSet<Integer> explored = new HashSet<Integer>();
		Path p = new Path();
		p.path.addLast(V);
		while (explored.size() < g.graph.size() - 1) {
			HashMap<Integer, Double> hm = g.graph.get(V);
			explored.add(V);
			double minCost = Double.MAX_VALUE;
			int nextV = -1;
			for (Integer dest : hm.keySet()) {
				if (!explored.contains(dest)) {
					if (hm.get(dest) < minCost) {
						minCost = hm.get(dest);
						nextV = dest;
					}
				}
			}
			V = nextV;
			p.path.addLast(V);
			p.cost += minCost;
		}
		int x_a = p.path.getLast();
		int x_b = p.path.getFirst();
		double lastDist = g.graph.get(x_a).get(x_b);
		p.cost += lastDist;
		if (!p.check()) {
			throw new IllegalStateException();
		}
		return p;
	}

	public static Path run(Graph g) {
		Path globalP = new Path();
		globalP.cost = Double.MAX_VALUE;
		ArrayList<Double> costs = new ArrayList<Double>();
		for (int i = 0; i < g.graph.size(); i++) {
			HashSet<Integer> explored = new HashSet<Integer>();
			int V = i;
			Path p = new Path();
			p.path.addLast(V);
			while (explored.size() < g.graph.size() - 1) {
				HashMap<Integer, Double> hm = g.graph.get(V);
				explored.add(V);
				double minCost = Double.MAX_VALUE;
				int nextV = -1;
				for (Integer dest : hm.keySet()) {
					if (!explored.contains(dest)) {
						if (hm.get(dest) < minCost) {
							minCost = hm.get(dest);
							nextV = dest;
						}
					}
				}
				V = nextV;
				p.path.addLast(V);
				p.cost += minCost;
			}
			int x_a = p.path.getLast();
			int x_b = p.path.getFirst();
			double lastDist = g.graph.get(x_a).get(x_b);
			p.cost += lastDist;
			if (globalP.cost > p.cost) {
				globalP = p;
			}
		}
		if (!globalP.check()) {
			throw new IllegalStateException();
		}

		System.out.println(globalP);// + "\n" + "Check Cost: " + (costs.get(0)));
		return globalP;
	}

}
