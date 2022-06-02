package de.tlnguyen.settings;

public class AppTexts {

	//region 0. Konstanten
	//Header/Name der Applikation
	public static final String APPLICATION_NAME = "---------------------------------\n"
			+ "|" + "\t\tDer Kalorienzähler\t\t" + "|\n"
			+ "---------------------------------\n" ;
	
	
	//Anzeige der Liste
	public static final String LEFT_BRACKET = "[";
	public static final String RIGHT_BRACKET = "] ";
	public static final String TIME = "Uhrzeit (HH:MM): ";
	public static final String FOOD = "Einnahme: ";
	public static final String CALORIES = "Kalorien in kcal: ";
	public static final String DIVIDING_LINE                 = " | ";
	public static final String INDEX_ENTRY_FORMAT            = LEFT_BRACKET + "%d" + RIGHT_BRACKET;
	public static final String SHOW_SINGLE_ENTRY_LINE_FORMAT = INDEX_ENTRY_FORMAT + TIME + "%s" + DIVIDING_LINE +
			FOOD + "%s" + DIVIDING_LINE +
			CALORIES + "%s\n";
	
	public static final String NO_ENTRIES_YET = "Es sind noch keine Einträge vorhanden!\n\n";
	public static final String MAX_CALORIES = "\t-\tTägliches Kalorienlimit: ";
	public static final String TOTAL_CALORIES = "Kalorieneinnahme Gesamt: ";
	public static final String CALORIE_BALANCE = "Kalorien bis zum Limit: ";
	public static final String TOTAL_LINE = "______________________________";
	
	//Anzeigen des Menues
	public static final String ENTRY = "Eintrag ";
	public static final String EDIT = "ändern";
	public static final String DELETE = "löschen";
	public static final String SET_LIMIT = "Kalorienlimit setzen";
	public static final String SAVE       = "Neuer Eintrag";
	public static final String EDIT_ENTRY   = ENTRY + EDIT;
	public static final String DELETE_ENTRY = ENTRY + DELETE;
	public static final String EXIT         = "Programm beenden";
	
	public static final String OPTION_SET = LEFT_BRACKET + AppCommands.CMD_SET_LIMIT + RIGHT_BRACKET;
	public static final String OPTION_SAVE = LEFT_BRACKET + AppCommands.CMD_SAVE + RIGHT_BRACKET;
	public static final String OPTION_EDIT = LEFT_BRACKET + AppCommands.CMD_EDIT + RIGHT_BRACKET;
	public static final String OPTION_DELETE = LEFT_BRACKET + AppCommands.CMD_DELETE + RIGHT_BRACKET;
	public static final String OPTION_EXIT = LEFT_BRACKET + AppCommands.CMD_EXIT + RIGHT_BRACKET;
	
	public static final String MENU = OPTION_SET + SET_LIMIT + "\n" +
			OPTION_SAVE + SAVE + "\n" +
			OPTION_EDIT + EDIT_ENTRY + "\n"+
			OPTION_DELETE + DELETE_ENTRY + "\n" +
			OPTION_EXIT + EXIT + "\n";
	
	//User-Messages (Aufforderungen zur Eingabe)
	public static final String USER_MSG_CHOOSE_OPTION = "Wählen Sie eine der Optionen in " +
			LEFT_BRACKET + " " + RIGHT_BRACKET + " :";
	public static final String USER_MSG_ENTER_LIMIT = "\n" + "Geben Sie Ihr gewünschtes Tageslimit an Kalorien (kcal) ein: ";
	public static final String USER_MSG_ENTER_DATA                = "\n" + "Eingabe der Daten: ";
	public static final String USER_MSG_CHOOSE_INDEX_ENTRY_FORMAT = "Wählen Sie den Index " +
			LEFT_BRACKET + " " + RIGHT_BRACKET + "des Eintrags, welchen Sie %s möchten:";
	public static final String USER_MSG_SAFETY_QUESTION_FORMAT = "\n" + "Möchten Sie den Eintrag " + INDEX_ENTRY_FORMAT +
			"wirklich %s?";
	
	//Programmende
	public static final String EXIT_APPLICATION = "\n" + "Das Programm wird beendet.";
	//endregion
	
	//region 1. Konstruktor
	private AppTexts() {}
	//endregion
}
