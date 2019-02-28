package edu.hsosna.gravelshipping;

public class RandomNumberGenerator {

	public static int nextNumber(LoadingStation loadingStation) {
		double x = Math.round(Math.random());

		if (x > 0.8) {
			return 15;
		} else if (x > 0.3) {
			return 10;
		} else {
			return 5;
		}

	}
 
	public static int nextNumber(WeighingStation weighingStation) {
		double m = Math.round(Math.random());

		if (m > 0.7) {
			return 16;
		} else {
			return 12;
		}

	}

	public static int nextNumber(Truck truck) {
		double k = Math.round(Math.random());

		if (k > 0.9) {
			return 100;
		} else if (k > 0.7) {
			return 80;
		} else if (k > 0.4) {
			return 60;
		} else {
			return 40;
		}
	}

}
