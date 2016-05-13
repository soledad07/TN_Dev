package test.tn.common.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tn.common.util.TJxlUtil;

public class TjxlUtilTest {
	TJxlUtil tJxlUtil = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		if(tJxlUtil == null){
			tJxlUtil = new TJxlUtil();
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void writeExcelData() {
		File file = new File("");
		
		String path = file.getAbsolutePath() + File.separator + "file";

		String fileName = "testExcel";
		
		String data[][] = new String[10][];
		
		for(int i=0 ; i<10 ; i++){	// column 데이터
			data[i] = new String[100];
			
			for(int j=0 ; j<100 ; j++){	// row 데이터
				data[i][j] = i +", " + j;
			}
		}

		tJxlUtil.writeExcelData(path, fileName, data);
	}
	
	@Test
	public void readExcelCellData(){
//		String path = this.getClass().getResource("/").getPath();	// Class root 경로
		
		File file = new File("");
		
		String path = file.getAbsolutePath() + File.separator + "file";

		String fileName = "testExcel";
		
		assertEquals("5, 56", tJxlUtil.readExcelCellData(path, fileName, 0, 5, 56));
		assertEquals("7, 28", tJxlUtil.readExcelCellData(path, fileName, 0, 7, 28));
		assertEquals("4, 31", tJxlUtil.readExcelCellData(path, fileName, 0, 4, 31));
	}
}
