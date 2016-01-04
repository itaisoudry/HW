package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Itai Soudry
 * 
 */
public class SpellingCorrector {
	private static final int SORTED_ARR_MAX_SIZE = 3000;

	public static void main(String[] args) throws Exception {
		boolean quitFlag = false;
		String sentence = "";
		int correctionCount = 0;
		String[] splittedSentence;
		String[][] vocabulary = null;

		if (args.length == 0) {
			throw new Exception("[ERROR] File location is missing");
		}
		// I assume that the text file is located with the classes.
		Scanner inputScanner = new Scanner(System.in);
		File file = new File(buildString(args));
		Scanner scan;
		scan = new Scanner(file);
		String[] scanVocabulary = scanVocabulary(scan);

		System.out.println("Read " + scanVocabulary.length + " words from " + file.getName());
		System.out.println("Enter your sentence");
		sentence = inputScanner.nextLine();
		while (!sentence.equals("quit") && !quitFlag) {
			quitFlag = false;
			// Split sentence
			splittedSentence = splitSentence(sentence);
			// Iterate over words
			for (String word : splittedSentence) {
				vocabulary = findSimilarWords(word, scanVocabulary);
				int count = 1;
				// Skip word if no match
				if (vocabulary[0].length > 0) {
					continue;
				} else {
					// Print words by specific pattern
					System.out.println("The word " + word + " is incorrect:");
					System.out.println(vocabulary[1].length + " corrections of distance 1");
					System.out.println(vocabulary[2].length + " corrections of distance 2");
					// iterate over the first array ( 1 hamming count ) - until
					// 8 items or the end of the array
					for (int i = 0; count < 9 && i < vocabulary[1].length; i++) {
						System.out.println(count + ". " + vocabulary[1][i]);
						count++;
					}

					for (int j = 0; count < 9 && j < vocabulary[2].length; j++) {
						System.out.println(count + ". " + vocabulary[2][j]);
						count++;
					}

				}
				// Input choice from user
				System.out.println("Enter your choice:");
				String userInput = inputScanner.nextLine();
				// If the user enters something that is not a number \ a number
				// that is larger than 10
				if (userInput.matches("quit")) {
					quitFlag = true;
					break;
				}
				// Checks the user input - if a number, check if its between 1
				// and the number of options, else, repeatedly ask the user for
				// input until its correct
				while (userInput.length() != 1 || Character.isDigit(userInput.charAt(0))
						|| Character.isAlphabetic(userInput.charAt(0))) {
					if (Character.isDigit(userInput.charAt(0))) {
						if (Integer.valueOf(userInput) < count && Integer.valueOf(userInput) > 0) {
							break;
						}
					}
					System.out.println("[WARNING] input must be a number between 1 and " + (count - 1));
					userInput = inputScanner.nextLine();

				}

				if (Integer.valueOf(userInput) > count - 1) {
					// If the user input is invalid- print warning and ask
					// again for input.
					while (Integer.valueOf(userInput) > count - 1) {
						printInvalidChoice();
						userInput = inputScanner.nextLine();

					}
				}
				// Replace word to be corrected with the correct word.
				if (Integer.valueOf(userInput) > vocabulary[1].length)
					splittedSentence[Arrays.asList(splittedSentence)
							.indexOf(word)] = vocabulary[2][Integer.valueOf(userInput) - vocabulary[1].length - 1];
				else
					splittedSentence[Arrays.asList(splittedSentence)
							.indexOf(word)] = vocabulary[1][Integer.valueOf(userInput) - 1];
				correctionCount++;
			}
			// Check if 'quit' was entered
			if (!quitFlag) {
				System.out.println("The correct sentence is: " + buildSentence(splittedSentence));
				System.out.println("You corrected " + correctionCount + " Words.");
				System.out.println("Enter your sentence");
				// Reads next input line
				sentence = inputScanner.nextLine();
				// sentence = inputScanner.nextLine();
			}
		}
		// close scanners
		inputScanner.close();
		scan.close();
	}

	// Build string using string builder
	private static String buildString(String[] strArray) {
		StringBuilder builder = new StringBuilder();
		for (String str : strArray) {
			builder.append(str);
			builder.append(" ");
		}
		return builder.toString();
	}

	/**** use these method to print all your output messages ****/
	public static void printWordIncorrect(String word) {
		System.out.println("the word " + word + " is incorrect");
	}

	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printNumOfCorrections(int num, int distance) {
		System.out.println(num + " corrections of distance " + distance);
	}

	public static void printEnterYourSentence() {
		System.out.println("Enter your sentence:");
	}

	public static void printEnterYourChoice() {
		System.out.println("Enter your choice:");
	}

