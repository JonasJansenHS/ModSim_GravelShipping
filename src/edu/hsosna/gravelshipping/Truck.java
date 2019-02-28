package edu.hsosna.gravelshipping;

public class Truck implements SimObject {
	private double loadedWeight = 0.0d;
	private String name;
	private static int id = 0;

	
	
	@Override
	public String toString() {
		return "Truck [loadedWeight=" + loadedWeight + ", name=" + name + "]";
	}
	
	public Truck() {
		super();
		
		name = "T" + id++;
		
		EventQueue.getInstance().add(new Event(0, EventType.loadingReady, this, null, LoadingStation.class));
		LoadingStation.truckQueue.add(this);
	}

	public double getLoadedWeight() {
		return loadedWeight;
	}

	public boolean setLoadedWeight(double loadedWeight) {
		if(loadedWeight < 0)
			return false;
		
		this.loadedWeight = loadedWeight;
		return true;
	}


	@Override
	public boolean simulate(int timeStep) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
