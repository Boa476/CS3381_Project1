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
			sc = new Scanner(new File("data.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (sc.hasNextLine()) {
			String[] Tokens = sc.nextLine().split(",");	
			Patient temp = new Patient()
		}
	}
}
