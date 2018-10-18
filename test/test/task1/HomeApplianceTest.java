package test.task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task1.HomeAppliance;
import test.TestOrder;
import test.TestUtil;

@TestOrder(1)
public class HomeApplianceTest {

	HomeAppliance instance;

	@Before
	public void setUp() throws Exception {
		instance = new HomeAppliance(true, "Laptop Acer A315");
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_ukljucen() {
		assertTrue("U klasi nije definisan atribut ukljucen", TestUtil.doesFieldExist(HomeAppliance.class, "turnedOn"));
	}
	
	@Test
	public void atribut_ukljucen_vidljivost() {
		assertTrue("Atribut ukljucen nije privatan", TestUtil.hasFieldModifier(HomeAppliance.class, "turnedOn", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_markaIModel() {
		assertTrue("U klasi nije definisan atribut markaIModel", TestUtil.doesFieldExist(HomeAppliance.class, "brandAndModel"));
	}
	
	@Test
	public void atribut_markaIModel_vidljivost() {
		assertTrue("Atribut markaIModel nije privatan", TestUtil.hasFieldModifier(HomeAppliance.class, "brandAndModel", Modifier.PRIVATE));
	}
	
	@Test
	public void konstruktor_KucniAparat_LaptopAcerA315() {
		// testing with two different instances in case value of some of the attributes is hard-coded
		HomeAppliance k1 = new HomeAppliance(true, "Laptop Acer A315");
		boolean ukljucenValue1 = (boolean) TestUtil.getFieldValue(k1, "turnedOn");
		String markaIModelValue1 = (String) TestUtil.getFieldValue(k1, "brandAndModel");
		
		assertEquals("Za prosledjeni prvi argument \"true\", atribut ukljucen ima vrednost "+ukljucenValue1, true, ukljucenValue1);
		assertEquals("Za prosledjeni drugi argument \"Laptop Acer A315\", atribut markaIModel ima vrednost \""+markaIModelValue1+"\"", "Laptop Acer A315", markaIModelValue1);
	}
	
	@Test
	public void konstruktor_KucniAparat_CanonDSLR() {
		HomeAppliance k2 = new HomeAppliance(false, "Canon DSLR EOS 5D");
		boolean ukljucenValue2 = (boolean) TestUtil.getFieldValue(k2, "turnedOn");
		String markaIModelValue2 = (String) TestUtil.getFieldValue(k2, "brandAndModel");
		
		assertEquals("Za prosledjeni prvi argument \"true\", atribut ukljucen ima vrednost "+ukljucenValue2, false, ukljucenValue2);
		assertEquals("Za prosledjeni drugi argument \"Canon DSLR EOS 5D\", atribut markaIModel ima vrednost \""+markaIModelValue2+"\"", "Canon DSLR EOS 5D", markaIModelValue2);
	}
	
	@Test
	public void metoda_ukljuciIskljuci() {
		boolean ukljucenValue1 = (boolean) TestUtil.getFieldValue(instance, "turnedOn");
		instance.turnOnOrOff();
		boolean ukljucenValue2 = (boolean) TestUtil.getFieldValue(instance, "turnedOn");
		assertEquals("Nakon poziva metode ukljuciIskljuci, vrednost atributa ukljucen se nije promenila sa \""+ukljucenValue1+"\" na \""+!ukljucenValue1+"\"", !ukljucenValue1, ukljucenValue2);
		
		instance.turnOnOrOff();
		boolean ukljucenValue3 = (boolean) TestUtil.getFieldValue(instance, "turnedOn");
		assertEquals("Nakon poziva metode ukljuciIskljuci, vrednost atributa ukljucen se nije promenila sa \""+ukljucenValue2+"\" na \""+!ukljucenValue2+"\"", !ukljucenValue2, ukljucenValue3);
	}
	
	@Test
	public void metoda_toString() {
		assertEquals("Metoda toString ne vraca String u odgovarajucem formatu", "BRAND AND MODEL: Laptop Acer A315 TURNED ON: true", instance.toString());
	}
	
	@Test
	public void metoda_equals_pogresanTip() {
		assertEquals("Metoda equals ne vraca false ako je prosledjen objekat koji nije KucniAparat", false, instance.equals(new Object()));
	}
	
	@Test
	public void metoda_equals_isti() {
		// testing with two different instances in case value of the the attribute markaIModel is hard-coded
		HomeAppliance k1 = new HomeAppliance(true, "Laptop Acer A315");
		HomeAppliance k2 = new HomeAppliance(true, "Laptop Acer A315");
		assertEquals("Metoda equals ne vraca true kada je pozvana nad kucnim aparatom sa markom i modelom \"Laptop Acer A315\", a prosledjen je kucni aparat sa markom i modelom \"Laptop Acer A315\"", true, k1.equals(k2));
	}
	
	@Test
	public void metoda_equals_razliciti() {
		HomeAppliance k1 = new HomeAppliance(true, "Laptop Acer A315");
		HomeAppliance k2 = new HomeAppliance(true, "LG OLED65");
		
		assertEquals("Metoda equals ne vraca false kada je pozvana nad kucnim aparatom sa markom i modelom \"Laptop Acer A315\", a prosledjen je kucni aparat sa markom i modelom \"LG OLED65\"", false, k1.equals(k2));
	}
	
}
