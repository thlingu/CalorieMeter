package de.tlnguyen.ui;

import de.rhistel.logic.ConsoleReader;
import de.tlnguyen.logic.CsvFileHandler;
import de.tlnguyen.model.Entry;
import de.tlnguyen.model.IntData;
import de.tlnguyen.settings.AppCommands;
import de.tlnguyen.settings.AppTexts;
import de.tlnguyen.testdata.TestData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UiController {
	
	//region 0. Konstanten
	
	public static final LocalDate DEFAULT_DATE           = LocalDate.now();
	public static final boolean   DEFAULT_EXIT           = false;
	/*public static final int       INDEX_DATE_YEAR        = 0;
	public static final int       INDEX_DATE_MONTH       = 1;
	public static final int       INDEX_DATE_DAY         = 2;*/
	//endregion
	
	//region 1. Attribute
	private List<Entry> dailyListOfCalorieEntries = new ArrayList<>();
	private IntData     totalCaloriesAndLimit     = new IntData();
	//endregion
	
	//region 2. Konstruktor
	public UiController() {
		dailyListOfCalorieEntries = CsvFileHandler.getInstance().readEntriesFromFile();
		totalCaloriesAndLimit = CsvFileHandler.getInstance().readIntDataFromFile();
		
		/*dailyListOfCalorieEntries = TestData.getTestData();
		totalCaloriesAndLimit.setTotalCalories(0);
		totalCaloriesAndLimit.setCalorieLimit(1500);*/
		
//		setAttributesFromCsvFile(CsvFileHandler.getInstance().readIntDataFromFile());
	}
	//endregion
	
	//region 3. startUi
	public void startUi() {
		
		printHeader();
		
		LocalDate date;
		int totalCalories;
		
		if (dailyListOfCalorieEntries.size() == 0) {
			date = LocalDate.now();
			totalCalories = 0;
			totalCaloriesAndLimit.setCalorieLimit(1500);
		} else {
			date = dailyListOfCalorieEntries.get(0).getDate();
			totalCalories = totalCaloriesAndLimit.getTotalCalories();
		}
		
		boolean exit = false;
		while (!exit) {
			
			if (!date.equals(LocalDate.now())) {
				System.out.println("\n\n" + "Ein neuer Tag hat begonnen! \n" +
						"Letzter Eintrag am: " + date + "\n" +
						"Gesamtkalorieneinnahme am " + date + " (in kcal): " + totalCalories);
				
				dailyListOfCalorieEntries = new ArrayList<>();
				date = LocalDate.now();
				totalCalories = 0;
			}
			
			showAllEntries();
			showMenu();
			
				switch (getUserOption()) {
					case AppCommands.CMD_SET_LIMIT -> {
						setBalance();
					}
					case AppCommands.CMD_SAVE -> save();
					case AppCommands.CMD_EDIT -> edit();
					case AppCommands.CMD_DELETE -> delete();
					case AppCommands.CMD_EXIT ->  {
						exit();
						exit = true;
					}
				}
		}
		
	}
	//endregion
	
	//region 4. Methoden und Funktionen
	private void printHeader() {
		System.out.println(AppTexts.APPLICATION_NAME);
	}
	
	private void printDateAndMaxCalories(LocalDate date, int calorieLimit) {
		System.out.println("\n\n" + date + AppTexts.MAX_CALORIES + calorieLimit + "\n");
	}
	
	private int calculateTotalCalories() {
		int totalCalories = 0;
		for (Entry e : dailyListOfCalorieEntries) {
			totalCalories += Integer.parseInt(e.getCalories());
		}
		
		return totalCalories;
	}
	
	public int calculateBalance(int totalCalories) {
		return totalCaloriesAndLimit.getCalorieLimit() - totalCalories;
	}
	
	private void printTotalCaloriesAndBalance(int totalCalories, int balance) {
		System.out.println(AppTexts.TOTAL_LINE);
		System.out.println(AppTexts.TOTAL_CALORIES + totalCalories);
		System.out.println(AppTexts.CALORIE_BALANCE + balance + "\n\n");
	}
	
	private void showAllEntries() {
		
		printDateAndMaxCalories(LocalDate.now(), totalCaloriesAndLimit.getCalorieLimit());
		
		if (dailyListOfCalorieEntries.isEmpty()) {
			System.out.println(AppTexts.NO_ENTRIES_YET);
		} else {
			for (int i = 0; i < dailyListOfCalorieEntries.size(); i++) {
				System.out.printf(AppTexts.SHOW_SINGLE_ENTRY_LINE_FORMAT, i, dailyListOfCalorieEntries.get(i).getTime(),
						dailyListOfCalorieEntries.get(i).getFood(), dailyListOfCalorieEntries.get(i).getCalories());
			}
		}
		
		int totalCalories = calculateTotalCalories();
		int balance = calculateBalance(totalCalories);
		printTotalCaloriesAndBalance(totalCalories, balance);
	}
	
	private int getUserOption() {
		int userOption;
		
		while (true) {
			
			System.out.print(AppTexts.USER_MSG_CHOOSE_OPTION);
			userOption = ConsoleReader.in.readPositivInt();
			
			if (userOption < 5) {
				return userOption;
			}
		}
	}
	
	
	//endregion
	
	//region 5. Menü
	private void showMenu() {
		System.out.println(AppTexts.MENU);
	}
	//endregion
	
	//region 6. Limit setzen
	private void setBalance() {
		System.out.print(AppTexts.USER_MSG_ENTER_LIMIT);
		totalCaloriesAndLimit.setCalorieLimit(ConsoleReader.in.readPositivInt());
		
		CsvFileHandler.getInstance().saveIntDataToFile(totalCaloriesAndLimit);
	}
	//endregion
	
	//region 6. Neuer Eintrag
	private void save() {
		
		dailyListOfCalorieEntries.add(getUserEntry());
		CsvFileHandler.getInstance().saveEntriesToFile(dailyListOfCalorieEntries);
	}
	//endregion
	
	//region 7. Eintrag bearbeiten
	private void edit() {
		showAllEntries();
		
		int     enteredIndex = getUserIndexEntry(AppCommands.CMD_EDIT);
		Entry enteredEntry = getUserEntry();
		
		System.out.printf(AppTexts.USER_MSG_SAFETY_QUESTION_FORMAT, enteredIndex, AppTexts.EDIT);
		boolean answerYes = ConsoleReader.in.readMandatoryAnswerToBinaryQuestion();
		
		if (answerYes) {
			dailyListOfCalorieEntries.remove(enteredIndex);
			dailyListOfCalorieEntries.add(enteredIndex, enteredEntry);
			
			CsvFileHandler.getInstance().saveEntriesToFile(dailyListOfCalorieEntries);
		}
	}
	//endregion
	
	//region 8. Eintrag loeschen
	private void delete() {
		showAllEntries();
		
		int   enteredIndex = getUserIndexEntry(AppCommands.CMD_DELETE);
		
		System.out.printf(AppTexts.USER_MSG_SAFETY_QUESTION_FORMAT, enteredIndex, AppTexts.DELETE);
		boolean answerYes = ConsoleReader.in.readMandatoryAnswerToBinaryQuestion();
		
		if (answerYes) {
			dailyListOfCalorieEntries.remove(enteredIndex);
			
			CsvFileHandler.getInstance().saveEntriesToFile(dailyListOfCalorieEntries);
		}
	}
	//endregion
	
	//region 9. Programm beenden
	private void exit() {
//		dailyListOfCalorieEntries.get(0).setDate(LocalDate.now());
		totalCaloriesAndLimit.setTotalCalories(calculateTotalCalories());
		
		CsvFileHandler.getInstance().saveIntDataToFile(totalCaloriesAndLimit);
		System.out.println(AppTexts.EXIT_APPLICATION);
	}
	//endregion
	
	//region 10.  Hilfsmethoden
	private Entry getUserEntry() {
		System.out.println(AppTexts.USER_MSG_ENTER_DATA);
		System.out.print(AppTexts.TIME);
		String enteredTime = ConsoleReader.in.readMandatoryString();
		System.out.print(AppTexts.FOOD);
		String enteredFood = ConsoleReader.in.readMandatoryString();
		System.out.print(AppTexts.CALORIES);
		String enteredCalories = ConsoleReader.in.readMandatoryString();
		
		return new Entry(enteredTime, enteredFood, enteredCalories, LocalDate.now());
	}
	
	public int getUserIndexEntry(int option) {
		int enteredIndex;
		
		while (true) {
			if (option == AppCommands.CMD_EDIT) {
				System.out.printf(AppTexts.USER_MSG_CHOOSE_INDEX_ENTRY_FORMAT, AppTexts.EDIT);
			} else {
				System.out.printf(AppTexts.USER_MSG_CHOOSE_INDEX_ENTRY_FORMAT, AppTexts.DELETE);
			}
			
			enteredIndex = ConsoleReader.in.readPositivInt();
			
			if (enteredIndex < dailyListOfCalorieEntries.size()) {
				return enteredIndex;
			}
		}
	}
	//endregion
}