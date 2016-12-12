import java.util.LinkedList;

import parser.Graph;
import parser.OPTParse;
import parser.XMLParsing;
import solver.DivideAndConquer;
import solver.FirstMPF;
import solver.OptSolution;
import solver.Path;
import solver.SecondMPF;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String question = "att48";
			LinkedList<Integer> opt = OPTParse.getOPTList("opt/" + question + ".opt.tour");
			Graph g = XMLParsing.parse("datasets/" + question + ".xml");
			System.out.println(g.getAttribString());
			Path optSoln = OptSolution.run(g, opt);
			double optCost = optSoln.cost;
			System.out.println("Optimal");
			System.out.println("Error: " + Math.abs(optCost - optSoln.cost));
			System.out.println("----------------------------");
			System.out.println("FirstMPF");
			long start1 = System.currentTimeMillis();
			Path firstMPF = FirstMPF.run(g);
			System.out.println("Elapsed time: " + (System.currentTimeMillis() - start1));
			System.out.println("Error: " + Math.abs(optCost - firstMPF.cost));
			System.out.println("----------------------------");
			System.out.println("FirstMPF Indiv");
			long start2 = System.currentTimeMillis();
			Path pIndiv = FirstMPF.runIndividual(g, (opt.getFirst() - 1));
			System.out.println("Elapsed time: " + (System.currentTimeMillis() - start2));
			System.out.println(pIndiv);
			System.out.println("Error: " + Math.abs(optCost - pIndiv.cost));
			System.out.println("----------------------------");
			System.out.println("SecondMPF");
			long start3 = System.currentTimeMillis();
			Path secondMPF = SecondMPF.run(g);
			System.out.println("Elapsed time: " + (System.currentTimeMillis() - start3));
			System.out.println("Error: " + Math.abs(optCost - secondMPF.cost));
			System.out.println("----------------------------");
//			DivideAndConquer.run(g);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
