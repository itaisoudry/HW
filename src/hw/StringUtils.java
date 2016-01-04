package hw;

import java.util.Arrays;

public class StringUtils {
	public static void main(String[] args) {
		// // System.out.println(sortStringWords("To Be Or Not To Be"));
		//// System.out.println(deleteSubString("If you don’t have dreams, ",
		//// "If you don’t have dreams, you’ll never make your dreams come true
		// "));
		//// System.out.println(deleteSubString("It is better to be roughly
		// wrong ",
		//// "It is better to be roughly right than precisely wrong "));
		// System.out.println(mergeStrings ("boy","girl"));
	}

	public static String sortStringWords(String str) {
		// Split words
		String[] splitedString = str.split(" ");
		String sortedString = "";
		// Sort words
		Arrays.sort(splitedString);
		// Create new string from sorted array of words
		for (String word : splitedString) {
			sortedString += word + " ";
		}
		return sortedString;
	}

	public static String deleteSubString(String sub, String s) {
		String deletedString = s;
		// Check for substring
		if (deletedString.contains(sub)) {
			// replace it with "" (deletes it)
			return deletedString.replace(sub, "");
		}

		return deletedString;
	}

	public static String mergeStrings(String a, String b) {
		String mergesString = "";
		for (int i = 0; i < a.length(); i++) {
			// If the char is contained in the second string, it will be added
			// to the result string.
			if (b.contains(String.valueOf(a.charAt(i)))) {
				mergesString += String.valueOf(a.charAt(i));
			}
		}
		return mergesString;

	}

}
