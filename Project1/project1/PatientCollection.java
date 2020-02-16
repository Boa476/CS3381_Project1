package project1;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	
	public void writeFile(String fileName)
	{
		i = list.iterator();
		
		//writeFile file creation and writer sourced from https://www.w3schools.com/java/java_files_create.asp
		
		 try {
		      FileWriter myWriter = new FileWriter(fileName);
		      String str = new String();
		      while (i.hasNext()) {
		    	  Patient temp = i.next();
		    	  str = str + temp.getId() + "," + temp.getPred() + "," + temp.getResult();
		    	  for(int k = 0; k < temp.getGenes().size(); k++) {
		    		  str = str + "," + temp.getGenes().get(k);
		    	  }
		    	  str = str + "\n";
		    	  System.out.println(str);
		      }
		      myWriter.write(str);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
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
