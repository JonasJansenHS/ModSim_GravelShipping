/**
 * 
 */
package edu.hsosna.gravelshipping;

import java.util.ArrayList;

/**
 * @author christopher
 *
 */
public abstract class Simulation {

	/**
	 * @param args
	 */
	public static int timeStep = 0;

	protected ArrayList<SimObject> simObjects = new ArrayList<SimObject>();

	protected abstract void initialize();

	protected void simulate() {
		boolean oneObjectSimulatedSuccesfully;
		boolean timeShift;
		do {
			System.out.println(EventQueue.getInstance());
			
			timeShift = false;

			do {
				oneObjectSimulatedSuccesfully = false;

				for (SimObject simObject : simObjects) {
					if (simObject.simulate(timeStep)) {
						oneObjectSimulatedSuccesfully = true;
						System.out.println(timeStep + " " + simObject);
					}
				}
			} while (oneObjectSimulatedSuccesfully);
			
			Event nextEvent = EventQueue.getInstance().peakFutureEvents(timeStep);
			if (nextEvent != null) {
				timeStep = nextEvent.getTimestep();
				timeShift = true;
			}
		} while (timeShift);
	}
}