	public static void printCorrectionOption(int i, String correction) {
		System.out.println(i + ". " + correction);
	}

	public static void printInvalidChoice() {
		System.out.println("[WARNING] Invalid choice, try again.");
	}

	public static void printCorrectSentence(String sentence) {
		System.out.println("The correct sentence is: " + sentence);
	}

	public static void printNumOfCorrectedWords(int numOfWords) {
		System.out.println("Corrected " + numOfWords + " words.");
	}

	public static String[] scanVocabulary(Scanner scanner) {
		// Counter - to determine the size of the array to be returned
		int index = 0;
		// Max size array - for now max size is 3000
		String[] wordsArr = new String[SORTED_ARR_MAX_SIZE];
		// Iterating over words and adding them as lower case to the array
		while (scanner.hasNext()) {
			String word = scanner.next().toLowerCase();
			if (Arrays.asList(wordsArr).contains(word) && scanner.hasNext()) {
				word = scanner.next().toLowerCase();
			}
			if (!Arrays.asList(wordsArr).contains(word)) {
				wordsArr[index] = word.toLowerCase();
				index++;
			}
		}

		// create new array to be returned and sort it
		String[] sortedArr = new String[index];
		sortedArr = Arrays.copyOf(wordsArr, index);
		Arrays.sort(sortedArr);

		return sortedArr;
	}

	public static int calcHammingDistance(String word1, String word2) {
		int hamCount = 0;
		String firstWord, secondWord;
		// To determine which word is longer.
		if (word1.length() > word2.length()) {
			firstWord = word1;
			secondWord = word2;

		} else {
			firstWord = word2;
			secondWord = word1;

		}
		// Adds the size difference to the hamming distance counter
		hamCount += firstWord.length() - secondWord.length();
		// Check for differences between words
		for (int i = 0; i < secondWord.length(); i++) {
			if (firstWord.charAt(i) != (secondWord.charAt(i))) {
				hamCount++;
			}
		}
		return hamCount;
	}

	public static String[][] findSimilarWords(String word, String[] vocabulary) {
		String[][] similarArr = new String[3][];
		// Creating a vocabulary sized array, it is impossible that the number
		// of words in those arrays will out number the vocabulary size.
		String[] oneDiffArr = new String[vocabulary.length];
		String[] secDiffArr = new String[vocabulary.length];
		// Indexes for arrays
		int oneIndex = 0;
		int secIndex = 0;
		// Set the first array in the matrix to an array size 0 in case the word
		// is not in the vocabulary.
		similarArr[0] = new String[0];
		if (Arrays.asList(vocabulary).contains(word)) {
			similarArr[0] = new String[1];
			similarArr[0][0] = word;
		}
		// Using the calcHammingDistance method to determine the differences
		// between the words.
		for (String vocWord : vocabulary) {
			if (calcHammingDistance(word, vocWord) == 1) {
				oneDiffArr[oneIndex] = vocWord;
				oneIndex++;
			} else if (calcHammingDistance(word, vocWord) == 2) {
				secDiffArr[secIndex] = vocWord;
				secIndex++;
			}
		}
		// Create right-sized new arrays and insert them to the matrix and sort
		// them if needed.

		similarArr[1] = new String[oneIndex];
		similarArr[2] = new String[secIndex];
		similarArr[1] = Arrays.copyOf(oneDiffArr, oneIndex);
		similarArr[2] = Arrays.copyOf(secDiffArr, secIndex);
		if (oneIndex > 0) {
			Arrays.sort(similarArr[1]);
		}
		if (secIndex > 0) {
			Arrays.sort(similarArr[2]);
		}
		return similarArr;
	}

	public static String[] splitSentence(String sentence) {
		String[] splitedArr = sentence.split(" ");
		String[] splitedSentence = new String[splitedArr.length];
		int index = 0;
		// Iterate over words
		for (String word : splitedArr) {
			// If a word is empty (more than one space between words) - skip it
			if (word.isEmpty()) {
				continue;
			}
			// else - change to lower case and add it
			splitedSentence[index] = word.toLowerCase();
			index++;
		}
		// Copy to new right-sized array and return.
		String[] returnedArr = new String[index];
		returnedArr = Arrays.copyOf(splitedSentence, index);
		return returnedArr;
	}

	// Building sentence using string builder
	public static String buildSentence(String[] words) {
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			if (word.isEmpty()) {
				continue;
			}
			builder.append(word.toLowerCase() + " ");
		}

		return builder.toString().trim();
	}

	// Checks if a word is in a vocabulary
	public static boolean isInVocabulary(String[] vocabulary, String word) {
		if (Arrays.asList(vocabulary).contains(word)) {
			return true;
		}
		return false;
	}
}
