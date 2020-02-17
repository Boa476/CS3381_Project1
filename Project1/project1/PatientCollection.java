package project1;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PatientCollection { //Creates a "roster" of patients
	private ArrayList<Patient> list;
	Iterator<Patient> i;
	Scanner sc = null;

	public PatientCollection() {
		list = new ArrayList<Patient>();
		readfile();
	}

	// Reads from pre-existing data file and adds patients to ArrayList
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
	
	public void writeFile(String fileName) //writes arraylist of patients to a file of the given directory
	{
		i = list.iterator();
		
		//writeFile file creation and writer sourced from https://www.w3schools.com/java/java_files_create.asp
		
		 try {
		      FileWriter myWriter = new FileWriter(fileName);
		      String str = new String("");
		      while (i.hasNext()) {
		    	  Patient temp = i.next();
		    	  str = str + temp.getId() + "," + temp.getPred() + "," + temp.getResult();
		    	  for(int k = 0; k < temp.getGenes().size(); k++) {
		    		  str = str + "," + temp.getGenes().get(k);
		    	  }
		    	  str = str + "\n";
		      }
		      myWriter.write(str);
		      System.out.println("File written successfully.");
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public Patient getPatient(String id) { // returns a patient with the given id
		i = list.iterator();
		while(i.hasNext()) {
			int temp = list.indexOf(i.next());
			if(list.get(temp).getId().equals(id)) {
				return list.get(temp);
			}
		}
		
		System.out.println("Patient ID not found.");
		return null;
	}
	
	public Patient removePatient(String id) { // removes a patient from the array with the given id
		while(i.hasNext()) {
			int temp = list.indexOf(i.next());
			if(list.get(temp).getId().equals(id)) {
				Patient p1 = list.get(temp); 
				list.remove(temp);
				return p1;
			}
		}
		
		System.out.println("Patient not found in system.");
		return null;
	}
	
	 public void setResultForPatient (String id, String result) {  // Set the result field for the patient with given id.
		 while(i.hasNext()) {
				int temp = list.indexOf(i.next());
				if(list.get(temp).getId().equals(id)) { 
					list.get(temp).setResult(result);
				}
		 }
	 }
	 
	 public ArrayList<String> getIds(){ // Return an ArrayList with all of the collection's patient ids
		 i = list.iterator();
		 ArrayList<String> ids = new ArrayList<String>();
		 while(i.hasNext()) {
			 ids.add(i.next().getId());
		 }
		 
		 return ids;
	 }
	 
	 public String addPatientsFromFile (String fileName) { //adds patients to the arraylist and makes a prediction based on proteins
		 String str = "All patients successfully added to file";
		 Predictor pred = new Predictor();
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
				
//				System.out.println(proteins.size()); // validation for patient meeting criteria for filing
//				System.out.println(contains(p)); // validation for patient meeting criteria for filing
				
				if (contains(p) || proteins.size() != 4776) {
					err.add(ctr);
					str = "Error adding patient on line: " + err.toString() + "\nPlease validate the formatting of the data file\ni.e. id,protein1, ... protein4776";
				}
				else {
					String x = pred.Predict(p.getGenes().get(3696).doubleValue(), p.getGenes().get(3257).doubleValue());
					p.setPred(x);
					list.add(p);
				}
			}
		 
		 return str;
	 }
	 
	 public boolean contains(Patient p) { // check to see if patient p is within the list
		 i = list.iterator();
		 while(i.hasNext()) {
			if (p.equals(i.next())) {
				return true;
			}
		 }
		return false;
	 }
	 
	 public String toString() { // Return a String representation of the collection.
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
