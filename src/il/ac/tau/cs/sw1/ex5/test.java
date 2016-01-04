
package il.ac.tau.cs.sw1.ex5;

import java.util.Arrays;

public class test {

	public static void main(String[] args) throws Exception {

		String fileName = "D:\\Itai Work\\Availo\\hw\\src\\il\\ac\\tau\\cs\\sw1\\ex5\\BX-Users1.csv";
		User[] users = User.loadUsersData(fileName);

		String fileName1 = "D:\\Itai Work\\Availo\\hw\\src\\il\\ac\\tau\\cs\\sw1\\ex5\\BX-Books1.csv";
		Book[] books = Book.loadBooksData(fileName1);

		String fileName2 = "D:\\Itai Work\\Availo\\hw\\src\\il\\ac\\tau\\cs\\sw1\\ex5\\BX-Ratings1.csv";
		int[][] ratingsData = BookRecommendations.loadRatingsData(fileName2, users, books);

		BookRecommendations bR = new BookRecommendations(books, users, ratingsData);

		double av = bR.getAverageRatingForUser(5);
		System.out.println(av); // 1.4444444444444444
		double av1 = bR.getAverageRatingForBook(0);
		System.out.println(av1); // 4.0
		boolean[] gr = bR.getUsersInAgeGroup(users[5]);
		System.out.println(Arrays.toString(gr));
		// [true, false, false, false, true, true, false, false, false, true,
		// true, true, true, false, false, true,
		// false, true, false, false, false, false, false]
		double av2 = bR.getAverageRatingForBookInAgeGroup(0, gr);
		System.out.println(av2); // 4.125
		bR.printRecommendationToFile(users[5], "testFile");
		// The recommended Book for you is: 0195153448,Classical Mythology,Mark
		// P. O. Morford
		// The book's average rating among its age group is: 4.13 - #it's 4.125
		// rounded up to become 2 digits after the point
		// The book's average rating among all the users is: 4.00
	}

}
