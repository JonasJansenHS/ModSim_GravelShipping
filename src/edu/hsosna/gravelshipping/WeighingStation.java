package edu.hsosna.gravelshipping;

import java.util.List;

public class WeighingStation extends Station {

	private String name;
	private static int w_id = 0;

	private static RandomNumberGenerator2<Integer> backToLoadingStation = new RandomNumberGenerator2<Integer>();
	private static RandomNumberGenerator2<Integer> loadShipTime = new RandomNumberGenerator2<Integer>();

	private Truck truck;
	
	private int timeToReload;

	public WeighingStation() {
		super();
		name = "W" + w_id++;

	}

	public static void initialize() {
		backToLoadingStation.add(new KeyValuePair<Integer>(0.4d, 5));
		backToLoadingStation.add(new KeyValuePair<Integer>(0.8d, 7));
		backToLoadingStation.add(new KeyValuePair<Integer>(1.0d, 10));

		loadShipTime.add(new KeyValuePair<Integer>(0.7d, 15));
		loadShipTime.add(new KeyValuePair<Integer>(1.0d, 17));
	}

	@Override
	public boolean simulate(int timeStep) {

		List<Event> list = EventQueue.getInstance().peak(timeStep, EventType.weighingReady, null, null,
				WeighingStation.class);
		if (list != null) 
		{
			Event e = list.get(0);
			truck = (Truck) e.getAttachedObject();
			EventQueue.getInstance().remove(e);
			if (truck.getLoadedWeight() > 40) {
				System.out.println("Weighingstation: overweight");
				GravelShipping.toShip += truck.getLoadedWeight(); // Truck lädt Gravel wieder auf den großen Haufen
				timeToReload = backToLoadingStation.nextValue();
				EventQueue.getInstance().add(new Event(timeStep + timeToReload ,
						EventType.loadingReady, truck, this, LoadingStation.class));
			} else {
				System.out.println("Weighingstation: correct weight");
				GravelShipping.shipped += truck.getLoadedWeight();
				//GravelShipping.toShip -= truck.getLoadedWeight(); an richtiger Stelle in LoadingStation platziert
				
				System.out.println("Gravel shipped: " + GravelShipping.shipped);
				
				timeToReload = backToLoadingStation.nextValue() + loadShipTime.nextValue();
				
				EventQueue.getInstance()
						.add(new Event(timeStep + timeToReload,
								EventType.loadingReady, truck, this, LoadingStation.class));
			}
			truck.setLoadedWeight(0);
			LoadingStation.truckQueue.add(truck);
			return true;
		}
		return false;
		
	}
	
	@Override
	public String toString() {
		return "WeighingStation [time to reolad=" + timeToReload + "]";
	}
}
