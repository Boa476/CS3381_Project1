package project1;

public class Tester {


	public static void main(String[] args) {
		
		//Patient Tester
		Patient p1 = new Patient();
		System.out.println(p1);
		
		p1.setId("01");
		p1.setPred("CR");
		p1.setResult("CR");
		System.out.println(p1);
		//
		
		//PatientCollection Tester
		PatientCollection s1 = new PatientCollection();
		boolean t;
		Patient t1 = new Patient();
		t1.setId("01");
		t = s1.contains(t1);
		
		System.out.println(t);
		
		System.out.println(s1);
		
		s1.addPatientsFromFile("./project1/newdata.csv");
		String msg = s1.addPatientsFromFile("./project1/newdata.csv");
		System.out.println(msg);
		System.out.println(s1);
	}

}
