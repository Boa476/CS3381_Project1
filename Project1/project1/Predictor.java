//Taylor Boling, CSCI 3381 OO with Java
package project1;

public class Predictor {

	public static String Predict(double p1, double p2) {
		if (p1 <= 20.903959) {
			return "predDP";
		}
		else {
			if (p2<= 22.058599) {
				return "predCR";
			}
			else {
				return "predDP";
			}
		}
	}
}
