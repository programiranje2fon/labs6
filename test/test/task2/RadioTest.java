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
import test.TestOrder;
import test.TestUtil;

@TestOrder(3)
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
	public void klasa_nasledjivanje() {
		assertTrue("Klasa Radio ne nasledjuje klasu KucniAparat", HomeAppliance.class.isInstance(instance));
	}
	
	@Test
	public void atribut_frekvencija() {
		assertTrue("U klasi nije definisan atribut frekvencija", TestUtil.doesFieldExist(Radio.class, "frequency"));
	}
	
	@Test
	public void atribut_frekvencija_vidljivost() {
		assertTrue("Atribut frekvencija nije privatan", TestUtil.hasFieldModifier(Radio.class, "frequency", Modifier.PRIVATE));
	}
	
	@Test
	public void konstruktor_Radio_AudioSistemSonyMHC() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 87.5);
		boolean ukljucenValue1 = (boolean) TestUtil.getFieldValue(r1, "turnedOn");
		String markaIModelValue1 = (String) TestUtil.getFieldValue(r1, "brandAndModel");
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		
		assertEquals("Za prosledjeni prvi argument \"true\", atribut ukljucen ima vrednost "+ukljucenValue1, true, ukljucenValue1);
		assertEquals("Za prosledjeni drugi argument \"Audio sistem Sony MHC\", atribut markaIModel ima vrednost \""+markaIModelValue1+"\"", "Audio sistem Sony MHC", markaIModelValue1);
		assertEquals("Za prosledjeni treci argument \"87.5\", atribut frekvencija ima vrednost \""+frekvencijaValue1+"\"", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void konstruktor_Radio_MicroSystemBlaupunktMS30BT() {
		Radio r2 = new Radio(false, "Micro system Blaupunkt MS30BT", 107.9);
		boolean ukljucenValue2 = (boolean) TestUtil.getFieldValue(r2, "turnedOn");
		String markaIModelValue2 = (String) TestUtil.getFieldValue(r2, "brandAndModel");
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(r2, "frequency");
		
		assertEquals("Za prosledjeni prvi argument \"false\", atribut ukljucen ima vrednost "+ukljucenValue2, false, ukljucenValue2);
		assertEquals("Za prosledjeni drugi argument \"Micro system Blaupunkt MS30BT\", atribut markaIModel ima vrednost \""+markaIModelValue2+"\"", "Micro system Blaupunkt MS30BT", markaIModelValue2);
		assertEquals("Za prosledjeni treci argument \"107.9\", frekvencija program ima vrednost \""+frekvencijaValue2+"\"", 107.9, frekvencijaValue2, 0.001);
	}
	
	/*
	 * test border cases for the attribute program
	 */
	
	@Test
	public void konstruktor_Radio_frekvencija874() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 87.4);

		assertTrue("NE ispisuje se rec ERROR u slucaju greske", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		assertEquals("Za prosledjeni treci argument \"87.4\", atribut frekvencija ima vrednost \""+frekvencijaValue1+"\", sto je van granica", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void konstruktor_Radio_frekvencija0() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 0);

		assertTrue("NE ispisuje se rec ERROR u slucaju greske", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		assertEquals("Za prosledjeni treci argument \"0\", atribut frekvencija ima vrednost \""+frekvencijaValue1+"\", sto je van granica", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void konstruktor_Radio_frekvencija108() {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 108);

		assertTrue("NE ispisuje se rec ERROR u slucaju greske", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(r1, "frequency");
		assertEquals("Za prosledjeni treci argument \"108\", atribut frekvencija ima vrednost \""+frekvencijaValue1+"\", sto je van granica", 87.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void metoda_setFrekvencija905() {
		instance.setFrequency(90.5);
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("Metoda setFrekvencija(double) sa prosledjenim argumentom \"90.5\" nije postavila atribut frekvencija na tu vrednost", 90.5, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void metoda_setFrekvencija102() {
		instance.setFrequency(102);
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("Metoda setFrekvencija(double) sa prosledjenim argumentom \"102\" nije postavila atribut frekvencija na tu vrednost", 102, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void metoda_setFrekvencija_parameterIspodGranice874() {
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(87.4);
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("Metoda setFrekvencija(double) sa prosledjenim argumentom \"87.4\" ne treba da menja vrednost atributa frekvencija", frekvencijaValue1, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void metoda_setFrekvencija_parameterIspodGraniceMinus1000() {
		double frekvencijaValue3 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(-1000);
		double frekvencijaValue4 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("Metoda setFrekvencija(double) sa prosledjenim argumentom \"-1000\" ne treba da menja vrednost atributa frekvencija", frekvencijaValue3, frekvencijaValue4, 0.001);
	}
	
	@Test
	public void metoda_setFrekvencija_parametarIznadGranice108() {
		double frekvencijaValue1 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(108);
		double frekvencijaValue2 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("Metoda setFrekvencija(double) sa prosledjenim argumentom \"108\" ne treba da menja vrednost atributa frekvencija", frekvencijaValue1, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void metoda_setFrekvencija_parametarIznadGranice1000() {
		double frekvencijaValue3 = (double) TestUtil.getFieldValue(instance, "frequency");
		instance.setFrequency(1000);
		double frekvencijaValue4 = (double) TestUtil.getFieldValue(instance, "frequency");
		assertEquals("Metoda setFrekvencija(double) sa prosledjenim argumentom \"1000\" ne treba da menja vrednost atributa frekvencija", frekvencijaValue3, frekvencijaValue4, 0.001);
	}
	
	@Test
	public void metoda_getFrekvencija_uGranicama962() {
		instance.setFrequency(96.2);
		double frekvencijaValue1 = instance.getFrequency();
		assertEquals("Za prethodno postavljenu frekvenciju na \"96.2\", metoda getFrekvencija() vraca "+frekvencijaValue1, 96.2, frekvencijaValue1, 0.001);
	}
	
	@Test
	public void metoda_getFrekvencija_uGranicama106() {
		instance.setFrequency(106);
		double frekvencijaValue2 = instance.getFrequency();
		assertEquals("Za prethodno postavljenu frekvenciju na \"106\", metoda getFrekvencija() vraca "+frekvencijaValue2, 106, frekvencijaValue2, 0.001);
	}
	
	@Test
	public void metoda_toString() {
		Radio r1 = new Radio(false, "Audio sistem Sony MHC", 87.5);
		
		assertEquals("Metoda toString ne vraca String u odgovarajucem formatu", "RADIO FREQUENCY: 87.5 Mhz", r1.toString());
	}
}
