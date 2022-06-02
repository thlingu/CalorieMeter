package de.tlnguyen.testdata;

import de.tlnguyen.model.Entry;
import de.tlnguyen.model.IntData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestData {
	//region 0. Konstanten
	public static final int NUMBER_OF_TEST_ENTRIES = 5;
	public static final String GENERIC_TIME = "OO:0";
	public static final String GENERIC_FOOD = "Produkt ";
	public static final String GENERIC_CALORIES = "20";
	public static final LocalDate GENERIC_DATE = LocalDate.of(2000,01,01);
	//endregion
	
	//region 1. Attribute
	private static List<Entry> genericEntryList = new ArrayList<>();
	//endregion
	
	//region 2. Konstruktor
	private TestData() {}
	//endregion
	
	//region 3. Getter
	public static synchronized List<Entry> getTestData() {
	
		for (int i = 0; i < NUMBER_OF_TEST_ENTRIES; i++) {
			Entry e = new Entry(GENERIC_TIME + i, GENERIC_FOOD + i, GENERIC_CALORIES + i, GENERIC_DATE);
			genericEntryList.add(e);
		}
		
		return genericEntryList;
	}
	//endregion
	
}
