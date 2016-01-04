package il.ac.tau.cs.sw1.ex8.filesSim;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;

public class FileIndex {
	private static final String ERROR = "[ERROR] ";
	private IHistogram<String> map = HistogramsFactory.getHistogram();
	private int filesCount = 0;
	private File[] listFiles;

	/**
	 * Given a path to a folder, reads all the files in it and indexes them
	 */
	public void index(String folderPath) {
		// first, clear the previous contents of the index
		clearPreviousIndex();
		File folder = new File(folderPath);
		listFiles = folder.listFiles();
		filesCount = listFiles.length;
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				String path = file.getAbsolutePath();
				System.out.println("Indexing " + path);
				try {
					// add to the index if read is successful
					addFileToIndex(file);
				} catch (IOException e) {
					System.out.println(ERROR + "failed to read from " + path);
				}
			}
		}
	}

	/**
	 * Adds the input file to the index
	 */
	public void addFileToIndex(File file) throws IOException {
		List<String> tokens = FileUtils.readAllTokens(file);
		String path = file.getAbsolutePath();
		if (tokens.isEmpty()) {
			System.out.println(ERROR + "ignoring empty file " + path);
			return;
		}
		for (String token : tokens) {
			map.addItem(token);
		}

	}

	/**
	 * Called at the beginning of index() in order to clear the fields from
	 * previously indexed files. After calling it the index contains no files.
	 */
	public void clearPreviousIndex() {
		map.clear();
	}

	/**
	 * Given indexed input files, compute their cosine similarity based on their
	 * indexed tokens
	 * 
	 * @throws IOException
	 */
	public double getCosineSimilarity(File file1, File file2) throws IOException {
		double oneTokens = 0, twoTokens = 0;
		double count = 0;
		if (!verifyFile(file1) || !verifyFile(file2)) {
			return Double.NaN;
		}
		List<String> tokens1 = FileUtils.readAllTokens(file1);
		List<String> tokens2 = FileUtils.readAllTokens(file2);
		IHistogram<String> map1 = HistogramsFactory.getHistogram();
		IHistogram<String> map2 = HistogramsFactory.getHistogram();
		// Load items into histogram
		for (String token : tokens1) {
			map1.addItem(token);
		}
		for (String token : tokens2) {
			map2.addItem(token);
		}

		// Iterate over tokens to calculate the denominator
		Iterator<String> itr1 = map1.getItemsSet().iterator();
		while (itr1.hasNext()) {
			oneTokens += Math.pow(map1.getCountForItem(itr1.toString()), 2);
			itr1.next();
		}

		Iterator<String> itr2 = map2.getItemsSet().iterator();
		while (itr1.hasNext()) {
			twoTokens += Math.pow(map2.getCountForItem(itr2.toString()), 2);
			itr2.next();
		}
		// Sqrt of the denominator ( for each file )
		oneTokens = Math.sqrt(oneTokens);
		twoTokens = Math.sqrt(twoTokens);

		// Calculates the nominator
		for (String token : tokens1) {
			if (tokens2.contains(token)) {
				count += map1.getCountForItem(token) * map2.getCountForItem(token);
			}
		}
		return (count) / (oneTokens * twoTokens);
	}

	/**
	 * Given indexed input files, return the number of the common token in both
	 * files
	 * 
	 * @throws IOException
	 */
	public int getCommonTokensNum(File file1, File file2) throws IOException {
		List<String> commonTokens = new ArrayList<String>();
		if (!verifyFile(file1) || !verifyFile(file2)) {
			return 0;
		}
		// Load into lists
		List<String> tokens1 = FileUtils.readAllTokens(file1);
		List<String> tokens2 = FileUtils.readAllTokens(file2);
		// checks if both lists contains the same word, if they do, add it to a
		// list of common words ( without duplicatin )
		for (String token : tokens1) {
			if (tokens2.contains(token) && !commonTokens.contains(token)) {
				commonTokens.add(token);
			}
		}

		return commonTokens.size();

	}

	/**
	 * returns true iff the input file is currently indexed. Otherwise, prints
	 * an error message.
	 */
	public boolean verifyFile(File file) {
		if (Arrays.asList(listFiles).contains(file))
			return true;
		System.out.println(ERROR + " File not verifyed");
		return false;
	}

	/**
	 * @return the number of files currently indexed.
	 */
	public int getNumIndexedFiles() {
		return filesCount;
	}

}
