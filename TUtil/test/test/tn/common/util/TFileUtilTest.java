package test.tn.common.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tn.common.util.TFileUtil;

/**
 * ���� ���� ��ƿ
 * @author dmhan
 *
 */
public class TFileUtilTest {
	TFileUtil tFileUtil = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		if(tFileUtil == null){
			tFileUtil = new TFileUtil();
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void TFileUtil(){
		File file = new File("../../tn-util");
		
		System.out.println(tFileUtil.getFilePath(file, TFileUtil.PATH));
		System.out.println(tFileUtil.getFilePath(file, TFileUtil.ABSOLUTEPATH));
		System.out.println(tFileUtil.getFilePath(file, TFileUtil.CANONICALPATH));
		
		// �����
		assertEquals("..\\..\\tn-util", tFileUtil.getFilePath(file, TFileUtil.PATH));
		
		// ./ ../ ������ ������
		assertEquals("G:\\Project\\TUtil\\workspace\\tn-util\\TUtil\\..\\..\\tn-util", tFileUtil.getFilePath(file, TFileUtil.ABSOLUTEPATH));
		
		// ./ ../ �� �� ������
		assertEquals("G:\\Project\\TUtil\\workspace\\tn-util", tFileUtil.getFilePath(file, TFileUtil.CANONICALPATH));
	}
}
