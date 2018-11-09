package task1.test;

import task1.HomeAppliance;
import task1.TV;

public class TestHomeAppliance {

	public static void main(String[] args) {

		HomeAppliance k1 = new HomeAppliance(true, "Laptop Acer A315");
		HomeAppliance k2 = new HomeAppliance(true, "Laptop Acer A315");
		
		k1.turnOnOrOff();
		
		System.out.println(k1.toString());
		System.out.println(k1.equals(k2));
		
		TV t1 = new TV(true, "Samsung UE40", 1);
		
		t1.turnOnOrOff();
		t1.changeProgram(7);
		
		System.out.println(t1.toString());
		
		HomeAppliance k3 = t1;
		
		k3.turnOnOrOff();
	}

}
