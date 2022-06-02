package de.tlnguyen.model;

import java.time.LocalDate;

public class Entry {
	
	//region 0. Konstanten
	public static final String PARSING_SIGN = ";";
	public static final int INDEX_TIME = 0;
	public static final int INDEX_FOOD = 1;
	public static final int INDEX_CALORIES = 2;
	public static final int INDEX_DATE = 3;
	//endregion
	
	//region 1. Attribute
	private String time;
	private String food;
	private String calories;
	private LocalDate date;
	//endregion
	
	//region 2. Konstruktoren
	
	//Standardkonstruktor
	public Entry() {
	}
	
	//2. Konstruktor (Ueberladener Konstruktor)
	public Entry(String time, String food, String calories, LocalDate date) {
		this.time = time;
		this.food = food;
		this.calories = calories;
		this.date = date;
	}
	//endregion
	
	//region 3. Getter und Setter
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getFood() {
		return food;
	}
	
	public void setFood(String food) {
		this.food = food;
	}
	
	public String getCalories() {
		return calories;
	}
	
	public void setCalories(String calories) {
		this.calories = calories;
	}
	
	public LocalDate getDate() { return date;}
	
	public void setDate(LocalDate date) { this.date = date;}
	
	public String getAttributesAsCsvFileString() {
		return time + PARSING_SIGN + food + PARSING_SIGN + calories
				+ PARSING_SIGN + date;
	}
	
	public void setAttributesFromCsvFile(String csvFileString) {
		String[] splittedString = csvFileString.split(PARSING_SIGN);
		
		time = splittedString[INDEX_TIME];
		food = splittedString[INDEX_FOOD];
		calories = splittedString[INDEX_CALORIES];
		date = LocalDate.parse(splittedString[INDEX_DATE]);
	}
	//endregion
	
	//region 4. toString()-Funktion
	@Override
	public String toString() {
		return "Entry{" +
				"time='" + time + '\'' +
				", food='" + food + '\'' +
				", calories='" + calories + '\'' +
				", date=" + date +
				'}';
	}
	//endregion
}
