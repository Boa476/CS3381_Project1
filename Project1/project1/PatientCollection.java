//Taylor Boling, CSCI 3381 OO with Java
package project1;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private void readfile() { //sourced from CSCI 3381 BlackBoard
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader("./project1/data.csv");
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
					String tokens[] =line.split(",");
					ArrayList<Double> proteins = new ArrayList<Double>();
					for (int i = 3; i < tokens.length; i++) {
						proteins.add(Double.parseDouble(tokens[i]));
					}
					list.add(new Patient(tokens[2], tokens[1], tokens[0], proteins));
				}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
//			try {
//				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
//				String line = null;
//				while ((line = lineReader.readLine())!=null) {
//					String name = lineReader.readLine();
//					String id = lineReader.readLine();
//					if (line.equals("student")) {
//						String gpaString = lineReader.readLine();
//						addStudent(new Student(name,id,Double.parseDouble(gpaString)));
//					}
//					else if (line.equals("instructor")) {
//						String email = lineReader.readLine();
//						addInstructor(new Instructor(name,id,email));
//					}
//					else {
//						System.err.println("error: unnknown person type");
//					}				}
//			} catch (Exception e2) {
//				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
//			} finally {
//				if (lineReader != null)
//					try {
//						lineReader.close();
//					} catch (IOException e2) {
//						System.err.println("could not close BufferedReader");
//					}
//			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}
	
	public void writeFile() //writes arraylist of patients to a file of the given directory (Sourced from CSCI 3381 BlackBoard)
	{
		i = list.iterator();
		


		// this method writes all of the data in the persons array to a file
		try
		{

			FileWriter fw = new FileWriter("./project1.data.csv");
			BufferedWriter myOutfile = new BufferedWriter(fw);
			 while (i.hasNext()) {
				 String str = new String();
		    	  Patient temp = i.next();
		    	  str = temp.getId() + "," + temp.getPred() + "," + temp.getResult();
		    	  for(int k = 0; k < temp.getGenes().size(); k++) {
		    		  str = str + "," + temp.getGenes().get(k);
		    	  }
		    	  myOutfile.write(str + "\n");
		      }

			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to ./project1.data.csv" );
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
