package il.ac.tau.cs.sw1.ex5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BookRecommendations {

	private static final int NO_RATING = -1;
	private static final int AGE_GROUP_MARGINE_SIZE = 3;
	private Book[] books;
	private User[] users;
	private int[][] ratings;

	/**
	 * 
	 * @param books
	 * @param users
	 * @param ratings
	 * @pre ratings.length == users.length
	 * @pre ratings[0].length == books.length
	 */
	public BookRecommendations(Book[] books, User[] users, int[][] ratings) {
		this.books = books;
		this.users = users;
		this.ratings = ratings;
	}

	/**
	 * 
	 * @param fileName
	 * @param usersArray
	 * @param booksArray
	 * @return
	 * @throws Exception
	 * @pre usersArray.length != 0
	 * @pre booksArray.length != 0
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @post $ret.length = usersArray.length
	 * @post $ret[0].length = booksArray.length
	 * @post $res[i][j] == the rating of usersArray[i] to the booksArray[j]
	 */
	public static int[][] loadRatingsData(String fileName, User[] usersArray, Book[] booksArray) throws Exception {
		int[][] ratingMatrix = new int[usersArray.length][booksArray.length];
		ratingMatrix = initializeArray(ratingMatrix);
		BufferedReader bf = new BufferedReader(new FileReader(fileName));
		String line, isbn;
		int userId, rating, usersIndex = 0, booksIndex = 0;
		String[] splittedLine;
		boolean firstLine = true;
		while ((line = bf.readLine()) != null) {
			// Skips the first line.
			if (firstLine) {
				firstLine = false;
				line = bf.readLine();
				if (line == null) {
					break;
				}
			}
			// Splits the line by regex ';'
			splittedLine = line.split(";");
			userId = Integer.valueOf(splittedLine[0].replaceAll("\"", ""));
			isbn = splittedLine[1].replaceAll("\"", "");
			rating = Integer.valueOf(splittedLine[2].replace("\"", ""));
			// Get user and book indexes in te arrays
			usersIndex = getUserIdIndex(userId, usersArray);
			booksIndex = getISBNIndex(isbn, booksArray);
			// Incase user & isbn indexes was found, insert the rating in the
			// right place in the matrix.
			if (usersIndex != -1 && booksIndex != -1) {
				ratingMatrix[usersIndex][booksIndex] = rating;
			}
		}
		bf.close();
		return ratingMatrix;

	}

	// Gets the user id index in the users array
	private static int getUserIdIndex(int userId, User[] users) {
		int index = 0;
		while (index < users.length) {
			if (users[index].getUserID() == userId) {
				return index;
			}
			index++;
		}
		return -1;
	}

	// Gets the isbn index in the array
	private static int getISBNIndex(String isbn, Book[] books) {
		int index = 0;
		while (index < books.length) {
			if (books[index].getISBN().equals(isbn)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	private static int[][] initializeArray(int[][] mat) {
		int[][] returnMat = new int[mat.length][mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				returnMat[i][j] = -1;
			}
		}
		return returnMat;
	}

	/**
	 * 
	 * @param userIndex
	 * @return
	 * @pre userIndex >0 0 && userIndex < this.users.length
	 * @post $ret = average rating of all the books this.users[userIndex] rated
	 */
	public double getAverageRatingForUser(int userIndex) {
		int sum = 0;
		int count = 0;
		// Iterate over the books that the user rated
		for (int j = 0; j < ratings[userIndex].length; j++) {
			// If rating exist, sum it.
			if (ratings[userIndex][j] > NO_RATING) {
				sum += ratings[userIndex][j];
				count++;
			}
		}
		if (sum == 0) {
			return NO_RATING;
		}
		return ((double) sum / count);

	}

	/**
	 * 
	 * @param bookIndex
	 * @return
	 * @pre bookIndex >= 0 && bookIndex < this.books.length
	 * @post $ret = NO_RATING if no user had rated this.books[bookIndex]
	 * @post otherwise, $ret = average rating of this.books[bookIndex] among all
	 *       the users who rated it
	 */
	public double getAverageRatingForBook(int bookIndex) {
		int sum = 0;
		int count = 0;
		// Iterate over users who rated this book and sum the rating if exist
		for (int i = 0; i < ratings.length; i++) {
			if (ratings[i][bookIndex] > NO_RATING) {
				sum += ratings[i][bookIndex];
				count++;
			}
		}
		if (sum == 0) {
			return NO_RATING;
		}
		// Return average
		return ((double) sum / count);

	}

	/**
	 * 
	 * @param user
	 * @return
	 * @pre there exist i s.t. (such that) this.users[i] == user && user.age !=
	 *      NO_AGE
	 * @post $ret.lenght = this.users.lenght
	 * @post $ret[i] == true <=> this.users[i] in the user group of "user" (
	 *       user.age - AGE_GROUP_MARGINE_SIZE <= this.users.age <= user.age +
	 *       AGE_GROUP_MARGINE_SIZE
	 */
	public boolean[] getUsersInAgeGroup(User user) {
		boolean[] inAgeGroupArr = new boolean[users.length];
		int age = user.getAge();
		// Iterate over users array and check for each user if he is within the
		// age range.
		for (int i = 0; i < users.length; i++) {
			int compareAge = users[i].getAge();
			if (compareAge >= (age - AGE_GROUP_MARGINE_SIZE) && compareAge <= (age + AGE_GROUP_MARGINE_SIZE)) {
				inAgeGroupArr[i] = true;
			} else {
				inAgeGroupArr[i] = false;
			}
		}
		return inAgeGroupArr;

	}

	/**
	 * 
	 * @param bookIndex
	 * @param ageGroup
	 * @return
	 * @pre ageGroup.length == this.users.length
	 * @pre bookIndex >= 0 && bookIndex < this.books.length
	 * @post $res = NO_RATING if there is no user in the age group that rated
	 *       book[bookIndex]
	 * @post otherwise, $res = average ratings for all users this.users[i] s.t.
	 *       ageGroup[i] == true
	 */
	public double getAverageRatingForBookInAgeGroup(int bookIndex, boolean[] ageGroup) {
		int sum = 0;
		int rating = 0;
		int count = 0;
		// Iterate over the users that rated this book ( that are in the same
		// age group ) and sum their ratings
		for (int i = 0; i < ratings.length; i++) {
			if (ageGroup[i]) {
				rating = ratings[i][bookIndex];
				if (rating > NO_RATING) {
					sum += rating;
					count++;
				}
			}
		}
		if (sum > 0) {
			return ((double) sum / count);
		}
		return NO_RATING;

	}

	/**
	 * 
	 * @param user
	 * @return
	 * @pre there exist i s.t. this.users[i] == user && user.age != NO_AGE
	 * @pos $res = NO_RATING if there is no user in the age group that rated
	 *      book[bookIndex]
	 * @post $res = this.books[i] s.t. average for book[i] in the age group of
	 *       user is maximum
	 */
	public Book getHighestRatedBookInAgeGroup(User user) {
		boolean[] ageGroup = getUsersInAgeGroup(user);
		double maxRate = 0;
		double rate = 0;
		int maxRateIndex = NO_RATING;
		// for each book get his average rating from the user's age group
		for (int i = 0; i < books.length; i++) {
			rate = getAverageRatingForBookInAgeGroup(i, ageGroup);
			// Save the highest rating index so we could return the book with
			// the highest rating.
			if (rate > maxRate) {
				maxRate = rate;
				maxRateIndex = i;
			}
		}

		return books[maxRateIndex];

	}

	/**
	 * 
	 * @param user
	 * @param fileName
	 * @throws Exception
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @pre there exist i s.t. this.users[i] == user && user.age != NO_AGE
	 */
	public void printRecommendationToFile(User user, String fileName) throws Exception {
		Book bestBook = getHighestRatedBookInAgeGroup(user);
		boolean[] ageGroup = getUsersInAgeGroup(user);
		int bookIndex = getISBNIndex(bestBook.getISBN(), books);
		double avgAge = getAverageRatingForBookInAgeGroup(bookIndex, ageGroup);
		double avg = getAverageRatingForBook(bookIndex);

		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
		bufferedWriter.write(getRecommendedBookString(bestBook) + "\n");
		bufferedWriter.write(getRecommendedBookAverageInUserGroup(avgAge) + "\n");
		bufferedWriter.write(getRecommendedBookAverageFoAllUsers(avg) + "\n");
		bufferedWriter.close();

	}

	private String getRecommendedBookString(Book b) {
		return "The recommended Book for you is: " + b.toString();
	}

	private String getRecommendedBookAverageInUserGroup(double average) {
		return String.format("The book's average rating among its age group is: %.2f", average);
	}

	private String getRecommendedBookAverageFoAllUsers(double average) {
		return String.format("The book's average rating among all the users is: %.2f", average);
	}

}
