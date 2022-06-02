package de.tlnguyen.model;

public class IntData {
	
	//region 0. Konstanten
	public static final String PARSING_SIGN         = ";";
	public static final int INDEX_TOTAL_CALORIES = 0;
	public static final int INDEX_CALORIE_LIMIT  = 1;
	//endregion
	
	//region 1. Attribute
	private int totalCalories;
	private int calorieLimit;
	//endregion
	
	//region 2. Konstruktoren
	
	//Standardkonstruktor
	public IntData() {
	}
	
	//2. Konstruktor (Ueberladener Konstruktor)
	public IntData(int totalCalories, int calorieLimit) {
		this.totalCalories = totalCalories;
		this.calorieLimit = calorieLimit;
	}
	//endregion
	
	//region 3. Getter und Setter
	public int getTotalCalories() {
		return totalCalories;
	}
	
	public void setTotalCalories(int totalCalories) {this.totalCalories = totalCalories;
	}
	
	public int getCalorieLimit() {
		return calorieLimit;
	}
	
	public void setCalorieLimit(int calorieLimit) {
		this.calorieLimit = calorieLimit;
	}
	
	public String getAttributesAsCsvFileString() {
		return totalCalories + PARSING_SIGN + calorieLimit;
	}
	
	public void setAttributesFromCsvFile(String csvFileString) {
		String[] splittedString = csvFileString.split(PARSING_SIGN);
		
		totalCalories = Integer.valueOf(splittedString[INDEX_TOTAL_CALORIES]);
		calorieLimit = Integer.valueOf(splittedString[INDEX_CALORIE_LIMIT]);
	}
	//endregion
	
	//region 4. toString()-Funktion
	@Override
	public String toString() {
		return "IntData{" +
				"time='" + totalCalories + '\'' +
				", food='" + calorieLimit + '\''
				;
	}
	//endregion
}
