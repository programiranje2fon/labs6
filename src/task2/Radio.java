package task2;

import task1.HomeAppliance;

public class Radio extends HomeAppliance {

	private double frequency;

	public Radio(boolean on, String brandModel, double frequency) {
		super(on, brandModel);

		if ((frequency < 87.5) || (frequency > 107.9)) {
			System.out.println("ERROR");
			this.frequency = 87.5;
		} else
			this.frequency = frequency;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		if ((frequency >= 87.5) && (frequency <= 107.9))
			this.frequency = frequency;
		else
			System.out.println("ERROR");
	}

	@Override
	public String toString() {
		return "RADIO FREQUENCY: " + frequency + " Mhz";
	}

}
