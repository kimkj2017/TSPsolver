package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class OPTParse {
	
	public static LinkedList<Integer> getOPTList(String filename) {
		try {
			LinkedList<Integer> opt = new LinkedList<Integer>();
			Scanner f = new Scanner(new File(filename));
			boolean reading = false;
			while (f.hasNext()) {
				String line = f.nextLine();
				if (line.equalsIgnoreCase("-1")) {
					break;
				}
				if (reading) {
					opt.addLast(Integer.parseInt(line));
				}
				if (line.equals("TOUR_SECTION")) {
					reading = true;
				}
			}
			f.close();
			return opt;
		} catch (FileNotFoundException e) {
			System.err.println("Opt sol file not exist");
			return null;
		}
	}

}
