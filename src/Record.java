import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import parser.Graph;
import parser.OPTParse;
import parser.XMLParsing;
import solver.FirstMPF;
import solver.OptSolution;
import solver.Path;
import solver.SecondMPF;

public class Record {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(new File("tsps.txt"));
		PrintWriter pw = new PrintWriter(new File("record.csv"));
		pw.println("dataset,method,cost,elapsedtime,error");
		while (s.hasNext()) {
			try {
				String question = s.nextLine();
				LinkedList<Integer> opt = OPTParse.getOPTList("opt/" + question
						+ ".opt.tour");
				Graph g = XMLParsing.parse("datasets/" + question + ".xml");
				System.out.println(g.getAttribString());
				Path optSoln = OptSolution.run(g, opt);
				double optCost = optSoln.cost;
				System.out.println("Optimal");
				System.out
						.println("Error: " + Math.abs(optCost - optSoln.cost));
				pw.println(question + ",optimal," + optCost + "," + 0 + "," + 0);
				System.out.println("----------------------------");
				System.out.println("FirstMPF");
				long start1 = System.currentTimeMillis();
				Path firstMPF = FirstMPF.run(g);
				long end1 = System.currentTimeMillis();
				System.out.println("Elapsed time: "
						+ (end1 - start1));
				System.out.println("Error: "
						+ Math.abs(optCost - firstMPF.cost));
				pw.println(question + ",firstMPF," + firstMPF.cost + "," + (end1 - start1) + "," + Math.abs(optCost - firstMPF.cost));
				System.out.println("----------------------------");
				System.out.println("FirstMPF Indiv");
				long start2 = System.currentTimeMillis();
				Path pIndiv = FirstMPF.runIndividual(g, (opt.getFirst() - 1));
				long end2 = System.currentTimeMillis();
				System.out.println("Elapsed time: "
						+ (end2 - start2));
				System.out.println(pIndiv);
				System.out.println("Error: " + Math.abs(optCost - pIndiv.cost));
				pw.println(question + ",firstMPFindiv," + pIndiv.cost + "," + (end2 - start2) + "," + Math.abs(optCost - pIndiv.cost));
				System.out.println("----------------------------");
				System.out.println("SecondMPF");
				long start3 = System.currentTimeMillis();
				Path secondMPF = SecondMPF.run(g);
				long end3=System.currentTimeMillis();
				System.out.println("Elapsed time: "
						+ (end3 - start3));
				System.out.println("Error: "
						+ Math.abs(optCost - secondMPF.cost));
				pw.println(question + ",secondMPF," + secondMPF.cost + "," + (end3 - start3) + "," + Math.abs(optCost - secondMPF.cost));
				System.out.println("----------------------------");
				// DivideAndConquer.run(g);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		s.close();
		pw.close();
	}

}
