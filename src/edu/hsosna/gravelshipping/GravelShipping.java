package edu.hsosna.gravelshipping;

public class GravelShipping extends Simulation {

	public static double toShip = 4000.0;
	public static double shipped = 0.0;
	private static final int NUMBER_OF_TRUCKS = 10;
	private static final int NUMBER_OF_WEIHGING_STATIONS = 2;
	private static final int NUMBER_OF_LOADING_STATIONS = 2;

	public static void main(String[] args) {
		GravelShipping gs = new GravelShipping();
		gs.initialize();
		gs.simulate();

	}

	@Override
	protected void initialize() {

		for (int i = 0; i < NUMBER_OF_TRUCKS; i++) {
			simObjects.add(new Truck());
		}
		for (int i = 0; i < NUMBER_OF_WEIHGING_STATIONS; i++) {
			simObjects.add(new WeighingStation());
		}
		for (int i = 0; i < NUMBER_OF_LOADING_STATIONS; i++) {
			simObjects.add(new LoadingStation());
		}

		LoadingStation.initialize();
		WeighingStation.initialize();
	}
}