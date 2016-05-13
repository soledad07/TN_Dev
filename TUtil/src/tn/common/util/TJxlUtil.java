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
 * jxl 엑셀 파일 컨트롤 Util
 * @author dmhan
 *
 */
public class TJxlUtil {
	/**
	 * 엑셀 파일생성(data 이중배열안의 데이터로 엑셀파일생성) - 하나의 sheet만 생성
	 * EX) tJxlUtil.writeExcelData("g:\Project\TUtil\workspace\tn-util\TUtil\bin\file\", testExcel, data);
	 * @param path
	 * @param fileName
	 * @param data
	 * @return
	 */
	public boolean writeExcelData(String path, String fileName, String data[][]){
		WritableWorkbook workbook = null;
		
		WritableSheet sheet = null;

		/* 파일경로 유무 확인, 없으면 생성[ */
		File file = new File(path);

		if(!file.exists()){
			file.mkdirs();
		}
		/* ] */

		File excelFile = new File(path + File.separator + fileName + ".xls");
		
		/* 파일명 중복 체크, 중복시 파일명에 넘버링 추가[ */
		int fileIndex = 1;
		
		while(excelFile.exists()){
			excelFile = new File(path + File.separator + fileName + fileIndex + ".xls");
			
			fileIndex++;
		}
		/* ] */
		
		/* 엑셀파일 생성[ */
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
	 * 엑셀파일을 읽어 Workbook을 반환한다.
	 * 파일이 없거나 오류 발생 시 null 반환.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public Workbook readExcelWorkbook(String path, String fileName){
		Workbook workbook = null;
		
		/* 파일 존재 여부 확인[ */
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
		
		/* 엑셀파일 읽기[ */
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
	 * 엑셀파일을 읽어 Sheet[] 을 반환한다.
	 * 파일이 없거나 오류 발생 시 null 반환.
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
	 * 엑셀파일을 읽어 Sheet 을 반환한다.
	 * 파일이 없거나 오류 발생 시 null 반환.
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
	 * 엑셀파일을 읽어 해당 sheet 의 cell 을 반환한다.
	 * 파일이 없거나 오류 발생 시 null 반환.
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
	 * 엑셀파일을 읽어 해당 sheet 의 cell 의 data 를 반환한다.
	 * 파일이 없거나 오류 발생 시 null 반환.
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
