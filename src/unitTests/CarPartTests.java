package unitTests;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.digitreko.games.model.CarPart;
import com.digitreko.games.model.Team;
import com.digitreko.games.model.Brakes;

public class CarPartTests {
	
	Team t = new Team();
	CarPart b = new Brakes(1);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		t.setFunds(20000000);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testUpgradeOK() {
		Assert.assertEquals(100000, b.costToUpgrade()); 
		Assert.assertEquals(260000, b.costToUpgrade());
		Assert.assertEquals(390000, b.costToUpgrade());
		Assert.assertEquals(520000, b.costToUpgrade());
		Assert.assertEquals(650000, b.costToUpgrade());
		Assert.assertEquals(780000, b.costToUpgrade());
		Assert.assertEquals(910000, b.costToUpgrade());
		Assert.assertEquals(1040000, b.costToUpgrade());
		Assert.assertEquals(1070000, b.costToUpgrade());
		Assert.assertEquals(1100000, b.costToUpgrade());		
	}
	

}
