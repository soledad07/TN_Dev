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
	 * ���ϰ�� ��ȯ
	 * @param file
	 * @param flag	PATH - �����
	 * 				ABSOLUTEPATH - ./ ../ ������ ������
	 * 				CANONICALPATH - ./ ../ �� �� ������
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
	 * �Է¹��� ���ϸ� �ش��ϴ� ������ �о� BufferedReader�� ������ ��ȯ�Ѵ�.
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
	 * �Է¹��� ������ BufferedReader�� ��� ��ȯ�Ѵ�.
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
	 * ���ϸ��� �Է� �޾� File Writer �� ���� �Ѵ�.
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
	 * ������ �Է¹��� ������ ���� FileWriter�� ��ȯ�Ѵ�.
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
	 * ���ϰ��� ��ü�� �ݴ´�.
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
