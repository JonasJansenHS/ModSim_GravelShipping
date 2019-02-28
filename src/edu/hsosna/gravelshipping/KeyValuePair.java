package edu.hsosna.gravelshipping;

public class KeyValuePair<T> implements Comparable<KeyValuePair>{

	public Double probability;
	public T value;
	
	public KeyValuePair(Double probability, T value) {
		this.probability = probability;
		this.value = value;
	}
	
	@Override
	public int compareTo(KeyValuePair o) {		
		return probability.compareTo(o.probability);
	}
	
	
	
}
