package edwin.alkins.swingTest.littelGame2.core.util;

import java.util.Random;

public final class Tools {

	public synchronized static Double randome(Double rangeMin, Double rangeMax) {
		Random r = new Random();
		return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	}
	
	public synchronized static Integer randome(int rangeMin, int rangeMax) {
		Random r = new Random();
		return rangeMin + (rangeMax - rangeMin) * r.nextInt();
	}
}
