package test.task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task1.HomeAppliance;
import test.TestUtil;

public class HomeApplianceTest {

	private HomeAppliance instance;

	@Before
	public void setUp() throws Exception {
		instance = new HomeAppliance(true, "Laptop Acer A315");
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void attribute_turnedOn() {
		assertTrue("There is no attribute turnedOn declared", TestUtil.doesFieldExist(HomeAppliance.class, "turnedOn"));
	}
	
	@Test
	public void attribute_turnedOn_visibility() {
		assertTrue("Attribute turnedOn is not private", TestUtil.hasFieldModifier(HomeAppliance.class, "turnedOn", Modifier.PRIVATE));
	}
	
	@Test
	public void attribute_brandAndModel() {
		assertTrue("There is no attribute brandAndModel declared", TestUtil.doesFieldExist(HomeAppliance.class, "brandAndModel"));
	}
	
	@Test
	public void attribute_brandAndModel_visibility() {
		assertTrue("Attribute brandAndModel is not private", TestUtil.hasFieldModifier(HomeAppliance.class, "brandAndModel", Modifier.PRIVATE));
	}
	
	@Test
	public void constructor_HomeAppliance_LaptopAcerA315() {
		// testing with two different instances in case value of some of the attributes is hard-coded
		HomeAppliance k1 = new HomeAppliance(true, "Laptop Acer A315");
		boolean ukljucenValue1 = (boolean) TestUtil.getFieldValue(k1, "turnedOn");
		String markaIModelValue1 = (String) TestUtil.getFieldValue(k1, "brandAndModel");
		
		assertEquals("When the first argument is \"true\", the attribute turnedOn does not contain this value", true, ukljucenValue1);
		assertEquals("When the second argument is \"Laptop Acer A315\", the attribute brandAndModel does not contain this value", "Laptop Acer A315", markaIModelValue1);
	}
	
	@Test
	public void constructor_HomeAppliance_CanonDSLR() {
		HomeAppliance k2 = new HomeAppliance(false, "Canon DSLR EOS 5D");
		boolean ukljucenValue2 = (boolean) TestUtil.getFieldValue(k2, "turnedOn");
		String markaIModelValue2 = (String) TestUtil.getFieldValue(k2, "brandAndModel");
		
		assertEquals("When the first argument is \"true\", the attribute turnedOn does not contain this value", false, ukljucenValue2);
		assertEquals("When the second argument is \"Canon DSLR EOS 5D\", the attribute brandAndModel does not contain this value", "Canon DSLR EOS 5D", markaIModelValue2);
	}
	
	@Test
	public void method_turnOnOrOff() {
		boolean ukljucenValue1 = (boolean) TestUtil.getFieldValue(instance, "turnedOn");
		instance.turnOnOrOff();
		boolean ukljucenValue2 = (boolean) TestUtil.getFieldValue(instance, "turnedOn");
		assertEquals("After calling the method turnOnOrOff, the value of the attribute turnedOn did not change", !ukljucenValue1, ukljucenValue2);
		
		instance.turnOnOrOff();
		boolean ukljucenValue3 = (boolean) TestUtil.getFieldValue(instance, "turnedOn");
		assertEquals("After calling the method turnOnOrOff twice, the value of the attribute turnedOn changed", !ukljucenValue2, ukljucenValue3);
	}
	
	@Test
	public void method_toString() {
		assertTrue("The return value does not contain the value of the attribute turnedOn", instance.toString().contains("true"));
		assertTrue("The return value does not contain the value of the attribute brandAndModel", instance.toString().contains("Laptop Acer A315"));
	}
	
	@Test
	public void method_equals_wrongType() {
		assertEquals("The equals method does not return false when the argument is of a type other than HomeAppliance", false, instance.equals(new Object()));
	}
	
	@Test
	public void method_equals_same() {
		// testing with two different instances in case value of the the attribute markaIModel is hard-coded
		HomeAppliance k1 = new HomeAppliance(true, "Laptop Acer A315");
		HomeAppliance k2 = new HomeAppliance(true, "Laptop Acer A315");
		assertEquals("The method should return true when the argument passed is an object with the same values for both attributes turnedOn and brandAndModel", true, k1.equals(k2));
	}
	
	@Test
	public void method_equals_different() {
		HomeAppliance k1 = new HomeAppliance(true, "Laptop Acer A315");
		HomeAppliance k2 = new HomeAppliance(true, "LG OLED65");
		
		assertEquals("he method should return false when the argument passed is an object with different values for any of the attributes turnedOn and brandAndModel", false, k1.equals(k2));
	}
	
}
