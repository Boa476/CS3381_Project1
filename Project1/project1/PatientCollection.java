package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientCollection {

	public PatientCollection() {
		ArrayList<Patient> list = new ArrayList<Patient>();
		readfile();
	}

	private void readfile() {
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File("./project1/data.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (sc.hasNextLine()) {
			String[] tokens = sc.nextLine().split(",");	
			ArrayList<Double> proteins = new ArrayList<Double>();
			for (int i = 3; i < tokens.length; i++) {
				proteins.add(Double.parseDouble(tokens[i]));
			}
			Patient temp = new Patient(tokens[2], tokens[1], tokens[0], proteins);
		}
	}
}
