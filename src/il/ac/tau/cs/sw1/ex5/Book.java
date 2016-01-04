package il.ac.tau.cs.sw1.ex5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Book {

	private static final int MAX_BOOK_IN_FILE = 20000;

	private String ISBN;
	private String bookName;
	private String bookAuthor;
	private String yearOfPublication;
	private String publisher;

	public Book(String ISBN, String bookName, String bookAuthor, String yearOfPUblication, String publisher) {
		this.ISBN = ISBN;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.yearOfPublication = yearOfPUblication;
		this.publisher = publisher;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public String getPublisher() {
		return publisher;
	}

	public String toString() {
		StringBuffer sB = new StringBuffer();
		char sep = ',';
		sB.append(this.ISBN).append(sep).append(this.bookName).append(sep).append(this.bookAuthor);
		return sB.toString();
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @post $ret is an Arrays of Book objects read from the file fileName
	 */
	public static Book[] loadBooksData(String fileName) throws Exception {
		Book[] bookData = new Book[MAX_BOOK_IN_FILE];
		boolean firstLine = true;
		int bookIndex = 0;
		String line, isbn, title, author, publishYear, publisher;
		String[] splitLine = new String[5];

		// Reads input file
		BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)));

		while ((line = bf.readLine()) != null) {
			// Skips the first line of the file.
			if (firstLine) {
				firstLine = false;
				line = bf.readLine();
				if (line == null) {
					break;
				}
			}
			// Split the line according to '";"' - different than a user, a book
			// title might contain ';'.
			splitLine = line.split("\";\"");
			// Lose all quotes marks if they are any.
			isbn = splitLine[0].replaceAll("\"", "");
			title = splitLine[1];
			author = splitLine[2].replaceAll("\"", "");
			publishYear = splitLine[3].replaceAll("\"", "");
			publisher = splitLine[4].replaceAll("\"", "");

			bookData[bookIndex] = new Book(isbn, title, author, publishYear, publisher);
			bookIndex++;
		}
		bf.close();
		return Arrays.copyOf(bookData, bookIndex);

	}

}
