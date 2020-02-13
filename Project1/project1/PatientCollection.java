package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PatientCollection {
	private ArrayList<Patient> list;
	Iterator<Patient> i;
	Scanner sc = null;

	public PatientCollection() {
		list = new ArrayList<Patient>();
		readfile();
	}

	private void readfile() {
		
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
			list.add(new Patient(tokens[2], tokens[1], tokens[0], proteins));
		}
	}
	
	public Patient getPatient(String id) {
		i = list.iterator();
		while(i.hasNext()) {
			int temp = list.indexOf(i.next());
			if(list.get(temp).getId() == id) {
				return list.get(temp);
			}
		}
		
		System.out.println("Patient ID not found.");
		return null;
	}
	
	public Patient removePatient(String id) {
		while(i.hasNext()) {
			int temp = list.indexOf(i.next());
			if(list.get(temp).getId() == id) {
				Patient p1 = list.get(temp); 
				list.remove(temp);
				return p1;
			}
		}
		
		System.out.println("Patient not found in system.");
		return null;
	}
	
	 public void setResultForPatient (String id, String result) {
		 while(i.hasNext()) {
				int temp = list.indexOf(i.next());
				if(list.get(temp).getId() == id) { 
					list.get(temp).setResult(result);
				}
		 }
	 }
	 
	 public ArrayList<String> getIds(){
		 i = list.iterator();
		 ArrayList<String> ids = new ArrayList<String>();
		 while(i.hasNext()) {
			 ids.add(i.next().getId());
		 }
		 
		 return ids;
	 }
	 
	 public String addPatientsFromFile (String fileName) {
		 String str = "All patients successfully added to file";
		 int ctr = 0;
		 ArrayList<Integer> err = new ArrayList<Integer>();
		 
		 try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split(",");	
				ArrayList<Double> proteins = new ArrayList<Double>();
				for (int i = 1; i < tokens.length; i++) {
					proteins.add(Double.parseDouble(tokens[i]));
				}
				Patient p = new Patient(tokens[0], proteins);
				ctr++;
				
				if (list.contains(p) || proteins.size() != 4776) {
					err.add(ctr);
					str = "Error adding patient on line: " + err.toString() + "\n Please validate the formatting of the data file \n i.e. id,protein1, ... protein4776";
				}
				else {
					list.add(p);
				}
			}
		 
		return str;
	 }
	 
	 public boolean contains(Patient p) {
		 i = list.iterator();
		 while(i.hasNext()) {
			if (p.equals(i.next())) {
				return true;
			}
		 }
		return false;
	 }
	 
	 public String toString() {
		 String str = "";
		 i = list.iterator();
		 if(list.isEmpty()) {
			 return "Empty List. Please add patients to the list";
		 }

		 while (i.hasNext()) {
			 Patient temp = i.next();
			 str = str + temp + " " + temp.getGenes().get(3696) + " " + temp.getGenes().get(3257) + "\n";
		 }
		 return str;
	 }
}
