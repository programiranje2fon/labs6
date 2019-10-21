package test.task2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task1.HomeAppliance;
import task2.Radio;
import test.TestUtil;

public class RadioTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private Radio instance;

	@Before
	public void setUp() throws Exception {
		instance = new Radio(true, "Audio sistem Sony MHC", 96.2);
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	public void class_nasledjivanje() {
		assertTrue("Class Radio should extend the class HomeAppliance", HomeAppliance.class.isInstance(instance));
	}
	
	@Test
	public void attribute_frequency() {
		assertTrue("There is no attribute frequency declared", TestUtil.doesFieldExist(Radio.class, "frequency"));
	}
	
	@Test
	public void attribute_frequency_visibility() {
		assertTrue("The attribute frequency should be private", TestUtil.hasFieldModifier(Radio.class, "frequency", Modifier.PRIVATE));
	}
	
	@Test
	public void constructor_Radio_AudioSistemSonyMHC() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 87.5);
		boolean ukljucenValue1 = (boolean) TestUtil.getFieldValue(r1, "turnedOn");
		String markaIModelValue1 = (String) TestUtil.getFieldValue(r1, "brandAndModel");
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		
		assertEquals("When the first argument is \"true\", the attribute turnedOn does not contain this value", true, ukljucenValue1);
		assertEquals("When the second argument is \"Audio sistem Sony MHC\", the attribute brandAndModel does not contain this value", "Audio sistem Sony MHC", markaIModelValue1);
		assertEquals("When the third argument is \"87.5\", the attribute frequency does not contain this value", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void constructor_Radio_MicroSystemBlaupunktMS30BT() {
		Radio r2 = new Radio(false, "Micro system Blaupunkt MS30BT", 107.9);
		boolean ukljucenValue2 = (boolean) TestUtil.getFieldValue(r2, "turnedOn");
		String markaIModelValue2 = (String) TestUtil.getFieldValue(r2, "brandAndModel");
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(r2, "frequency");
		
		assertEquals("When the first argument is \"false\", the attribute turnedOn does not contain this value", false, ukljucenValue2);
		assertEquals("When the second argument is \"Micro system Blaupunkt MS30BT\", the attribute brandAndModel does not contain this value", "Micro system Blaupunkt MS30BT", markaIModelValue2);
		assertEquals("When the third argument is \"107.9\", the attribute frequency does not contain this value", 107.9, frekvencijaValue2, 0.001);
	}
	
	/*
	 * test border cases for the attribute program
	 */
	
	@Test
	public void constructor_Radio_frequency874() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 87.4);

		assertTrue("For invalid arguments, the method does not print ERROR to the console", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		assertEquals("When the third argument is \"87.4\", the attribute frequency has the wrong value", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void constructor_Radio_frequency0() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 0);

		assertTrue("For invalid arguments, the method does not print ERROR to the console", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		assertEquals("When the third argument is \"0\", the attribute frequency has the wrong value", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void constructor_Radio_frequency108() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 108);

		assertTrue("For invalid arguments, the method does not print ERROR to the console", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		assertEquals("When the third argument is \"108\", the attribute frequency has the wrong value", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void method_setFrequency905() {
		instance.setFrequency(90.5);
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("For the method argument of \"90.5\", the method did not store this value to the attribute frequency", 90.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void method_setFrequency102() {
		instance.setFrequency(102);
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("For the method argument of \"102\", the method did not store this value to the attribute frequency", 102, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void method_setFrequency_belowLimitsMinus874() {
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(87.4);
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("For the method argument of \"87.4\", the value of the attribute frequency should not have been changed", frekvencijaValue1, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void method_setFrequency_belowLimitsMinus1000() {
		double frekvencijaValue3 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(-1000);
		double frekvencijaValue4 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("For the method argument of \"-1000\", the value of the attribute frequency should not have been changed", frekvencijaValue3, frekvencijaValue4, 0.001);
	}
	
	@Test
	public void method_setFrequency_aboveLimits108() {
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(108);
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("For the method argument of \"108\", the value of the attribute frequency should not have been changed", frekvencijaValue1, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void method_setFrequency_aboveLimits1000() {
		double frekvencijaValue3 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(1000);
		double frekvencijaValue4 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("For the method argument of \"1000\", the value of the attribute frequency should not have been changed", frekvencijaValue3, frekvencijaValue4, 0.001);
	}
	
	@Test
	public void method_getFrequency_withinLimits962() {
		instance.setFrequency(96.2);
		double frekvencijaValue1 = instance.getFrequency();
		assertEquals("When the attribute frequency is \"96.2\", the method getFrekvencija() does not return this value when called", 96.2, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void method_getFrequency_withinLimits106() {
		instance.setFrequency(106);
		double frekvencijaValue2 = instance.getFrequency();
		assertEquals("When the attribute frequency is \"106\", the method getFrekvencija() does not return this value when called", 106, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void method_toString() {
		assertTrue("The return value does not contain the value of the attribute frequency", instance.toString().contains("96.2"));
	}
}
