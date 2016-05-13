package test.tn.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tn.common.util.DateUtil;

public class DateUtilTest {
	DateUtil dateUtil = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		if(dateUtil == null){
			dateUtil = new DateUtil();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFormatString() {
		String today = "20130403";	// ¿À´Ã³¯Â¥

		assertEquals(today, dateUtil.getFormatToDay("YYYYMMdd"));
	}
	
	@Test
	public void isDate(){
		assertTrue(dateUtil.isDate("20130401"));
		assertFalse(dateUtil.isDate("20130432"));
		assertFalse(dateUtil.isDate("20130231"));
	}
	
	@Test
	public void getDayDistance() throws Exception{
		assertEquals(3, dateUtil.getDayDistance("20130401", "20130404"));
		assertEquals(31, dateUtil.getDayDistance("20130301", "20130401"));
		assertEquals(30, dateUtil.getDayDistance("20130302", "20130401"));
	}
	
	@Test
	public void getPeriodTimeString(){
		assertEquals("000130", dateUtil.getPeriodTimeString("090000", "090130"));
		assertEquals("010130", dateUtil.getPeriodTimeString("080000", "090130"));
		assertEquals("000000", dateUtil.getPeriodTimeString("100000", "090130"));
	}
}
