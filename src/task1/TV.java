package task1;

public class TV extends HomeAppliance {

	private int program;

	public TV(boolean on, String brandModel, int program) {
		super(on, brandModel);
		
		if ((program < 1) || (program > 40)) {
			System.out.println("ERROR");
			this.program = 1;
		} else 
			this.program = program;
	}

	public void changeProgram(int program) {
		if (program >= 1 && program <= 40) {
			this.program = program;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " PROGRAM: " + program;
	}
}
