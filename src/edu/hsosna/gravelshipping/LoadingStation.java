/**
 * 
 */
package edu.hsosna.gravelshipping;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author christopher
 *
 */
public class LoadingStation extends Station {
	public static int l_id = 0;
	public static String name;

	private static RandomNumberGenerator2<Integer> loadingTimes = new RandomNumberGenerator2<Integer>();
	private static RandomNumberGenerator2<Double> loadingWeight = new RandomNumberGenerator2<Double>();
	private static RandomNumberGenerator2<Integer> driveToWeighingStation = new RandomNumberGenerator2<Integer>();
	
	public static Queue<Truck> truckQueue = new LinkedList<Truck>();

	private Truck truck;

	public LoadingStation() {
		super();
		name = "L" + ++l_id;

	}

	public static void initialize() {
		loadingTimes.add(new KeyValuePair<Integer>(0.3d, 5));
		loadingTimes.add(new KeyValuePair<Integer>(0.8d, 10));
		loadingTimes.add(new KeyValuePair<Integer>(1.0d, 15));

		loadingWeight.add(new KeyValuePair<Double>(0.1d, 36.0d));
		loadingWeight.add(new KeyValuePair<Double>(0.9d, 40.0d));
		loadingWeight.add(new KeyValuePair<Double>(1.0d, 44.0d));
		
		driveToWeighingStation.add(new KeyValuePair<Integer>(0.2d, 5));
		driveToWeighingStation.add(new KeyValuePair<Integer>(0.6d, 10));
		driveToWeighingStation.add(new KeyValuePair<Integer>(1.0d, 15));

	}

	@Override
	public boolean simulate(int timeStep) {

		if (truck == null) {
			List<Event> list = EventQueue.getInstance().peak(timeStep, EventType.loadingReady, null, null,
					LoadingStation.class);

			if (list != null && GravelShipping.toShip > 0) {

				truck = truckQueue.poll();

				for (Event e : list)
					if (e.getAttachedObject() == truck) {
						EventQueue.getInstance().remove(e);
						
						double x = loadingWeight.nextValue();
						
						if(x > GravelShipping.toShip) {
							x = GravelShipping.toShip;
						}
						GravelShipping.toShip -= x; //Die Ladung wird vom Lager entfernt
						truck.setLoadedWeight(x); // Der Truck erhält die Ladung
						EventQueue.getInstance().add(new Event(timeStep + loadingTimes.nextValue(),
								EventType.loadingDone, truck, this, LoadingStation.class));
					}
				return true;
			}
		} else {
			List<Event> list = EventQueue.getInstance().peak(timeStep, EventType.loadingDone, truck, null,
					LoadingStation.class);
			
			if(list != null) {
				
				EventQueue.getInstance().remove(list.get(0));
				
				Event ev = new Event(timeStep + driveToWeighingStation.nextValue(), EventType.weighingReady,
						truck, null, WeighingStation.class);
				
				EventQueue.getInstance().add(ev);
				truck = null;
				return true;
			}
			
		}

		return false;
	}

	@Override
	public String toString() {
		return "LoadingStation [truck=" + truck + "]";
	}

	
}
