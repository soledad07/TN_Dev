package test.tn.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tn.common.util.StringUtil;

public class StringUtilTest {
	public StringUtil stringUtil = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		if(stringUtil == null){
			stringUtil = new StringUtil();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLPad() {
		assertEquals("000000123", stringUtil.lPad("123", 9, '0'));
		assertEquals("000000123", stringUtil.lPad(123, 9, 0));
	}

}
