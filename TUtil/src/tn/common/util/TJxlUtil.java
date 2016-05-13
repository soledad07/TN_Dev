package tn.common.util;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * jxl ���� ���� ��Ʈ�� Util
 * @author dmhan
 *
 */
public class TJxlUtil {
	/**
	 * ���� ���ϻ���(data ���߹迭���� �����ͷ� �������ϻ���) - �ϳ��� sheet�� ����
	 * EX) tJxlUtil.writeExcelData("g:\Project\TUtil\workspace\tn-util\TUtil\bin\file\", testExcel, data);
	 * @param path
	 * @param fileName
	 * @param data
	 * @return
	 */
	public boolean writeExcelData(String path, String fileName, String data[][]){
		WritableWorkbook workbook = null;
		
		WritableSheet sheet = null;

		/* ���ϰ�� ���� Ȯ��, ������ ����[ */
		File file = new File(path);

		if(!file.exists()){
			file.mkdirs();
		}
		/* ] */

		File excelFile = new File(path + File.separator + fileName + ".xls");
		
		/* ���ϸ� �ߺ� üũ, �ߺ��� ���ϸ� �ѹ��� �߰�[ */
		int fileIndex = 1;
		
		while(excelFile.exists()){
			excelFile = new File(path + File.separator + fileName + fileIndex + ".xls");
			
			fileIndex++;
		}
		/* ] */
		
		/* �������� ����[ */
		try {
			workbook = Workbook.createWorkbook(excelFile);
			
			workbook.createSheet("sheet1", 0);
			
			sheet = workbook.getSheet(0);
			
			for(int i=0 ; i<data.length ; i++){
				for(int j=0 ; j<data[i].length ; j++){
					sheet.addCell(new Label(i, j, data[i][j]));
				}
			}
			
			workbook.write();
			
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();

			return false;
		} catch (RowsExceededException e) {
			e.printStackTrace();
			
			return false;
		} catch (WriteException e) {
			e.printStackTrace();
			
			return false;
		}
		/* ] */
		
		return true;
	}
	
	/**
	 * ���������� �о� Workbook�� ��ȯ�Ѵ�.
	 * ������ ���ų� ���� �߻� �� null ��ȯ.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public Workbook readExcelWorkbook(String path, String fileName){
		Workbook workbook = null;
		
		/* ���� ���� ���� Ȯ��[ */
		File file = new File(path + fileName);
		
		if(!file.exists()){
			file = new File(path + File.separator + fileName);
			
			if(!file.exists()){
				file = new File(path + File.separator + fileName + ".xls");
				
				if(!file.exists()){
					return null;
				}
			}
		}
		/* ] */
		
		/* �������� �б�[ */
		try {
			workbook = Workbook.getWorkbook(file);
		
		} catch (BiffException | IOException e) {
			e.printStackTrace();
			
			return null;
		}
		/* ] */
		
		return workbook;
	}
	
	/**
	 * ���������� �о� Sheet[] �� ��ȯ�Ѵ�.
	 * ������ ���ų� ���� �߻� �� null ��ȯ.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public Sheet[] readExcelSheets(String path, String fileName){
		Sheet[] sheets = null;
		
		Workbook workbook = readExcelWorkbook(path, fileName);
		
		if(workbook == null){
			return null;
		}
		
		sheets = workbook.getSheets();
		
		return sheets;
	}
	
	/**
	 * ���������� �о� Sheet �� ��ȯ�Ѵ�.
	 * ������ ���ų� ���� �߻� �� null ��ȯ.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public Sheet readExcelSheets(String path, String fileName, int sheetIndex){
		Sheet sheet = null;
		
		Sheet[] sheets = readExcelSheets(path, fileName);
		
		if(sheets != null && sheetIndex >= 0 && sheets.length > sheetIndex){
			sheet = sheets[sheetIndex];
		}else{
			return null;
		}
		
		return sheet;
	}
	
	/**
	 * ���������� �о� �ش� sheet �� cell �� ��ȯ�Ѵ�.
	 * ������ ���ų� ���� �߻� �� null ��ȯ.
	 * @param path
	 * @param fileName
	 * @param sheetIndex
	 * @param column
	 * @param row
	 * @return
	 */
	public Cell readExcelCell(String path, String fileName, int sheetIndex, int column, int row){
		Sheet sheet = readExcelSheets(path, fileName, sheetIndex);
		
		if(sheet == null){
			return null;
		}
		
		return sheet.getCell(column, row);
	}
	
	/**
	 * ���������� �о� �ش� sheet �� cell �� data �� ��ȯ�Ѵ�.
	 * ������ ���ų� ���� �߻� �� null ��ȯ.
	 * @param path
	 * @param fileName
	 * @param sheetIndex
	 * @param column
	 * @param row
	 * @return
	 */
	public String readExcelCellData(String path, String fileName, int sheetIndex, int column, int row){
		Cell cell = readExcelCell(path, fileName, sheetIndex, column, row);
		
		if(cell == null){
			return null;
		}
		
		return cell.getContents();
	}
}
