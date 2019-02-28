  package edu.hsosna.gravelshipping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomNumberGenerator2<T> extends ArrayList<KeyValuePair<T>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Random random = new Random(12345);

	@Override
	public boolean add(KeyValuePair<T> keyValuePair) {
		super.add(keyValuePair);

		Collections.sort(this);
		return true;
	}

	public T nextValue() {
		double rand = random.nextDouble();

		int i = 0;

		while (rand > this.get(i).probability)
			i++;

		return this.get(i).value;
	}

	public T nextValue(double rand) {

		int i = 0;

		while (rand > this.get(i).probability)
			i++;

		return this.get(i).value;
	}

}
