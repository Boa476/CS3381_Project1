package project1;

import java.util.ArrayList;

public class Patient {
	private String id = new String();
	private String pred = new String();
	private String result = new String();
	private ArrayList<Double> genes = new ArrayList<Double>();
	
	public Patient() {
		id = "00";
		pred = "unknown";
		result = "N/A";
	}
	
	public Patient(String i, String p, String r, ArrayList<Double> g){
		id = i;
		pred = p;
		result = r;
		genes = g;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPred() {
		return pred;
	}

	public void setPred(String pred) {
		this.pred = pred;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ArrayList<Double> getGenes() {
		return genes;
	}

	public void setGenes(ArrayList<Double> genes) {
		this.genes = genes;
	}
	
	public String toString() {
		return "id: " + id + " prediction: " + pred + " result: " + result;
	}





}
