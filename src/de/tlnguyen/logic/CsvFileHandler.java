package de.tlnguyen.logic;

import de.tlnguyen.model.Entry;
import de.tlnguyen.model.IntData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvFileHandler {
	
	//region 0. Konstanten
	public static final String LIST_FILE_PATH     = "src/de/tlnguyen/resources/entries.csv";
	public static final String INT_FILE_PATH      = "src/de/tlnguyen/resources/intdata.csv";
	//endregion
	
	//region 1. Attribute
	private static CsvFileHandler instance;
	//endregion
	
	//region 2. Konstruktor
	private CsvFileHandler() {
	}
	//endregion
	
	//region 3. Getter
	public static synchronized CsvFileHandler getInstance() {
		
		if (instance == null) {
			return new CsvFileHandler();
		}
		
		return instance;
	}
	//endregion
	
	//region 4. saveEntriesToCsvFile
	public void saveEntriesToFile(List<Entry> listOfEntries) {
		
		File           file = new File(LIST_FILE_PATH);
		BufferedWriter bw   = null;
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream   fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			bw = new BufferedWriter(osw);
			
			for (Entry entry : listOfEntries) {
				bw.write(entry.getAttributesAsCsvFileString() + "\n");
			}
		} catch (Exception e) {
			System.err.println(LIST_FILE_PATH);
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//endregion
	
	//region 5. readEntriesFromFile
	public List<Entry> readEntriesFromFile() {
		List<Entry> entryList = new ArrayList<>();
		File          file      = new File(LIST_FILE_PATH);
		
		BufferedReader br = null;
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileInputStream   fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);
			
			boolean endOfFile = false;
			
			while (!endOfFile) {
				String readLine = br.readLine();
				
				if (readLine != null) {
					Entry entry = new Entry();
					entry.setAttributesFromCsvFile(readLine);
					entryList.add(entry);
					
				} else {
					endOfFile = true;
				}
			}
		} catch (Exception e) {
			System.err.println(LIST_FILE_PATH);
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return entryList;
	}
	//endregion
	
	//region 6. saveIntDataToCsvFile
	public void saveIntDataToFile(IntData totalCaloriesAndLimit) {
		
		File           file = new File(INT_FILE_PATH);
		BufferedWriter bw   = null;
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream   fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			bw = new BufferedWriter(osw);
			
			bw.write(totalCaloriesAndLimit.getAttributesAsCsvFileString());
			/*for (int i : intData) {
				bw.write(i);
			}*/
			
		} catch (Exception e) {
			System.err.println(INT_FILE_PATH);
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//endregion
	
	//region 7. readIntDataFromFile
	public IntData readIntDataFromFile() {
		
		IntData totalCaloriesAndLimit = new IntData();
		
		File           file = new File(INT_FILE_PATH);
		BufferedReader br   = null;
		
		try {
			
			if (!file.exists()) {
				
				file.createNewFile();
			}
			
			FileInputStream   fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);
			
			String readLine = br.readLine();
			if (readLine != null) {
				totalCaloriesAndLimit.setAttributesFromCsvFile(readLine);
			}
			/*for (int i = 0; i < NUMBER_OF_INT_DATA; i++) {
				intData[i] = br.read();
			}*/
		} catch (Exception e) {
			
			System.err.println(INT_FILE_PATH);
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		return totalCaloriesAndLimit;
	}
	//endregion
}

