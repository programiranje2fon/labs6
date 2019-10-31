package test.task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task1.HomeAppliance;
import task1.TV;
import test.TestUtil;

public class TVTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private TV instance;

	@Before
	public void setUp() throws Exception {
		instance = new TV(true, "Samsung UE40", 1);
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
		assertTrue("Class TV should extend the class HomeAppliance", HomeAppliance.class.isInstance(instance));
	}
	
	@Test
	public void attribute_program() {
		assertTrue("There is no attribute program declared", TestUtil.doesFieldExist(TV.class, "program"));
	}
	
	@Test
	public void attribute_program_visibility() {
		assertTrue("The attribute program should be private", TestUtil.hasFieldModifier(TV.class, "program", Modifier.PRIVATE));
	}
	
	@Test
	public void constructor_TV_SamsungUE40() {
		TV t1 = new TV(true, "Samsung UE40", 1);
		boolean ukljucenValue1 = (boolean) TestUtil.getFieldValue(t1, "turnedOn");
		String markaIModelValue1 = (String) TestUtil.getFieldValue(t1, "brandAndModel");
		int programValue1 = (int) TestUtil.getFieldValue(t1, "program");
		
		assertEquals("When the first argument is \"true\", the attribute turnedOn does not contain this value", true, ukljucenValue1);
		assertEquals("When the second argument is \"Samsung UE40\", the attribute brandAndModel does not contain this value", "Samsung UE40", markaIModelValue1);
		assertEquals("When the third argument is \"1\", the attribute program does not contain this value", 1, programValue1);
	}
	
	@Test
	public void constructor_TV_LGOLED65() {
		TV t2 = new TV(false, "LG OLED65", 40);
		boolean ukljucenValue2 = (boolean) TestUtil.getFieldValue(t2, "turnedOn");
		String markaIModelValue2 = (String) TestUtil.getFieldValue(t2, "brandAndModel");
		int programValue2 = (int) TestUtil.getFieldValue(t2, "program");
		
		assertEquals("When the first argument is \"false\", the attribute turnedOn does not contain this value", false, ukljucenValue2);
		assertEquals("When the second argument is \"LG OLED65\", the attribute brandAndModel does not contain this value", "LG OLED65", markaIModelValue2);
		assertEquals("When the third argument is \"40\", the attribute program does not contain this value", 40, programValue2);
	}
	
	/*
	 * test border cases for the attribute program
	 */
	
	@Test
	public void constructor_TV_program0() {
		TV t1 = new TV(false, "LG OLED65", 0);

		assertTrue("For invalid arguments, the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("error"));
		
		int programValue1 = (int) TestUtil.getFieldValue(t1, "program");
		assertEquals("When the third argument is \"0\", the attribute program has the wrong value", 1, programValue1);
	}
	
	@Test
	public void constructor_TV_programMinus1000() {
		TV t1 = new TV(false, "LG OLED65", -1000);

		assertTrue("For invalid arguments, the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("error"));
		
		int programValue1 = (int) TestUtil.getFieldValue(t1, "program");
		assertEquals("When the third argument is \"-1000\", the attribute program has the wrong value", 1, programValue1);
	}
	
	@Test
	public void constructor_TV_program41() {
		TV t1 = new TV(false, "LG OLED65", 41);

		assertTrue("For invalid arguments, the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("error"));
		
		int programValue1 = (int) TestUtil.getFieldValue(t1, "program");
		assertEquals("When the third argument is \"41\", the attribute program has the wrong value", 1, programValue1);
	}
	
	@Test
	public void constructor_TV_program1000() {
		TV t1 = new TV(false, "LG OLED65", 1000);

		assertTrue("For invalid arguments, the method does not print ERROR to the console", outContent.toString().toLowerCase().contains("error"));
		
		int programValue1 = (int) TestUtil.getFieldValue(t1, "program");
		assertEquals("When the third argument is \"1000\", the attribute program has the wrong value", 1, programValue1);
	}
	
	@Test
	public void method_changeProgram_withinLimits1() {
		instance.changeProgram(1);
		int programValue1 = (int) TestUtil.getFieldValue(instance, "program");
		assertEquals("For the method argument of \"1\", the method did not store this value to the attribute program", 1, programValue1);
	}
	
	@Test
	public void method_changeProgram_withinLimits40() {
		instance.changeProgram(40);
		int programValue2 = (int) TestUtil.getFieldValue(instance, "program");
		assertEquals("For the method argument of \"40\", the method did not store this value to the attribute program", 40, programValue2);
	}
	
	@Test
	public void method_changeProgram_bellowTheLimitOfMinus0() {
		int programValue1 = (int) TestUtil.getFieldValue(instance, "program");
		instance.changeProgram(0);
		int programValue2 = (int) TestUtil.getFieldValue(instance, "program");
		assertEquals("For the method argument of \"0\", the value of the attribute program should not have been changed", programValue1, programValue2);
	}
	
	public void method_changeProgram_bellowTheLimitOfMinus1000() {
		int programValue3 = (int) TestUtil.getFieldValue(instance, "program");
		instance.changeProgram(-1000);
		int programValue4 = (int) TestUtil.getFieldValue(instance, "program");
		assertEquals("For the method argument of \"-1000\", the value of the attribute program should not have been changed", programValue3, programValue4);
	}
	
	@Test
	public void method_changeProgram_aboveTheLimitOf41() {
		int programValue1 = (int) TestUtil.getFieldValue(instance, "program");
		instance.changeProgram(41);
		int programValue2 = (int) TestUtil.getFieldValue(instance, "program");
		assertEquals("For the method argument of \"41\", the value of the attribute program should not have been changed", programValue1, programValue2);
	}
	
	@Test
	public void method_changeProgram_aboveTheLimitOf1000() {
		int programValue3 = (int) TestUtil.getFieldValue(instance, "program");
		instance.changeProgram(1000);
		int programValue4 = (int) TestUtil.getFieldValue(instance, "program");
		assertEquals("For the method argument of \"1000\", the value of the attribute program should not have been changed", programValue3, programValue4);
	}
	
	@Test
	public void method_toString() {
		assertTrue("The return value does not contain the value of the attribute turnedOn", instance.toString().contains("true"));
		assertTrue("The return value does not contain the value of the attribute brandAndModel", instance.toString().toLowerCase().contains("Samsung UE40"));
		assertTrue("The return value does not contain the value of the attribute program", instance.toString().contains("1"));
	}
	
}
