package solver;

import java.util.Iterator;
import java.util.LinkedList;

import parser.Graph;

public class OptSolution {
	
	public static Path run(Graph g, LinkedList<Integer> opt) {
		Path p = new Path();
//		System.out.println(opt);
		Iterator<Integer> iter = opt.iterator();
		int x_s = iter.next();
		x_s--;
		p.path.addLast(x_s);
		while (iter.hasNext()) {
			int x_d = iter.next();
			x_d--;
//			System.out.println(x_s + "=>" + x_d);
//			System.out.println(g.graph.get(x_s).keySet());
			double dist = g.graph.get(x_s).get(x_d);
			p.path.addLast(x_d);
			p.cost += dist;
			x_s = x_d;
		}
		int x_last = p.path.getLast();
		int x_first = p.path.getFirst();
		p.cost += g.graph.get(x_last).get(x_first);
		System.out.println(p);
		return p;
	}

}
