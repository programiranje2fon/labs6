package task1;

public class HomeAppliance {
	
	private boolean turnedOn;
	private String brandAndModel;
	
	public HomeAppliance(boolean on, String brandAndModel) {
		this.turnedOn = on;
		this.brandAndModel = brandAndModel;
	}

	public void turnOnOrOff() {
		turnedOn = !turnedOn;
	}

	@Override
	public String toString() {
		return "BRAND AND MODEL: " + brandAndModel + " TURNED ON: " + turnedOn;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof HomeAppliance)) {
			return false;
		}
		
		HomeAppliance k = (HomeAppliance) obj;
		
		return this.brandAndModel.equals(k.brandAndModel);
	}
}
