package navigation;

import java.util.Random;

public class Flightpath {

	public static void main(String[] args) {
		
		Random random = new Random();
		
		for (int i = 0; i < 500; i++) {
			System.out.println("Next Gaussian value is = "
	                + random.nextGaussian()); 
			
		}

	}
}
