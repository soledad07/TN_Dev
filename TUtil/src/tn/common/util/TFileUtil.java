package tn.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TFileUtil {
	public static final int PATH			= 0;
	public static final int ABSOLUTEPATH	= 1;
	public static final int CANONICALPATH	= 2;
	
	/**
	 * 파일경로 반환
	 * @param file
	 * @param flag	PATH - 상대경로
	 * 				ABSOLUTEPATH - ./ ../ 포함한 절대경로
	 * 				CANONICALPATH - ./ ../ 를 뺀 절대경로
	 * @return
	 */
	public String getFilePath(File file, int flag){
		String path = null;
		
		switch(flag){
		case PATH:
			path = file.getPath();

			break;
		case ABSOLUTEPATH:
			path = file.getAbsolutePath();

			break;
		case CANONICALPATH:
			try {
				path = file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				
				return null;
			}

			break;
		}
		
		return path;
	}
	
	/**
	 * 입력받은 파일명에 해당하는 파일을 읽어 BufferedReader를 생성해 반환한다.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public BufferedReader getBufferedFileReader(String path, String fileName){
		FileReader fr = null;
		
		try {
			fr = new FileReader(new File(path + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(fr);
		
		return br;
	}
	
	/**
	 * 입력받은 파일을 BufferedReader에 담아 반환한다.
	 * @param file
	 * @return
	 */
	public BufferedReader getBufferedFileReader(File file){
		FileReader fr = null;
		
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(fr);
		
		return br;
	}
	
	/**
	 * 파일명을 입력 받아 File Writer 를 생성 한다.
	 * @param path
	 * @param fileName
	 * @return
	 */
	public FileWriter createFileWriter(String path, String fileName){
		File file = new File(path + fileName);
		
		FileWriter wf = null;
		
		try {
			wf = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wf;
	}
	
	/**
	 * 파일을 입력받은 파일을 담은 FileWriter를 반환한다.
	 * @param file
	 * @return
	 */
	public FileWriter createFileWriter(File file){
		FileWriter wf = null;
		
		try {
			wf = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wf;
	}
	
	/**
	 * 파일관련 객체를 닫는다.
	 * @param obj
	 */
	public void closeFileObj(Object obj){
		if(obj instanceof BufferedReader){
			try {
				((BufferedReader) obj).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(obj instanceof FileWriter){
			try {
				((FileWriter) obj).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
