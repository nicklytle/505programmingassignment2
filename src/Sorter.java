import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author bpjanos, nalytle
 * 
 *         An abstract class for sorting algorithms. This class simplifies
 *         reading in the input and writing the output. Additionally it makes it
 *         convenient to track the list that is being sorted, along with the
 *         runtime.
 *
 */
public abstract class Sorter {

	static ArrayList<Integer> integerList;
	static Scanner in;
	static int numItems;
	static double runtime = 0.0;
	static int numComparisons = 0;
	static double startTime;
	static double endTime;
	static Comparator<Integer> comp = new Counter();

	/**
	 * Reads in from Standard Input n, the number of integers to be sorted, and the
	 * n integers in the form of one per line. It then adds the integers to an
	 * ArrayList.
	 */
	public static void read() {
		in = new Scanner(System.in); // read in from Standard Input
		integerList = new ArrayList<Integer>(); // instantiate list
		numItems = Integer.parseInt(in.nextLine().replaceAll("[\\D]", ""));// read number of items
		for (int i = 0; i < numItems; i++) { // for number of items
			integerList.add(in.nextInt());// add item to list
		}
		in.close();
	}

	/**
	 * Writes the list of sorted integers to Standard Output (one per line) and logs
	 * the runtime (milliseconds expressed as an integer) and the number of
	 * comparisons the sorting function performed.
	 * 
	 * @param integerList The resulting sorted list of integers.
	 */
	public static void write(ArrayList<Integer> integerList) {
		for (Integer g : integerList) {
			System.out.println(g.toString());
		}
		/*
		 * Print to Standard Error the runtime (expressed in milliseconds) and the
		 * number of comparisons. Lines 52 and 53 are the output required for class
		 * where line 55 allows the output to a CSV file to make data analysis easier.
		 */
		System.err.println("runtime," + (int)( runtime / 1000000));
		System.err.println("comparisons," + numComparisons);

		// System.err.println((int) runtime / 1000000 + "," + numComparisons);
	}

}