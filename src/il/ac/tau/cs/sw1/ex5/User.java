package il.ac.tau.cs.sw1.ex5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class User {
	private int userID;
	private String location;
	private int age;

	private static final int NO_AGE = -1;
	private static final int MAX_USERS_IN_FILE = 20000;

	public User(int userID, String location, int age) {
		this.userID = userID;
		this.location = location;
		this.age = age;
	}

	public User(int userID, String location) {
		this.userID = userID;
		this.location = location;
		this.age = NO_AGE;
	}

	/**
	 * Getters and Setters
	 */
	public int getUserID() {
		return userID;

	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString(String str) {
		StringBuilder builder = new StringBuilder();
		builder.append(this.userID).append(",").append(this.location).append(",");
		if (age != NO_AGE) {
			builder.append(age);
		}
		return builder.toString();
	}

	/**
	 * 
	 * @return
	 * @post ($ret == true) <=> (this.age != NO_AGE)
	 */
	public boolean hasAge() {
		return this.age != NO_AGE ? true : false;

	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @post $ret is an Arrays of User objects read from the file fileName
	 */

	public static User[] loadUsersData(String fileName) throws Exception {
		User[] userData = new User[MAX_USERS_IN_FILE];
		boolean firstLine = true;
		int userIndex = 0;
		String line, age, location, userId;
		String[] splitLine = new String[3];
		User userToAdd;
		// Reads input file
		BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)));

		while ((line = bf.readLine()) != null) {
			// skips the first line in the file
			if (firstLine) {
				firstLine = false;
				line = bf.readLine();
				if (line == null) {
					break;
				}
			}
			// Split the line to age location and user id
			splitLine = line.split(";");
			// Lose all quotes marks if they are any.
			age = splitLine[2].replaceAll("\"", "");
			location = splitLine[1].replaceAll("\"", "");
			userId = splitLine[0].replaceAll("\"", "");

			// Calls constructor - with age or without, depends on the
			// input.
			if (age.equals("NULL")) {
				userToAdd = new User(Integer.valueOf(userId), location);
			} else {
				userToAdd = new User(Integer.valueOf(userId), location, Integer.valueOf(age));
			}

			userData[userIndex] = userToAdd;
			userIndex++;

		}
		bf.close();

		return Arrays.copyOf(userData, userIndex);
	}

}
